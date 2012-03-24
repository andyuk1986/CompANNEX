package com.compannex.properties;

import com.compannex.exceptions.CompANNEXRuntimeException;


/**
 * Bean for CompANNEXProperties.
 */
public final class CompANNEXPropertiesBean extends PropertyHandler implements
        CompANNEXProperties {

    /**
     * key for the property of logo files path.
     */
    private static final String LOGO_PATH = "logoPath";

    /**
     * key for the property of logo files URI.
     */
    private static final String LOGO_URI = "logoURI";
    
    /**
     * {@inheritDoc}
     */
    public void checkProperties() throws CompANNEXRuntimeException {
        try {
            // Check properties
            getLogosPath();
            getLogoURI();
            
            // CHECKSTYLE_OFF
        } catch (final RuntimeException ex) {
            // CHECKSTYLE_ON
            throw new CompANNEXRuntimeException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getLogosPath() {
        return getProperty(LOGO_PATH);
    }
    
    /**
     * {@inheritDoc}
     */
    public String getLogoURI() {
        return getProperty(LOGO_URI);
    }
}
