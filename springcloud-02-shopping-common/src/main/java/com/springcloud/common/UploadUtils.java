package com.springcloud.common;

import java.util.Date;
/**
 * 文件上传的工具类
 * @author Ya
 *
 */
public class UploadUtils {
	/**
	 * 获得新文件的名称(毫秒数 + 4位随机数)
	 * @return 返回新文件名的名称
	 */
	public static String getFileName() {
		// 用于接收新文件名
		String fileName = null;
		// 获得1~1000之间的随机数
		int num = (int) (Math.random() * 1000 + 1);
		// 将随机数设置为4位
		String tempNum = "000" + num;
		tempNum = tempNum.substring(tempNum.length() - 4);
		// 获得当前系统时间
		Date date = new Date();
		// date.getTime() 获得当前系统时间的时间戳+tempNum 4位随机数
		fileName = date.getTime() + tempNum;

		return fileName;
	}
	/**
	 * 获得文件的扩展名
	 * @param fileName 文件名
	 * @return 成功返回文件扩展名，否则返回null
	 */
	public static String getExendedName(String fileName) {
		if (fileName == null || fileName.length() == 0) {
			return null;
		}
		// 获得文件名中最后出现.最后一次出现的位置
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			return null;
		}
		// 返回文件扩展名
		return fileName.substring(index);
	}

}
