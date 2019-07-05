package main.java.com.hipopay.example;

import main.java.com.hipopay.api.HipopaySignature;

import java.util.HashMap;
import java.util.Map;

public class CallBackVerify {
    public static void main(String[] args) throws Exception {
        String signature = "WxOfFD6o4RTntf6sActNTstHAX1zbw6s9s20ebwaOB1KpcgoCk8riWUZOKyUmPHfO2tMqJDhZHWlVzeTZivm3vhgrdqDVgIHmm15lxlybCi7+/wqgntO/lds91IEuKxZQmkZsrnkwrfEd4ktpf5YtEcZbBzD+cTnQb8hwBvkGNc22+j3RBrNLYSCFlKvCLWBQ6n9jAwZtPqOVH/opiR2mwtmNcA30SFz2GssMFY89PsYppVb9Gi3FUYlWkytLtFaiHaHmR22URo52bxuCuvE1QUbtguQrYMAHPP+PT0/f2mzRZmEcgpvbtX3bXuj0xm4l37yZ7QCOr5ZjB1eNwW44w==";
        String timestamp = "1562309330";

        Map<String, Object> response = new HashMap<String, Object>();

        response.put("status", "PAID");
        response.put("trade_type", "NATIVE");
        response.put("exchange_rate", "1.0000000000");
        response.put("payment_no", "2019070514445292733433289");
        response.put("currency", "HKD");
        response.put("amount", "1.50");
        response.put("trade_time", "2019-07-05 14:44:53");
        response.put("finish_time", "2019-07-05 14:45:23");
        response.put("pay_currency", "CNY");
        response.put("pay_amount", "1.32");
        response.put("out_trade_id", "132964985901940736");
        response.put("settle_currency", "HKD");

        Boolean result = HipopaySignature.verify(response, signature, timestamp);
        System.out.println(result);
    }
}
