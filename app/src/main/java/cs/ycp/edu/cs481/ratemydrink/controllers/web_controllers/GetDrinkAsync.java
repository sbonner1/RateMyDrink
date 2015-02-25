package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;


import android.os.AsyncTask;

import com.rateMyDrink.modelClasses.Drink;

/**
 * A controller to asynchronously make a GET request to the database for a single drink.
 */
public class GetDrinkAsync extends AsyncTask<Integer, Void, Drink> {

    @Override
    protected Drink doInBackground(Integer... params) {
        return null;
    }

}
