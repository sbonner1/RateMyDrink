package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import Model.Comment;

import java.security.InvalidParameterException;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;

/**
 * An AsyncTask to asynchronously make a GET request to the database for an array of Comment objects.
 */
public class GetCommentsAsync extends AsyncTask<Integer, Void, Comment[]> {
    @Override
    protected Comment[] doInBackground(Integer... params) {
        if(params.length < 3){
            throw new InvalidParameterException("GetCommentsAsync.execute requires 3 parameters.");
        }

        ICommentRequests getCommentsService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .build()
                .create(ICommentRequests.class);

        return getCommentsService.getComments(params[0], params[1] ,params[2]);
    }
}
