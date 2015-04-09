package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.MixedDrink;

import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * A simple interface using the Retrofit api to make RESTful requests to make a POST request for a new MixedDrink Object
 */
public interface IPostNewMixedDrink {

    @Headers({"Content-Type: application/json"})
    @POST("/backend/?action=addMixedDrink")
    MixedDrink post(@Body MixedDrink mixedDrink);

}
