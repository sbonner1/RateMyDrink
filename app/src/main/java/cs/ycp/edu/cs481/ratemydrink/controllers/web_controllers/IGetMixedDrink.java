package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.MixedDrink;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * A simple interface using the Retrofit api to make RESTful requests to get a MixedDrink object from server-sides' database
 */
public interface IGetMixedDrink {

    @GET("/getMixedDrink")
    MixedDrink get(@Query("id") int id);

}
