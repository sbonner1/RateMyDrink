package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import Model.Comment;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
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
    Comment[] getComments(@Query("id") int id, @Query("startIndex") int start, @Query("endIndex") int end);

    /**
     * POSTs a new Comment object to the database.
     *
     * @param newComment the new comment.
     * @return the comment just POSTed to the database in JSON format.
     */
    @POST("/backend/?action=addComment")
    Comment postComment(@Body Comment newComment);

}
