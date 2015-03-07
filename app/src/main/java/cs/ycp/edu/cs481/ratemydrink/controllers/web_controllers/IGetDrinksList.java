package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Drink;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.GET;

/**
 * An interface using the Retrofit api to make RESTful request to the server-side database for a list of drinks
 */
public interface IGetDrinksList {

    @GET("/getDrinkList")
    List<Drink> getDrinkList(); //TODO: determine how we decide on which drink type to get

}
