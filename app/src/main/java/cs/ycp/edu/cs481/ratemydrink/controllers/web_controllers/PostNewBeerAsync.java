package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Drink;

import java.io.Reader;
import java.io.StringReader;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * A controller to asynchronously make a POST request add a new Beer object to the database.
 */
public class PostNewBeerAsync extends AsyncTask<Drink, Void, String> {
    @Override
    protected String doInBackground(Drink... params) {

        Gson gson = new GsonBuilder()
                //.registerTypeAdapter(Drink.class, new DateTypeAdapter())
                .create();

        //Log.d("GSON", gson.toJson(params[0]));

        IPostNewBeer newBeerService = RETROFIT.getRestAdapterBuilder()
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
                .create(IPostNewBeer.class);

        String body = gson.toJson(params[0]);


        newBeerService.post(body);

        return "";

    }
}
