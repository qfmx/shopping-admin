package com.springcloud.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.springcloud.common.AlipayConfig;
import com.springcloud.entity.BizContent;
import com.springcloud.entity.SysServiceProvide;;

@RestController
public class AlipayController {
	// 获得商户APPID
	public static String APP_ID = AlipayConfig.app_id;
	// 获得商户公钥
	public static String APP_PRIVATE_KEY = AlipayConfig.merchant_private_key;
	// 设置格式化格式为JSON
	public static String FORMAT = "json";
	// 获得字符编码格式
	public static String CHARSET = AlipayConfig.charset;
	// 获得商户公钥
	public static String ALIPAY_PUBLIC_KEY = AlipayConfig.alipay_public_key;
	// 获得签名方式为RSA2
	public static String SIGN_TYPE = AlipayConfig.sign_type;
	// 获得唯一标识UUID
	public static String uuid = UUID.randomUUID().toString().replaceAll("-", "");

	/**
	 * 支付接口的api
	 * 
	 * @param httpRequest  http请求
	 * @param httpResponse http响应
	 * @throws ServletException Servlet异常
	 * @throws IOException      IO异常
	 */
	@RequestMapping(value = "alipay_api")
	public void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String out_trade_no,
			String subject, Float total_amount) throws ServletException, IOException {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID,
				APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); // 获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		// alipayRequest.setReturnUrl("http://127.0.0.1:9001/goods/class/selectAllClass1?class1Id=2");
		alipayRequest.setReturnUrl("http://127.0.0.1:8848/demo/updateOrdersStatus.html");
		alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent("\"total_amount\":145.88");

		BizContent bizContent = new BizContent();
		bizContent.setOut_trade_no(out_trade_no);
		bizContent.setSubject(subject);
		bizContent.setProduct_code("FAST_INSTANT_TRADE_PAY");
		SysServiceProvide sysServiceProvide = new SysServiceProvide();
		sysServiceProvide.setSys_service_provider_id("2088511833207846");
		bizContent.setExtend_params(sysServiceProvide);
		String jsonString = JSON.toJSONString(bizContent);
		alipayRequest.setBizContent(jsonString);
		/*
		 * alipayRequest.setBizContent("{" +
		 * "    \"out_trade_no\":\"2010015589666070629\"," +
		 * "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
		 * "    \"total_amount\":1.88," + "    \"subject\":\"Iphone6 16G\"," +
		 * "    \"body\":\"Iphone6 16G\"," +
		 * "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","
		 * + "    \"extend_params\":{" +
		 * "    \"sys_service_provider_id\":\"2088511833207846\"" + "    }" + "  }");//
		 * 填充业务参数
		 */ String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
}
