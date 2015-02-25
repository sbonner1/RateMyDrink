package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.Drink;

import java.util.ArrayList;

import cs.ycp.edu.cs481.ratemydrink.URLInfo;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * An AsyncTask service to make a GET request for a list of drinks from the server
 */
public class GetDrinksListAsync extends AsyncTask{

    @Override
    protected Object doInBackground(Object[] params) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(/* add class type here*/, new DateTypeAdapter()) //TODO: figure out way to use ArrayList<Drink>.class with TypeAdapter
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        IGetDrinksList getDrinkService = restAdapter.create(IGetDrinksList.class);
        getDrinkService.getDrinkList();

        return null;
    }
}
