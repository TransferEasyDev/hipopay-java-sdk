package main.java.com.hipopay.example.alipay;

import main.java.com.hipopay.api.Alipay;
import main.java.com.hipopay.tool.Tool;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetBill {
    public static void main(String[] args) throws IOException {
        Alipay api = new Alipay();


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("merchant_no", Tool.getMerchantNo()));
        params.add(new BasicNameValuePair("start_date", "20190101"));
        params.add(new BasicNameValuePair("end_date", "20190120"));
        // 返回结果是 start_date 00:00:00 ~ end_date 23:59:59 范围内的订单记录
        api.getBill(params);
    }
}
