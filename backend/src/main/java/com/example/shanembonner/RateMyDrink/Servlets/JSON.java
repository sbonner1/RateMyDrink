package com.example.shanembonner.RateMyDrink.Servlets;

/**
 * Created by shanembonner on 2/10/15.
 */

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Class to provide access to a singleton {@link ObjectMapper} instance,
 * for converting model objects to/from JSON format.
 */
public class JSON {
    private static final ObjectMapper theObjectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return theObjectMapper;
    }

}
