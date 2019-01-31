package main.java.com.hipopay.api;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.List;

public class WechatCN extends Base {

    public WechatCN() {
        super();
    }

    public WechatCN(boolean isCNY) {
        super(isCNY);
    }

    public void appPay(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/wechatpay/app/payment", params);
        req.requestPost();
    }

    public void mpPay(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/wechatpay/mp/payment", params);
        req.requestPost();
    }

    public void miniProgramPay(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/wechatpay/mini_program/payment", params);
        req.requestPost();
    }

    public void consumerScanWeb(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/wechatpay/web/payment", params);
        req.requestPost();
    }

    public void consumerScanDevice(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/wechatpay/qrcode/payment", params);
        req.requestPost();
    }

    public void merchantScanConsumer(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/wechatpay/barcode/payment", params);
        req.requestPost();
    }

    public void getRate(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/wechatpay/rate", params);
        req.requestGet();
    }

}
