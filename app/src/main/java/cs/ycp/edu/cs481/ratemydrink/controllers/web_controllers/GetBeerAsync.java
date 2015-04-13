package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;


import android.os.AsyncTask;
import android.util.Log;

import com.rateMyDrink.modelClasses.Beer;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import retrofit.RestAdapter;

/**
 * A controller to asynchronously make a GET request to the database for a single Beer object.
 */
public class GetBeerAsync extends AsyncTask<Integer, Void, Beer> {

    @Override
    protected Beer doInBackground(Integer... params) {

        IBeerRequests getBeerService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("retrofit", message);
                    }
                })
                .build()
                .create(IBeerRequests.class);

        return getBeerService.get("getBeer" ,params[0]);
    }

}
