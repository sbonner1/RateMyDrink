package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Liquor;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * A simple interface using the Retrofit api (documentation for Retrofit can be found here: http://square.github.io/retrofit/ )
 * to make RESTful requests for POSTing and GETting Liquor objects from the backend.
 */
public interface ILiquorRequests {

    @GET("/")
    Liquor get(@Query("action") String action ,@Query("id") int id);

    @Headers({"Content-Type: application/json"})
    @POST("/backend/?action=addLiquor")
    Liquor post(@Body Liquor newLIQUOR);

}
