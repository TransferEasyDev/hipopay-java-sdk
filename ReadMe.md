
![HipoPay](https://s.transfereasy.com/logo/hipopay-github.png)

# 全渠道跨境收款

* [官方网站](https://www.hipopay.com/)
* [体验中心](https://www.hipopay.com/Demo/index)
* [商务合作](https://www.hipopay.com/Home/cooperate)
* [开发文档](https://www.hipopay.com/Document/newDoc?page=index)


## 结构

```$xslt
└── src
    └── main
        └── java
            └── com
                └── transfereasy 
                    ├── api       // API 
                    ├── example   // DEMO
                    ├── resources // 配置文件
                    └── tool      // 工具 
```

## 示例
```
/**
 * 配置文件
 * 
 * src
 *  └── main
 *      └── java
 *          └── com
 *              └── transfereasy 
 *                  └── resources
 *                      └── config.properties
 */



MERCHANT_NO = your_merchant_no
HP_HOST = https://testapi.wisecashier.com
VERSION = 1.0

PRIVATE_KEY_PATH = /your_project_path/hipopay-java-sdk/kernel/key/private.key
MERCHANT_MINI_APPID = 去微信公众平台, 登录小程序账号, 进入开发设置获取
MERCHANT_APPID = 去微信开放平台, 进入应用管理获取

```

```java
package main.java.com.hipopay.example.wechatCn;

import main.java.com.hipopay.api.WechatCN;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsumerScanWeb {
    public static void main(String[] args) throws IOException {
        /*
            'out_trade_id'   # 商户交易流水号 Y
            'amount'         # 支付单金额，单位为元，精度最多小数点后两位(如果是JPY和KRW，单位为分) Y
            'currency'       # 支付单结算币种
            'product_info'   # 商品信息
            'client_ip'      # 客户端设备IP地址
            'notify_url'     # 异步通知地址
        */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("out_trade_id", "your_trade_id"));
        params.add(new BasicNameValuePair("amount", "100"));
        params.add(new BasicNameValuePair("currency", "HKD"));
        params.add(new BasicNameValuePair("product_info", "test product"));
        params.add(new BasicNameValuePair("client_ip", "0.0.0.0"));
        params.add(new BasicNameValuePair("notify_url", "your_notify_url"));
        //$isCNY 是否采用人民币(CNY)计价，取值"TRUE"/"FALSE"，默认值为"FALSE"
        WechatCN api = new WechatCN(false);
        api.consumerScanWeb(params);
    }
}


```

## 依赖

* [lombok.jar](https://projectlombok.org/downloads/lombok.jar)
* [apache httpclient 4.5.6](http://mirror.bit.edu.cn/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.5.6-bin.tar.gz)
* [java-json.jar](http://www.java2s.com/Code/JarDownload/java-json/java-json.jar.zip)
* [gson.jar 2.8.5](https://search.maven.org/artifact/com.google.code.gson/gson/2.8.5/jar)

