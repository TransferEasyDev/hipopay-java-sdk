package main.java.com.hipopay.example.alipay;

import main.java.com.hipopay.api.Alipay;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Redeclaration {
    public static void main(String[] args) throws IOException {
        Alipay api = new Alipay();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("payment_no", "hipopay_payment_no"));
        params.add(new BasicNameValuePair("out_order_no", "your_order_no"));
        params.add(new BasicNameValuePair("sub_order_no", "your_sub_order_no"));
        params.add(new BasicNameValuePair("customs", "SHENZHEN"));
        params.add(new BasicNameValuePair("mch_customs_no", "123456789BTW"));
        params.add(new BasicNameValuePair("merchant_customs_name", "xxxhanguo_card"));

        api.redeclaration(params);
    }
}

