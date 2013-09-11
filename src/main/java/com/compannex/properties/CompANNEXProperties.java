package com.compannex.properties;

import com.compannex.exceptions.CompANNEXRuntimeException;

/**
 * Defines application configuration properties.
 */
public interface CompANNEXProperties {

	/**
	 * @return location where Logo Files are stored to be mounted
	 */
	String getLogosPath();

	/**
	 * @return URI of logo in tomcat 
	 * 
	 */
	String getLogoURI();
	

	/**
	 * @return host name of server 
	 * 
	 */
	String getHostName();
	
	/**
	 * Check the properties for valid data types.
	 * 
	 * @throws CompANNEXRuntimeException
	 *             when data type of a property is not valid.
	 */
	void checkProperties() throws CompANNEXRuntimeException;

}
