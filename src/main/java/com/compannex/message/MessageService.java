package com.compannex.message;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageService {
	
	private ResourceBundle messages;
	
	public MessageService() {
		Locale currentLocale = new Locale("en", "US");
		messages = ResourceBundle.getBundle("emails", currentLocale);	
	}
	
	public String getString(String key, Object... args) {
		String msg = messages.getString(key);
		if (msg != null) {
			msg = String.format(msg, args);
		}
		return msg;
	}

}
