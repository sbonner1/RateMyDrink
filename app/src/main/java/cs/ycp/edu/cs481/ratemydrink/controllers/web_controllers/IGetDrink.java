package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Drink;

import retrofit.http.GET;

/**
 * Created by Josh on 2/24/2015.
 */
public interface IGetDrink {

    @GET("")
    Drink getDrink();

}
