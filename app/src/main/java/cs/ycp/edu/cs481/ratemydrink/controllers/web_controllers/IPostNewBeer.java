package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Drink;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * A simple interface using the Retrofit api to make RESTful requests to make a POST request for a new Beer Object
 */
public interface IPostNewBeer {

    @Headers({"Content-Type: application/json"})
    @POST("/backend/?action=addDrink")
    Drink post(@Body String newBeer);

}
