package cs.ycp.edu.cs481.ratemydrink;

import retrofit.RestAdapter;

/**
 * A singleton instance of the Retrofit RestAdapter.Builder for making RESTful request to a server
 */
public class RETROFIT {

    private static final RestAdapter.Builder restAdapter = new RestAdapter.Builder();

    public static RestAdapter.Builder getRestAdapterBuilder() {
        return restAdapter;
    }

}
