package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Model.User;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import cs.ycp.edu.cs481.ratemydrink.controllers.UserAdapter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * An AsyncTask to register a new user with the backend server.
 */
public class RegisterUserAsync extends AsyncTask<User, Void, User> {

    @Override
    protected User doInBackground(User... params) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserAdapter())
                .create();

        IUserRequests registerUser = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("retrofit", message);
                    }
                })
                .build()
                .create(IUserRequests.class);

        return registerUser.register(params[0]);
    }
}
