package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Drink;

import java.util.List;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import cs.ycp.edu.cs481.ratemydrink.controllers.StringArrayAdapter;
import retrofit.RestAdapter;

/**
 * A controller to asynchronously make a GET request to the database for an array of Drink objects.
 */
public class GetDrinkListAsync extends AsyncTask<Void, Void, Drink[]>{

    @Override
    protected Drink[] doInBackground(Void... params) {

        IGetDrinksList getDrinkListService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("retrofit", message);
                    }
                })
                .build()
                .create(IGetDrinksList.class);

        return getDrinkListService.getDrinkList();
    }

}
