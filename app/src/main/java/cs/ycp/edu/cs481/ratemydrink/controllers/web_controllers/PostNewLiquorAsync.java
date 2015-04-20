package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rateMyDrink.modelClasses.Liquor;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import cs.ycp.edu.cs481.ratemydrink.controllers.LiquorAdapter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * An AsyncTask to post a new Liquor object to the backend database
 */
public class PostNewLiquorAsync extends AsyncTask<Liquor, Void, Liquor> {
    @Override
    protected Liquor doInBackground(Liquor... params) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Liquor.class, new LiquorAdapter())
                .create();

        ILiquorRequests newLiquorService = RETROFIT.getRestAdapterBuilder()
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
                .create(ILiquorRequests.class);

        return newLiquorService.post(params[0]);
    }
}
