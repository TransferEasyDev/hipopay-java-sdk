package main.java.com.hipopay.tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Tool {
    private static Properties getProp() throws IOException{
        Properties props = new Properties();
        InputStream config = Tool.class.getResourceAsStream("../resources/config.properties");
        props.load(config);
        config.close();
        return props;
    }
    public static String getHost() throws IOException {
        Properties props = Tool.getProp();
        return props.getProperty("HP_HOST");
    }

    public static String getMerchantNo() throws IOException {
        Properties props = Tool.getProp();
        return props.getProperty("MERCHANT_NO");
    }

    public static String getMiniAppid() throws IOException {
        Properties props = Tool.getProp();
        return props.getProperty("MERCHANT_MINI_APPID");
    }

    public static String getAppid() throws IOException {
        Properties props = Tool.getProp();
        return props.getProperty("MERCHANT_APPID");
    }

    public static String getVersion() throws IOException {
        Properties props = Tool.getProp();
        return props.getProperty("VERSION");
    }

    public static String getPrivateKey() throws IOException {
        Properties props = Tool.getProp();
        String key_path = props.getProperty("PRIVATE_KEY_PATH");

        FileInputStream inputStream = new FileInputStream(key_path);
        byte[] keyBytes = new byte[inputStream.available()];
        int i = inputStream.read(keyBytes);
        System.out.print(i);
        inputStream.close();
        return new String(keyBytes, "UTF-8");

    }

    public static String getPublicKey() throws IOException {
        Properties props = Tool.getProp();
        String key_path = props.getProperty("PUBLIC_KEY_PATH");

        FileInputStream inputStream = new FileInputStream(key_path);
        byte[] keyBytes = new byte[inputStream.available()];
        int i = inputStream.read(keyBytes);
//        System.out.print(i);
        inputStream.close();
        return new String(keyBytes, "UTF-8");

    }
}
