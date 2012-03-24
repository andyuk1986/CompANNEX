package com.compannex.properties;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.compannex.exceptions.CompANNEXRuntimeException;

/**
 * Abstract handler for property files.
 */
public abstract class PropertyHandler implements InitializingBean,
        ResourceLoaderAware {

    /**
     * Reference to the logging-system.
     */
    private static final Logger LG = Logger.getLogger(PropertyHandler.class
            .getName());

    /**
     * if failure to find the property resource should be ignored. True is
     * appropriate if the properties file is completely optional. Default is
     * "false".
     */
    private boolean ignoreResourceNotFound = true;

    /**
     * properties.
     */
    private Properties properties;

    /**
     * resource loader.
     */
    private ResourceLoader resourceLoader;

    /**
     * locations of properties file.
     */
    private String[] locations;

    /**
     * Returns locations.
     *
     * @return locations.
     */
    public String[] getLocations() {
        return this.locations;
    }

    /**
     * Sets locations.
     *
     * @param locations new value for locations.
     */
    public void setLocations(final String[] locations) {
        this.locations = locations;
    }

    /**
     * Converts a property value into an integer value.
     *
     * @param key key of property.
     * @return converted value.
     */
    protected int getInt(final String key) {
        final String value = getProperty(key);
        return Integer.parseInt(value);
    }

    /**
     * Converts a property into a char value. Only the first character of the
     * property value is considered.
     *
     * @param key key of property.
     * @return converted char value.
     */
    protected char getChar(final String key) {
        return getProperty(key).charAt(0);
    }

    /**
     * Returns a property value.
     *
     * @param key key of property.
     * @return value of property.
     */
    protected String getProperty(final String key) {
        String propValue = properties.getProperty(key);
        if (propValue == null) {
            throw new CompANNEXRuntimeException("The property: " + key + " is missing in configuration!");
        }
        return propValue;
    }

    /**
     * Sets the resource loader for reading the properties.
     *
     * @param resourceLoader the resource loader.
     */
    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Set if failure to find the property resource should be ignored. True is
     * appropriate if the properties file is completely optional. Default is
     * "false".
     *
     * @param ignoreResourceNotFound true, if failure to find the property resource should be
     *                               ignored
     */
    public void setIgnoreResourceNotFound(final boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }

    /**
     * Validates whether the given URL is valid or not.
     *
     * @param url the URL to validate.
     * @return returns the boolean representing whether the URL is valid or not.
     */
    public boolean validateUrl(final String url) {
        boolean isValid = true;
        //should be replaced with reg exp validation.
        return isValid;
    }

    /**
     * Initializes the property object.
     *
     * @throws Exception if error occurs
     */
    public void afterPropertiesSet() throws Exception {

        if (this.locations != null && this.locations.length > 0) {
            properties = new Properties();
            for (int i = 0; i < this.locations.length; i++) {
                final String propertyFilename = locations[i];
                try {
                    final Resource resource = resourceLoader
                            .getResource(propertyFilename);
                    properties.load(resource.getInputStream());
                } catch (IOException ex) {
                    if (this.ignoreResourceNotFound) {
                        LG.info("Could not load properties from "
                                + propertyFilename + ": " + ex.getMessage());
                    } else {
                        throw ex;
                    }
                }
            }
            if (properties.isEmpty()) {
                LG.error("Could not load any properties");
            }
        } else {
            throw new IllegalArgumentException(
                    "locations of properties file must be defined!");
        }

        resourceLoader = null;
    }
}
