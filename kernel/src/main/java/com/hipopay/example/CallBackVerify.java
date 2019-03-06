package main.java.com.hipopay.example;

import main.java.com.hipopay.api.HipopaySignature;

import java.util.HashMap;
import java.util.Map;

public class CallBackVerify {
    public static void main(String[] args) throws Exception {
        String signature = "Vw6tn0JS9ArRobUlnbX6ksTp437A9Rm1aHeWXuRYmoZX9526UKf4piMrUV/+zlB2VyMhhKNm164UKFIyLcggPrqOTsr5W3iMXtIOrzaXm5Jvh5/3GCAsdpQrnv6hUMuK7dUTysvZjhmxaTwKiXpEuwO7PKT5tGnMCGbBfq/2VZNecl/xQflz/a7tI7TQAEcJqbeZKYUlpewK17r5l0+OoQAHZqoHRrsG9IwN9YJ2VCkfO6S4nJobk6Pt9PVOPiS5MBLg54fxbjAfUXfXM8rZ1zcoTyJQZ7NCfb4lsEYQQgGzBdmi7GusnlDxEz7xXm4XnxH2me+A95Rr/IKBUsi2RA==";
        String timestamp = "1551840374";

        Map<String, Object> response = new HashMap<String, Object>();


        //
        // "status": "PAID",
        // "trade_type": "WAP",
        // "pay_amount": "0.33",
        // "out_trade_id": "201800353",
        // "currency": "USD",
        // "pay_currency": "CNY",
        // "exchange_rate": "1.00000000",
        // "finish_time": "2019-03-05 22:12:46",
        // "amount": "0.05",
        // "payment_no": "2019030522123448477453150",
        // "trade_time": "2019-03-05 22:12:35,
        // "Timestamp": "1551840374",
        // "Signature": "Vw6tn0JS9ArRobUlnbX6ksTp437A9Rm1aHeWXuRYmoZX9526UKf4piMrUV/+zlB2VyMhhKNm164UKFIyLcggPrqOTsr5W3iMXtIOrzaXm5Jvh5/3GCAsdpQrnv6hUMuK7dUTysvZjhmxaTwKiXpEuwO7PKT5tGnMCGbBfq/2VZNecl/xQflz/a7tI7TQAEcJqbeZKYUlpewK17r5l0+OoQAHZqoHRrsG9IwN9YJ2VCkfO6S4nJobk6Pt9PVOPiS5MBLg54fxbjAfUXfXM8rZ1zcoTyJQZ7NCfb4lsEYQQgGzBdmi7GusnlDxEz7xXm4XnxH2me+A95Rr/IKBUsi2RA==

        response.put("status", "PAID");
        response.put("trade_type", "WAP");
        response.put("exchange_rate", "1.00000000");
        response.put("payment_no", "2019030522123448477453150");
        response.put("currency", "USD");
        response.put("amount", "0.05");
        response.put("trade_time", "2019-03-05 22:12:35");
        response.put("finish_time", "2019-03-05 22:12:46");
        response.put("pay_currency", "CNY");
        response.put("pay_amount", "0.33");
        response.put("out_trade_id", "201800353");

        Boolean result = HipopaySignature.verify(response, signature, timestamp);
        System.out.println(result);
    }
}
