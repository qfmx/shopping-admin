package com.springcloud.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MyCode {
	// 验证码字符数组
		private char code[] = {'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
		//验证码长度
		private static final int WIDTH = 50;
		// 验证码宽度
		private static final int HEIGH = 20;
		// 验证码字符长度
		private static final int LENGTH = 5;
		@RequestMapping(value = "/validateCode")
		public void validateCode(HttpServletRequest request,HttpServletResponse respone) throws ServletException,IOException {
			// 设置响应报头信息
			respone.setHeader("Pragma", "No-cache");
			respone.setHeader("Cache-Control", "no-cache");
			respone.setDateHeader("Expires", 0);
			
			//设置MIME类型
			respone.setContentType("image/jpeg");
			BufferedImage image = new BufferedImage(WIDTH, HEIGH, BufferedImage.TYPE_INT_RGB);
			Font mFont = new Font("Arial", Font.TRUETYPE_FONT, 18);
			Graphics g = image.getGraphics();
			Random rd = new Random();
			// 设置背景颜色
			g.setColor(new Color(rd.nextInt(55)+200,rd.nextInt(55)+200,rd.nextInt(55)+200));
			g.fillRect(0, 0, WIDTH, HEIGH);
			// 设置字体
			g.setFont(mFont);
			// 画边框
			g.setColor(Color.black);
			g.drawRect(0, 0, WIDTH -1, HEIGH -1);
			// 产生随机验证码
			String result = "";
			for (int i = 0; i < LENGTH; i++) {
				result += code[rd.nextInt(code.length)];
			}
			// 将验证码以指定的名字放到前台
			HttpSession se = request.getSession();
			se.setAttribute("code", result);
			// 画验证码
			for (int i = 0; i < result.length(); i++) {
				g.setColor(new Color(rd.nextInt(200),rd.nextInt(200),rd.nextInt(200)));
				g.drawString(result.charAt(i)+"", 12*i+1, 16);
			}
			// 随机生成4条干扰线
			for (int i = 0; i < 4; i++) {
				g.setColor(new Color(rd.nextInt(200),rd.nextInt(200),rd.nextInt(200)));
				int x1 = rd.nextInt(WIDTH);
				int x2 = rd.nextInt(WIDTH);
				int y1 = rd.nextInt(HEIGH);
				int y2 = rd.nextInt(HEIGH);
				g.drawLine(x1, x2, y1, y2);
			}
			// 释放资源
			g.dispose();
			try {
				ServletOutputStream os = respone.getOutputStream();
				ImageIO.write(image,"JPEG",os);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
