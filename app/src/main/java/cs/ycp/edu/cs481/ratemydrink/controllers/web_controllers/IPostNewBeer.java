package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Beer;

import retrofit.http.POST;

/**
 * A simple interface using the Retrofit api to make RESTful requests to make a POST request for a new Beer Object
 */
public interface IPostNewBeer {

    @POST("/?action=addDrink")
    void post(Beer newBeer);

}
