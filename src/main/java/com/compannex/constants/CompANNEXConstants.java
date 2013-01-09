package com.compannex.constants;

public interface CompANNEXConstants {

	public static final String COMPANNEX = "CompANNEX";
	
	/**
     * Servlet context property for remembering if initialization was
     * successful.
     */
    public static final String COMPANNEX_INIT_SUCCESSFUL = COMPANNEX + " initialization is successful.";
    
    public static final int NEWS_LIMIT = 3;
    
    public static final int FEEDBACKS_LIMIT = 2;
    
    public static final int DEFAULT_LANGUAGE = 1;
    
    public static final int MAX_FILE_SIZE = 20 * 1024;
    
    public static final String COMPANNEX_COOKIE_NAME = "COMPANNEX";
    
    public static final String COOKIE_DELIM = "#";
    
    public static final int COOKIE_EXPIRE = 7*24*60*60;
    
    public static final String[] EXTENSIONS = {".jpeg", ".jpg", ".png", ".gif"};
    
}
