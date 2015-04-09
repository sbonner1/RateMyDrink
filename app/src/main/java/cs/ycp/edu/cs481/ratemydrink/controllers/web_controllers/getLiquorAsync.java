package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.Liquor;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import retrofit.converter.GsonConverter;

/**
 * A controller to asynchronously make a GET request to the database for a single Liquor object.
 */
public class getLiquorAsync extends AsyncTask<Integer, Void, Liquor> {
    @Override
    protected Liquor doInBackground(Integer... params) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Liquor.class, new DateTypeAdapter())
                .create();

        ILiquorRequests getLiquorService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                //.setConverter(new GsonConverter(gson))
                .build()
                .create(ILiquorRequests.class);

        return getLiquorService.get("getLiquor" ,params[0]);
    }
}
