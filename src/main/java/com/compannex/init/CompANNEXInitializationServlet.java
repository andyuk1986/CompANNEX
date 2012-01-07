package com.compannex.init;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.compannex.constants.CompANNEXConstants;
import com.compannex.dao.IndustryDao;

/**
 * Bootstrap servlet to start up Spring's root WebApplicationContext, initalize
 * beans and configure log4j.
 */
public class CompANNEXInitializationServlet extends ContextLoaderServlet implements CompANNEXConstants {

    /**
     * instance for logging.
     */
    private Logger logger = null;

    /**
     * The spring web application context.
     */
    private WebApplicationContext webApplicationContext = null;

    /**
     * Initialize the root web application context, monitoring, log4j and tiling processing thread.
     */
    public final void init() {
        try {
            // Configure log4j before anyone accesses log4j
            initLogging();
            super.init();

            // Remember spring web application context
            webApplicationContext = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(getServletContext());

            logger.debug(COMPANNEX_INIT_SUCCESSFUL);

            // Remember successful initialization.
            getServletContext().setAttribute(COMPANNEX_INIT_SUCCESSFUL,
                    Boolean.TRUE);

            doTest();
            
            // CHECKSTYLE_OFF
            // all exceptions have to be catched.
        } catch (Throwable e) {
            // CHECKSTYLE_ON
            final String message = "Problem to initialize the application: "
                    + e.getMessage();
            if (logger != null) {
                logger.debug(message, e);
                logger.error(message);
            } else {
                // CHECKSTYLE_OFF
                // Last chance to report error.
                System.err.println(message);
                // CHECKSTYLE_ON
            }

            if (getContextLoader() != null) {
                // Spring has to be initialized before it can be shut down.
                getContextLoader().closeWebApplicationContext(
                        getServletContext());
            }

            // Remember failed initialization.
            getServletContext().setAttribute(COMPANNEX_INIT_SUCCESSFUL,
                    Boolean.FALSE);

            //throw new ServletException(e);

            System.exit(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void destroy() {
        logger.debug("Stopping " + COMPANNEX + " Application.");
        LogManager.shutdown();
        super.destroy();
    }

    /**
     * Returns the spring application context.
     *
     * @return spring application context.
     */
    protected WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    /**
     * Initialize log4j.
     */
    private void initLogging() {
        logger = Logger.getLogger(getClass().getName());
        logger.info("Logging initialized");
    }
    
    private void doTest() {
    	IndustryDao indDao = (IndustryDao)getWebApplicationContext().getBean("industryDao");
    	indDao.getIndustryById(1);
    }

}
