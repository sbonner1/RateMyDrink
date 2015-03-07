package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import com.rateMyDrink.modelClasses.Drink;

import java.util.List;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;

/**
 * Created by user on 3/6/2015.
 */
public class GetDrinkListAsync extends AsyncTask<Void, Void, List<Drink>>{

    @Override
    protected List<Drink> doInBackground(Void... params) {
        IGetDrinksList getDrinkListService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .build()
                .create(IGetDrinksList.class);

        return getDrinkListService.getDrinkList();
    }

}
