package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rateMyDrink.modelClasses.MixedDrink;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import cs.ycp.edu.cs481.ratemydrink.controllers.MixedDrinkAdapter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * An AsyncTask to post a new MixedDrink object to the backend database
 */
public class PostNewMixedDrinkAsync extends AsyncTask<MixedDrink, Void, MixedDrink> {
    @Override
    protected MixedDrink doInBackground(MixedDrink... params) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MixedDrink.class, new MixedDrinkAdapter())
                .create();

        IMixedDrinkRequests newMixedDrinkService = RETROFIT.getRestAdapterBuilder()
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
                .create(IMixedDrinkRequests.class);

        return newMixedDrinkService.post(params[0]);
    }
}
