package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import com.rateMyDrink.modelClasses.Comment;

import java.security.InvalidParameterException;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;

/**
 * An AsyncTask to asynchronously make a GET request to the database for an array of Comment objects.
 */
public class GetCommentsAsync extends AsyncTask<Integer, Void, Comment[]> {
    @Override
    protected Comment[] doInBackground(Integer... params) {
        if(params.length < 2){
            throw new InvalidParameterException("GetCommentsAsync.execute requires 2 parameters.");
        }

        ICommentRequests getCommentsService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .build()
                .create(ICommentRequests.class);

        return getCommentsService.getComments(params[0] ,params[1]);
    }
}
