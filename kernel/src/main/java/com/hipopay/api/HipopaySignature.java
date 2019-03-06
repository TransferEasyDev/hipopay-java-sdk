package main.java.com.hipopay.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import main.java.com.hipopay.tool.Tool;
import org.apache.commons.codec.binary.Base64;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;


public class HipopaySignature {

    public static String sign(String data, String PEMEncodedPrivateKey, String charset) {
        PrivateKey privateKey = getPrivateKeyFromPEM(PEMEncodedPrivateKey);
        if (privateKey == null) {
            return null;
        }

        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes(charset));
            byte[] signBytes = signature.sign();

            return Base64.encodeBase64String(signBytes).replaceAll("[\n\r]", "");
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static PrivateKey getPrivateKeyFromPEM(String PEMEncodedPrivateKey) {
        PEMEncodedPrivateKey = PEMEncodedPrivateKey
                .replaceAll("(-+BEGIN (RSA )?PRIVATE KEY-+\\r?\\n|-+END (RSA )?PRIVATE KEY-+\\r?\\n?)", "");
        byte[] privateKeyBytes = Base64.decodeBase64(PEMEncodedPrivateKey);

        try {
            return generatePrivateKeyWithPKCS8(privateKeyBytes);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return generatePrivateKeyWithPKCS1(privateKeyBytes);
        }
    }

    private static PrivateKey generatePrivateKeyWithPKCS8(byte[] privateKeyBytes)
            throws InvalidKeySpecException {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PrivateKey generatePrivateKeyWithPKCS1(byte[] privateKeyBytes) {
        try {
            DerInputStream derReader = new DerInputStream(privateKeyBytes);
            DerValue[] seq = derReader.getSequence(0);
            if (seq.length < 9) {
                System.out.println("Could not parse a PKCS1 private key.");
                return null;
            }
            // skip version seq[0];
            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger prime1 = seq[4].getBigInteger();
            BigInteger prime2 = seq[5].getBigInteger();
            BigInteger exp1 = seq[6].getBigInteger();
            BigInteger exp2 = seq[7].getBigInteger();
            BigInteger crtCoef = seq[8].getBigInteger();
            RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(
                    modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePrivate(spec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String map2String4verfiy(Map<String, Object> params) throws  UnsupportedEncodingException {
        TreeMap<String, Object> sortedParams = new TreeMap<String, Object>(params);
        Set<String> keys = sortedParams.keySet();

        StringBuilder result = new StringBuilder();
        for (String key: keys) {
            result.append(key).append("=").append(sortedParams.get(key).toString()).append("&");
        }
        return result.substring(0, result.length()-1);
    }

    private static String getKey(String filename) throws IOException {
        // Read key from file
        StringBuilder strKeyPEM = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            strKeyPEM.append(line).append("\n");
        }
        br.close();
        return strKeyPEM.toString();
    }

    private static RSAPublicKey getPublicKeyFromString(String key) throws IOException, GeneralSecurityException {
        String publicKeyPEM = key;
        publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
        byte[] encoded = Base64.decodeBase64(publicKeyPEM);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
    }


    private static RSAPublicKey getPublicKey(String publicKey) throws IOException, GeneralSecurityException {

        String publicKeyPEM = "";
        if (publicKey.startsWith("-----BEGIN")) {
            publicKeyPEM = publicKey;
        } else {
            publicKeyPEM = getKey(publicKey);
        }

        return getPublicKeyFromString(publicKeyPEM);
    }

    public static Boolean verify(Map<String, Object> params, String sign, String timestamp) throws Exception {
        String strParams = map2String4verfiy(params) + ',' + timestamp;
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(getPublicKey(Tool.getPublicKey()));
        signature.update(strParams.getBytes("UTF-8"));
        return signature.verify(Base64.decodeBase64(sign));
    }


}
