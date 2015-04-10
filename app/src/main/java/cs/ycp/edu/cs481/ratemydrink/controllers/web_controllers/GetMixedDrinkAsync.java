package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.MixedDrink;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import retrofit.converter.GsonConverter;

/**
 * A controller to asynchronously make a GET request to the database for a single MixedDrink object.
 */
public class GetMixedDrinkAsync extends AsyncTask<Integer, Void, MixedDrink>{
    @Override
    protected MixedDrink doInBackground(Integer... params) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MixedDrink.class, new DateTypeAdapter())
                .create();

        IMixedDrinkRequests getMixedDrinkService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .setConverter(new GsonConverter(gson))
                .build()
                .create(IMixedDrinkRequests.class);

        return getMixedDrinkService.get(params[0]);
    }
}
