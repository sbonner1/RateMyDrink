package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import Model.MixedDrink;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;

/**
 * A controller to asynchronously make a GET request to the database for a single MixedDrink object.
 */
public class GetMixedDrinkAsync extends AsyncTask<Integer, Void, MixedDrink>{
    @Override
    protected MixedDrink doInBackground(Integer... params) {

        IMixedDrinkRequests getMixedDrinkService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .build()
                .create(IMixedDrinkRequests.class);

        return getMixedDrinkService.get(params[0]);
    }
}
