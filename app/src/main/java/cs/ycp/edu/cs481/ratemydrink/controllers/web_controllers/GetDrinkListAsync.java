package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Drink;

import java.util.List;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;

/**
 * Created by user on 3/6/2015.
 */
public class GetDrinkListAsync extends AsyncTask<Void, Void, String[]>{

    @Override
    protected String[] doInBackground(Void... params) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Drink.class, new DateTypeAdapter())
                .create();

        IGetDrinksList getDrinkListService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .build()
                .create(IGetDrinksList.class);

        return getDrinkListService.getDrinkList();
    }

}
