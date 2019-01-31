package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetBill {
    public static void main(String[] args) throws IOException {
        WechatCN api = new WechatCN();

        /*
        TODO:
         */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        api.getBill(params);
    }
}
