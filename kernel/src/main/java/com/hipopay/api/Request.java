package main.java.com.hipopay.api;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import main.java.com.hipopay.tool.Tool;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Request {
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private HttpResponse response;

    private String CHARSET = "UTF-8";
    private String version = Tool.getVersion();
    private String merchantNo = Tool.getMerchantNo();

    private String url;
    private String privateKey;
    private String timestamp;
    private String signature;
    private String strParams;
    private List<NameValuePair> params;

    Request(String api_url, List<NameValuePair> params) throws IOException {
        Integer requestTime = (int) (System.currentTimeMillis() / 1000);
        String timestamp = requestTime.toString();
        String hpHost = Tool.getHost();

        this.privateKey = Tool.getPrivateKey();
        this.url = hpHost + api_url;
        this.timestamp = timestamp;
        this.strParams = sortedStrParams(params);
        this.signature = generateSign();
        this.params = params;
        printRequest();
    }

    private String urlEncode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        } else {
            return URLEncoder.encode(str, CHARSET).replaceAll("\\+", "%20");
        }
    }

    private String map2String(TreeMap<String, Object> sortedParams) throws  UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        Set<String> keys = sortedParams.keySet();
        for (String key: keys) {
            result.append(urlEncode(key)).append("=").append(urlEncode(sortedParams.get(key).toString())).append("&");
        }
        return result.substring(0, result.length()-1);
    }

    private String sortedStrParams(List<NameValuePair> params) throws UnsupportedEncodingException {
        Map<String, String> mappedParams = params.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        TreeMap<String, Object> sortedParams = new TreeMap<String, Object>(mappedParams);
        return map2String(sortedParams);
    }

    private String generateSign() {
        // Add Timestamp in String end
        strParams += ("," + timestamp);
        // Return signature
        return HipopaySignature.sign(strParams, privateKey, CHARSET);
    }

    //common
    private void printRequest() {
        System.out.println("========接口url==============");
        System.out.println(this.url);
        System.out.println("========request参数==========");
        System.out.println(this.params.toString());
        System.out.println("========header==========");
        System.out.println(this.timestamp);
        System.out.println(this.signature);
        System.out.println(this.version);
        System.out.println(this.merchantNo);
    }

    private String exchangeResult(HttpResponse response)
            throws IOException {
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println("========response==============");
        System.out.println(result.toString());
        return result.toString();
    }

    //Get, Delete
    private String getFullUrl() {
        StringBuilder getUrl = new StringBuilder();
        getUrl.append(this.url);

        if(this.params.size() > 0) {
            getUrl.append("?");
            getUrl.append(this.params.get(0).toString());
            for (int i = 1 ; i < this.params.size(); i++) {
                getUrl.append("&");
                getUrl.append(this.params.get(i).toString());
            }
        }
        System.out.println(getUrl.toString());
        return getUrl.toString();

    }

    void requestGet()
            throws IOException {

        HttpGet httpGet = new HttpGet(getFullUrl());
        httpGet.setHeader("Version", this.version);
        httpGet.setHeader("MerchantNo", this.merchantNo);
        httpGet.setHeader("Signature", this.signature);
        httpGet.setHeader("Timestamp", this.timestamp);
        this.response = this.httpclient.execute(httpGet);

        exchangeResult(this.response);
    }

    public String requestGet(String path)
            throws IOException {

        HttpGet httpGet = new HttpGet(getFullUrl());
        httpGet.setHeader("Version", this.version);
        httpGet.setHeader("MerchantNo", this.merchantNo);
        httpGet.setHeader("Signature", this.signature);
        httpGet.setHeader("Timestamp", this.timestamp);
        this.response = this.httpclient.execute(httpGet);

        FileWriter wt = new FileWriter(path);
        BufferedWriter wf = new BufferedWriter(wt);
        BufferedReader rd =  new BufferedReader(
                new InputStreamReader(this.response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line = "";

        while ((line = rd.readLine()) != null) {
            result.append(line);
            result.append("\n");
            wf.write(line + "\n");
        }
        wf.close();
        return result.toString();
    }

    public String requestDelete()
            throws IOException {

        HttpDelete httpDelete = new HttpDelete(getFullUrl());
        httpDelete.setHeader("Version", this.version);
        httpDelete.setHeader("MerchantNo", this.merchantNo);
        httpDelete.setHeader("Signature", this.signature);
        httpDelete.setHeader("Timestamp", this.timestamp);
        this.response = this.httpclient.execute(httpDelete);

        return exchangeResult(this.response);
    }

    /*
        POST
        JSON  参数为JSON请求
        List  参数为普通form参数请求
        MultipartEntityBuilder  参数带文件流的请求
     */


    void requestPost()
            throws IOException {
        HttpPost httpPost = new HttpPost(this.url);
        httpPost.setHeader("Version", this.version);
        httpPost.setHeader("MerchantNo", this.merchantNo);
        httpPost.setHeader("Signature", this.signature);
        httpPost.setHeader("Timestamp", this.timestamp);
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(this.params, Consts.UTF_8);
        httpPost.setEntity(postEntity);
        this.response = this.httpclient.execute(httpPost);

        exchangeResult(this.response);
    }

    /*
        PUT
        JSON  参数为JSON请求
        List  参数为普通form参数请求
        MultipartEntityBuilder  参数带文件流的请求
     */


    public String requestPut()
            throws IOException {

        HttpPut httpPut = new HttpPut(this.url);
        httpPut.setHeader("Version", this.version);
        httpPut.setHeader("MerchantNo", this.merchantNo);
        httpPut.setHeader("Signature", this.signature);
        httpPut.setHeader("Timestamp", this.timestamp);
        UrlEncodedFormEntity putEntity = new UrlEncodedFormEntity(this.params, Consts.UTF_8);
        httpPut.setEntity(putEntity);
        this.response = this.httpclient.execute(httpPut);

        return exchangeResult(this.response);
    }

}
