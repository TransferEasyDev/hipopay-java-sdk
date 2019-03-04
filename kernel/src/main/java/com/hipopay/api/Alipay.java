package main.java.com.hipopay.api;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;

public class Alipay extends Base {

    public Alipay() {
        super();
    }

    public Alipay(boolean isHK, boolean isCNY) {
        super(isHK, isCNY);
    }


    private List<NameValuePair> __isHK(List<NameValuePair> params) {
        if(this.isHK) {
            params.add(new BasicNameValuePair("hk_wallet", "true"));
        }
        return params;
    }

    public void appPay(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        Request req = new Request("/alipay/app/payment", params);
        req.requestPost();
    }

    public void wapPay(List<NameValuePair> params) throws IOException {
        params = this.__isCNY(params);
        params = this.__isHK(params);
        Request req = new Request("/alipay/wap/payment", params);
        req.requestPost();
    }

    public void comsumerScanWeb(List<NameValuePair> params) throws IOException {
        params = this.__isHK(params);
        Request req = new Request("/alipay/web/payment", params);
        req.requestPost();
    }

    public void consumerScanMerchant(List<NameValuePair> params) throws IOException {
        params = this.__isHK(params);
        Request req = new Request("/alipay/qrcode/payment", params);
        req.requestPost();
    }

    public void merchantScanConsumer(List<NameValuePair> params) throws IOException {
        params = this.__isHK(params);
        Request req = new Request("/alipay/barcode/payment", params);
        req.requestPost();
    }

    public void getRate(List<NameValuePair> params) throws IOException {
        Request req = new Request("/alipay/rate", params);
        req.requestGet();
    }


}
