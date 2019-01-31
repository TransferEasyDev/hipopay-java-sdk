package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPayment {
    public static void main(String[] args) throws IOException {
        WechatCN api = new WechatCN();

        /*
            'payment_no' => 'hp_payment_no',        # 支付单号 N
            'out_trade_id' => 'your_trade_id',      # 商户交易流水号 N
         */
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("payment_no", "hp_payment_no"));
        params.add(new BasicNameValuePair("out_trade_id", "your_trade_id"));
        api.getPayment(params);
    }
}
