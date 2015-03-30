package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Liquor;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * A simple interface using the Retrofit api to make RESTful requests to get a Liquor object from server-sides' database
 */
public interface IGetLiquor {

    @GET("/")
    Liquor get(@Query("action") String action ,@Query("id") int id);

}
