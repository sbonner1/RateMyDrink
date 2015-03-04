package com.example.shanembonner.RateMyDrink.Servlets;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myServletApi",
        version = "v1",
        resource = "myServlet",
        namespace = @ApiNamespace(
                ownerDomain = "Servlets.RateMyDrink.shanembonner.example.com",
                ownerName = "Servlets.RateMyDrink.shanembonner.example.com",
                packagePath = ""
        )
)
public class MyServletEndpoint {

    private static final Logger logger = Logger.getLogger(MyServletEndpoint.class.getName());

    /**
     * This method gets the <code>MyServlet</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>MyServlet</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getMyServlet")
    public MyServlet getMyServlet(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getMyServlet method");
        return null;
    }

    /**
     * This inserts a new <code>MyServlet</code> object.
     *
     * @param myServlet The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertMyServlet")
    public MyServlet insertMyServlet(MyServlet myServlet) {
        // TODO: Implement this function
        logger.info("Calling insertMyServlet method");
        return myServlet;
    }
}