package com.compannex.util;

public class StringUtil {

	public static boolean equals(String str1, String str2) {
		
		if (str1 == null || str2 == null) return false;
		
		return str1.equals(str2);
		
	}
	
	public static boolean isBlank(String str1) {
		
		if (str1 == null || str1.equals("")) return true;
		return false;
	}
	
	
}
