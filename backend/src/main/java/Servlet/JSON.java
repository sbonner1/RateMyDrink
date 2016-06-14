package Servlet;

/**
 * Created by shanembonner on 2/10/15.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

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
