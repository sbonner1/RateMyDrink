package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Drink;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import retrofit.converter.GsonConverter;

/**
 * A controller to asynchronously make a POST request add a new Beer object to the database.
 */
public class PostNewBeerAsync extends AsyncTask<Beer, Void, Void> {
    @Override
    protected Void doInBackground(Beer... params) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Drink.class, new DateTypeAdapter())
                .create();

        Log.d("GSON", gson.toJson(params[0]));

        IPostNewBeer newBeerService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .setConverter(new GsonConverter(gson))
                .build()
                .create(IPostNewBeer.class);

        newBeerService.post(params[0]);

        return null;
    }
}
