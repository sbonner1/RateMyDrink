package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rateMyDrink.modelClasses.Comment;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import cs.ycp.edu.cs481.ratemydrink.controllers.CommentAdapter;
import retrofit.converter.GsonConverter;

/**
 * An AsyncTask to post a new Comment object to the backend database
 */
public class PostNewCommentAsync extends AsyncTask<Comment, Void, Comment> {
    @Override
    protected Comment doInBackground(Comment... params) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Comment.class, new CommentAdapter())
                .create();

        ICommentRequests getCommentsService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .setConverter(new GsonConverter(gson))
                .build()
                .create(ICommentRequests.class);

        return getCommentsService.postComment(params[0]);
    }
}
