package com.compannex.util;

import java.io.File;

public class StringUtil {

	public static boolean equals(String str1, String str2) {
		
		if (str1 == null || str2 == null) return false;
		
		return str1.equals(str2);
		
	}
	
	public static boolean isBlank(String str1) {
		
		if (str1 == null || str1.equals("")) return true;
		return false;
	}
	
	public static String getString(Object obj) {
		if (obj == null) return "";
		
		return obj.toString();
	}
	
	public static String getExtension(String path) {
		if (path == null) return "";
			
		return path.substring(path.lastIndexOf("."));
	}
	
	public static String getFolderPath(String path) {
		if (path == null) path = "";
		
		if (!path.endsWith(File.separator)) path += File.separator;
		
		return path;
	}
}
