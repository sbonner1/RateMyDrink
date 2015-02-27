package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Drink;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * A simple interface using the Retrofit api to make RESTful requests to get a Beer object from server-sides' database
 */
public interface IGetBeer {

    @GET("/getDrink")
    Drink get(@Query("id") int id);

}
