package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Drink;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * A simple interface using the Retrofit api (documentation for Retrofit can be found here: http://square.github.io/retrofit/ )
 * to make RESTful requests for POSTing and GETting Beer objects from the backend.
 */
public interface IBeerRequests {

    /**
     * retrieves the Beer object with the given id from the database
     *
     * @param action the "action" parameter in the URL (should always be 'getBeer')
     * @param id the id of the Beer object
     * @return the Beer object of the given id
     */
    @GET("/")
    Beer get(@Query("action") String action,@Query("id") int id);

    /**
     * POSTs a new Beer object into the database
     *
     * @param newBeer the new beer to be added to the database
     * @return the Beer object thet was just added to the database
     */
    @Headers({"Content-Type: application/json"})
    @POST("/backend/?action=addBeer")
    Drink post(@Body Beer newBeer);

}
