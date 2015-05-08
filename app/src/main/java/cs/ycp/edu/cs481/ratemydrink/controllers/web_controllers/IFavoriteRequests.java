package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Favorite;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Josh on 5/8/2015.
 */
public interface IFavoriteRequests {

    @GET("/backend/?action=getFavoritesList")
    Favorite[] getFavoritesList(@Query("id") int userId);

}
