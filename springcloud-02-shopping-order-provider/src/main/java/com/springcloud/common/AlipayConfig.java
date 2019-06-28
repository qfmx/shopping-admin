package com.springcloud.common;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101100662539";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMF8uv0Y8RjjEqwiw8m/xVCTCn+0Hz0ZMdyqapxcfy47F5de8gDjEWGVf6YzVBJsof0t6iyCpOZGILRm+ipKpGc4hZSOl+ctmhCDbFD6nBrlGHF/MyRjChslJM7WdTshFzZygK1zQy1B/V5BYMpiAfdfQ5+fa2Pg9Dv4j0F1oH/fuhs4jSz0tB0LBicpu1nbUnOfga0foSGxH5ugdUtp+/U+5ml0glxtlO3PeToLLLlDFNH+llY3ot81ov805uT5ZGAJd/GNi4xuVUkiJgG+9pgNCOuOYczx3+/QEj4K5nipnqTPB92M7YPtgox3TcILCgf5hdvEpgBuYugeYJ2n69AgMBAAECggEAWEOpzpV+yXXIHoOGMY2p1r3ajQuuCvsw8KU8ilyVVMAGklsulQi7/otrNdOJuwnAcXp/URpCJW/zodz3+wY6Tfb+ohphGo0r9txg/ZmXv/KfZTk+vZ8hhs7+MX+mCUJ44jfNL79VIO0rPazeA4wrUwcTH/AQc9P39BxaIkgQLlXxcRWPX/IgOVTZqAbfuUXqyUeaskjL2Iz79b9rtk8+Bv1x0MPoBokK0Qp2VS9rbXaSZmyUbd/MfsG1FjTNI6WCbFTjoCRcBU+fQjGs7dT/UEvkHzgjUUgptMJNkf0+jH6dZjJY/ibWaUrAy32eUqVSrO/2ecWjdEPVEy/5M9pV1QKBgQDkpfwzqL7yEzL5wrOP91MhqnLvsQj/L3LeZMotgVs01VndcKC5EseB4l6aDC/S3rfUVPOGd91z1d1GB3wLBrChA+zZxTuCkB2ToYMtruTDhch7pMIpbeiMroJeFf+B9hZNtkbPJD8UktO1tQ9k7lDVnxYvMkMGg28TDUOmXDWmHwKBgQCc2e7RpIos4YhDBX7nPMcVBUplfjRZW3aORPSz2lg8KLqxLWEpRny2snbEpeC0ZX+YTW88EfvVPMluLMgdMSgxPjzI4rpshh7yPzhNXCwF62aD5vrk0y+IyKbQu7amwAykaQwLNpjQECEdC1D73uKAtvv2IeG5y/goGW5VLj0nowKBgC4Ekhu7jKKf5pSJ8SW6e0q9SWCgWtUxMYc89cvuyvJ5NhrxPgfBldG4Q53xcfaYyJq9dj3MBg/4LQ6ZFwgJEqd1aMfzgCSo6Kr7yLD7ECOh21W3NTStYC5gOPDCsKfxZU/CrcGm4+M6Ic3iYPTJWg+EcyyQixjWfxZwhvsGd+21AoGAEQiFq9SPgNI5JvYY0unBwOGjM6Rh8PZCyK5qWIG0pUvTAlyoOYBDGOAGj9JntY90LMjz9HULgRup0EIitTDaaYaVuUcN8a0a/TmqVpRU34uxxU83HdRlTBhmLTLFYhRnCw+xVCJos+ecgPfyoneQ52N3t0H0rmWdHFWuqqdr0rsCgYApt/YQPBjFh7j7w4X9arKrVFcUygv8NpEXMIIBQO35ECzpBJGCHXhK3TEPFdwtGg62iEGKLi7R+i7xN4Hg1T+qRI5oiDgBtGW6ilzu3aU3aZp3tEmCTo63FcvCWBk1c8hN9iIND8XSt8jKpUPvgsYjrtEkaW/MWTuvcry6xWbhow==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjBfLr9GPEY4xKsIsPJv8VQkwp/tB89GTHcqmqcXH8uOxeXXvIA4xFhlX+mM1QSbKH9LeosgqTmRiC0ZvoqSqRnOIWUjpfnLZoQg2xQ+pwa5RhxfzMkYwobJSTO1nU7IRc2coCtc0MtQf1eQWDKYgH3X0Ofn2tj4PQ7+I9BdaB/37obOI0s9LQdCwYnKbtZ21Jzn4GtH6EhsR+boHVLafv1PuZpdIJcbZTtz3k6Cyy5QxTR/pZWN6LfNaL/NObk+WRgCXfxjYuMblVJIiYBvvaYDQjrjmHM8d/v0BI+CuZ4qZ6kzwfdjO2D7YKMd03CCwoH+YXbxKYAbmLoHmCdp+vQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

