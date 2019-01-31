package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import main.java.com.hipopay.tool.Tool;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppPay {
    public static void main(String[] args) throws IOException {
        /*
            'out_trade_id' => 'your_trade_id',              # 商户交易流水号 Y
            'amount' => '1',                                # 支付单金额，单位为元，精度最多小数点后两位(如果是JPY和KRW，单位为分) Y
            'currency' => 'HKD',                            # 结算币种 Y
            'product_info' => 'test product',               # 商品信息 Y
            'appid' => MERCHANT_APPID,                      # 微信开放平台分配的appid	Y
            'client_ip' => 'test_agent_order_id',           # 客户端设备IP地址 Y
            'notify_url' => 'test_product_id',              # 异步通知地址 N
         */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("out_trade_id", "your_trade_id"));
        params.add(new BasicNameValuePair("amount", "100"));
        params.add(new BasicNameValuePair("currency", "HKD"));
        params.add(new BasicNameValuePair("product_info", "test product"));
        params.add(new BasicNameValuePair("appid", Tool.getAppid()));
        params.add(new BasicNameValuePair("client_ip", "0.0.0.0"));
        params.add(new BasicNameValuePair("notify_url", "your_notify_url"));
        //$isCNY 是否采用人民币(CNY)计价，取值"TRUE"/"FALSE"，默认值为"FALSE"
        WechatCN api = new WechatCN(false);
        api.appPay(params);
    }
}
