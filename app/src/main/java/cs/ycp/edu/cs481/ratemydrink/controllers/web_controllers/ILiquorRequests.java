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

    /**
     * retrives a Liquor object from the database with the given id
     *
     * @param action the "action" parameter in the URL (should always be 'getLiquor'
     * @param id the "id" parameter in the URL, should be the id of the Liquor object to be retrived from the database
     * @return the Liquor object with the given id
     */
    @GET("/")
    Liquor get(@Query("action") String action ,@Query("id") int id);

    /**
     * POSTs a Liquor object to the database
     *
     * @param newLIQUOR the Liquor object to be added to the datatbase
     * @return the Liquor object that was just added to the database
     */
    @Headers({"Content-Type: application/json"})
    @POST("/backend/?action=addLiquor")
    Liquor post(@Body Liquor newLIQUOR);

}
