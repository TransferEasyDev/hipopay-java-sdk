package main.java.com.hipopay.example.alipay;

import main.java.com.hipopay.api.Alipay;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Refund {
    public static void main(String[] args) throws IOException {
        Alipay api = new Alipay();

        /*
            'payment_no'    # 支付单号 N
            'out_refund_id' # 外部退款单号 N
            'refund_amount' # 退款金额。传入此参数，可发起多次退款，退款总额不超过订单金额；不传此参数则是全额退款；
         */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("payment_no", "hp_payment_no"));
        params.add(new BasicNameValuePair("out_refund_id", "your_refund_id"));
        params.add(new BasicNameValuePair("refund_amount", "100"));
        api.refund(params);
    }
}
