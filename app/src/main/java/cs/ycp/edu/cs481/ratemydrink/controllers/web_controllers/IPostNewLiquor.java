package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Liquor;

import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * A simple interface using the Retrofit api to make RESTful requests to make a POST request for a new Liquor Object
 */
public interface IPostNewLiquor {

    @Headers({"Content-Type: application/json"})
    @POST("/backend/?action=addLiquor")
    Liquor post(@Body Liquor newLIQUOR);

}
