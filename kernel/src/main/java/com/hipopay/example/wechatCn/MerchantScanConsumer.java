package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerchantScanConsumer {
    public static void main(String[] args) throws IOException {
        /*
            'out_trade_id'  # 商户交易流水号 Y
            'amount'        # 支付单金额，单位为元，精度最多小数点后两位(如果是JPY和KRW，单位为分) Y
            'currency'      # 支付单结算币种 Y
            'auth_code'     # 二维码内容 Y
            'product_info'  # 商品信息 Y
            'client_ip'     # 客户端设备IP地址 Y
            'notify_url'    # 异步通知地址 N
        */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("out_trade_id", "your_trade_id"));
        params.add(new BasicNameValuePair("amount", "100"));
        params.add(new BasicNameValuePair("currency", "HKD"));
        params.add(new BasicNameValuePair("auth_code", ""));
        params.add(new BasicNameValuePair("product_info", "test product"));
        params.add(new BasicNameValuePair("client_ip", "0.0.0.0"));
        params.add(new BasicNameValuePair("notify_url", "your_notify_url"));
        //$isCNY 是否采用人民币(CNY)计价，取值"TRUE"/"FALSE"，默认值为"FALSE"
        WechatCN api = new WechatCN(false);
        api.consumerScanWeb(params);
    }
}
