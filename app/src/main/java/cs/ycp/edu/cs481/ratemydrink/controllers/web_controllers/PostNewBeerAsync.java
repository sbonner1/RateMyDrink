package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.Beer;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import cs.ycp.edu.cs481.ratemydrink.controllers.BeerAdapter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * A controller to asynchronously make a POST request add a new Beer object to the database.
 */
public class PostNewBeerAsync extends AsyncTask<Beer, Void, String> {
    @Override
    protected String doInBackground(Beer... params) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Beer.class, new BeerAdapter())
                .create();

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

        newBeerService.post(params[0]);

        return "";

    }
}
