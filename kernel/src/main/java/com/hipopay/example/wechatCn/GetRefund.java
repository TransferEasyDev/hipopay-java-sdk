package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetRefund {
    public static void main(String[] args) throws IOException {
        WechatCN api = new WechatCN();
        /*
            'refund_no'      # 退款单号，和out_refund_id不可同时为空
            'out_refund_id'  # 外部退款单号，和refund_no不可同时为空
         */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("refund_no", "hp_refund_no"));
        params.add(new BasicNameValuePair("out_refund_id", "your_refund_id"));
        api.getRefund(params);
    }
}
