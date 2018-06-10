package com.bookstore.utils;

public class ISBNUtil {
	public static boolean isISBN(String isbn) {
		isbn = isbn.replace("-", "");
		String frontStr = isbn.substring(0, isbn.length() - 1);// 除去最后一位的isbn子串
		char lastChar = isbn.substring(isbn.length() - 1).charAt(0);// isbn的最后一位
		boolean isNum = frontStr.matches("[0-9]+");
		if (!isNum || !(frontStr.length() == 12)) {//长度和其他字符校验
			return false;
		}
		char[] temp = frontStr.toCharArray();
		int sum = 0;
		if (frontStr.length() == 12) {
			String str = isbn.substring(0, 3);// 前三位
			if (!(str.equals("978") || str.equals("979"))) {
				return false;
			}
			//计算校验位
			for (int i = 0; i < 12; i++) {
				int dd = temp[i] - '0';
				if (i % 2 == 0) {
					sum += 1 * dd;
				} else {
					sum += 3 * dd;
				}
			}
			int n = sum % 10;
			char checkBit = '0';
			if (n != 0) {
				checkBit = (char) (10 - n + '0');
			}
			if (lastChar == checkBit) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
