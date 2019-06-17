package com.springcloud.common;

import java.util.Date;

public class UploadUtils {

	public static String getFileName() {
		String fileName = null;
		int num = (int) (Math.random() * 1000 + 1);
		String tempNum = "000" + num;
		tempNum = tempNum.substring(tempNum.length() - 4);

		Date date = new Date();
		fileName = date.getTime() + tempNum;

		return fileName;
	}

	public static String getExendedName(String fileName) {
		if (fileName == null || fileName.length() == 0) {
			return null;
		}

		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			return null;
		}
		return fileName.substring(index);
	}

}
