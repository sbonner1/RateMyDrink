package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Comment;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 *  A simple interface using the Retrofit api (documentation for Retrofit can be found here: http://square.github.io/retrofit/ )
 * to make RESTful requests GETting and array of Comments from the backend.
 */
public interface ICommentRequests {

    /**
     * GETs an array of Comment objects from the database between a start and end indices.
     *
     * @param start start index
     * @param end end index
     * @return an array of Comments from the database between indices start and end.
     */
    @GET("/backend/?action=getComments")
    Comment[] getComments(@Query("start") int start, @Query("end") int end);

}
