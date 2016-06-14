package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import Model.Drink;
import Model.Favorite;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Josh on 5/8/2015.
 */
public interface IFavoriteRequests {

    @GET("/backend/?action=getFavoritesList")
    Drink[] getFavoritesList(@Query("id") int userId);

    @GET("/backend/?action=getDrink")
    Drink getFavoriteDrink(@Query("id") int id);

    @POST("/backend/?action=addFavorite")
    Favorite postNewFavorite(@Body Favorite newFavorite);

}
