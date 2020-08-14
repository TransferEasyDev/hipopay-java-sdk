package main.java.com.hipopay.api;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.List;

public class WechatHK extends Base {

    public WechatHK() {
        super();
    }

    public void appPay(List<NameValuePair> params) throws IOException {
        Request req = new Request("/wechatpay/hk/app/payment", params);
        req.requestPost();
    }

    public void mpPay(List<NameValuePair> params) throws IOException {
        Request req = new Request("/wechatpay/hk/online/mp/payment", params);
        req.requestPost();
    }

    public void miniProgramPay(List<NameValuePair> params) throws IOException {
        Request req = new Request("/wechatpay/hk/mini_program/payment", params);
        req.requestPost();
    }

}
