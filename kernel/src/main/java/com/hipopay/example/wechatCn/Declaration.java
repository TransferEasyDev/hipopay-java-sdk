package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Declaration {
    public static void main(String[] args) throws IOException {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("out_order_no", "your_order_no"));
        params.add(new BasicNameValuePair("payment_no", "hipopay_payment_no"));
        params.add(new BasicNameValuePair("customs", "SHENZHEN"));
//        params.add(new BasicNameValuePair("duty", "88"));
        params.add(new BasicNameValuePair("action_type", "ADD"));

        params.add(new BasicNameValuePair("sub_order_no", "your_sub_order_no"));
        params.add(new BasicNameValuePair("sub_order_amount", "46.44"));
        params.add(new BasicNameValuePair("transport_amount", "23.22"));
        params.add(new BasicNameValuePair("product_amount", "23.22"));

        // 若要身份验证则需要填以下参数
        params.add(new BasicNameValuePair("cert_id", "payer_chn_id"));
        params.add(new BasicNameValuePair("name", "payer_name"));

        WechatCN api = new WechatCN();
        api.declaration(params);
    }
}
