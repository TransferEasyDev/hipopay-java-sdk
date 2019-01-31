package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetRate {
    public static void main(String[] args) throws IOException {
        /*
            'currency'  # 币种	        是
         */
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("currency", "HKD"));
        WechatCN api = new WechatCN();
        api.getRate(params);
    }
}
