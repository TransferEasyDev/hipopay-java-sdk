package main.java.com.hipopay.example.alipay;

import main.java.com.hipopay.api.Alipay;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetRate {
    public static void main(String[] args) throws IOException {
        Alipay api = new Alipay();

        /*
            'currency'  # 币种	        是
         */
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("currency", "HKD"));
        api.getRate(params);
    }

}
