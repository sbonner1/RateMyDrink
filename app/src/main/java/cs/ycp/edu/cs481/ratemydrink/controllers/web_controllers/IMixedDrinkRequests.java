package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.MixedDrink;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * A simple interface using the Retrofit api (documentation for Retrofit can be found here: http://square.github.io/retrofit/ )
 * to make RESTful requests for POSTing and GETting MixedDrink objects from the backend.
 */
public interface IMixedDrinkRequests {

    /**
     * retrieves a MixedDrink object with the given id
     *
     * @param id the id of the MixedDrink
     * @return the MixedDrink object with the given id
     */
    @GET("/backend/?action=getMixedDrink")
    MixedDrink get(@Query("id") int id);

    /**
     * POSTs a MixedDrink object to the database
     *
     * @param mixedDrink the MixedDrink object to be POSTed to the database
     * @return the MixedDrink object that was just POSTed to the database
     */
    @Headers({"Content-Type: application/json"})
    @POST("/backend/?action=addMixedDrink")
    MixedDrink post(@Body MixedDrink mixedDrink);

}
