package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Drink;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Josh on 5/8/2015.
 */
public interface IFavoriteRequests {

    @GET("/backend/?action=getFavoritesList")
    Drink[] getFavoritesList(@Query("id") int userId);

    @GET("/backend/?action=getDrink")
    Drink getFavoriteDrink(@Query("id") int id);

}
