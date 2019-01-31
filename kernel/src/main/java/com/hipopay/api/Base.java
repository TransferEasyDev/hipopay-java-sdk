package main.java.com.hipopay.api;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;

public class Base {
    boolean isHK;
    private boolean isCNY;

    Base() {
        this.isHK = false;
        this.isCNY = false;
    }

    Base(boolean isCNY) {
        this.isHK = false;
        this.isCNY = isCNY;
    }

    Base(boolean isHK, boolean isCNY) {
        this.isHK = isHK;
        this.isCNY = isCNY;
    }


    List<NameValuePair> __isCNY(List<NameValuePair> params) {
        if(this.isCNY) {
            params.add(new BasicNameValuePair("is_rmb", "TRUE"));
        }
        return params;
    }

    public void getBill(List<NameValuePair> params) {
        //TODO:
    }

    public void getPayment(List<NameValuePair> params) throws IOException {
        Request req = new Request("/payment", params);
        req.requestGet();
    }

    public void refund(List<NameValuePair> params) throws IOException{
        Request req = new Request("/refund", params);
        req.requestPost();
    }

    public void getRefund(List<NameValuePair> params) throws IOException {
        Request req = new Request("/refund", params);
        req.requestGet();
    }
}
