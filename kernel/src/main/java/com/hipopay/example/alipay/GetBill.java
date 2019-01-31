package main.java.com.hipopay.example.alipay;

import main.java.com.hipopay.api.Alipay;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetBill {
    public static void main(String[] args) throws IOException {
        Alipay api = new Alipay();

        /*
        TODO:
         */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        api.getBill(params);
    }
}
