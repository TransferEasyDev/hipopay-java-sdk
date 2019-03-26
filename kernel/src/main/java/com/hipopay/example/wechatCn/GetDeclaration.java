package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetDeclaration {
    public static void main(String[] args) throws IOException {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // 以下三个请求参数三选一，若拆单则传sub_order_no，若未拆单则传payment_no或out_order_no
        params.add(new BasicNameValuePair("payment_no", "hipopay_payment_no"));
        params.add(new BasicNameValuePair("out_order_no", "your_order_no"));
        params.add(new BasicNameValuePair("sub_order_no", "your_sub_order_no"));
        params.add(new BasicNameValuePair("customs", "SHENZHEN"));

        WechatCN api = new WechatCN();
        api.getDeclaration(params);
    }
}
