package com.amosli.rp;

import java.util.Date;

public class Calculator {
	@SuppressWarnings("deprecation")
	public static int calculateRPByName(String paramString) {
		Date dt = new Date();
		int result = 0;
		for (int i = 0; i < paramString.length(); i++) {
			int para = paramString.charAt(i);
			result += para * 2 - dt.getYear() - dt.getDate();
		}
		result = Math.abs(result) % 100;

		String valueOf = String.valueOf(result).substring(0, 2);
		System.out.println(valueOf);
		return result;
	}
}