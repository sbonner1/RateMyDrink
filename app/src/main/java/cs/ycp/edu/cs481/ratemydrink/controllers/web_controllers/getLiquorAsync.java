package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import android.os.AsyncTask;

import Model.Liquor;

import cs.ycp.edu.cs481.ratemydrink.RETROFIT;
import cs.ycp.edu.cs481.ratemydrink.URLInfo;

/**
 * A controller to asynchronously make a GET request to the database for a single Liquor object.
 */
public class getLiquorAsync extends AsyncTask<Integer, Void, Liquor> {
    @Override
    protected Liquor doInBackground(Integer... params) {

        ILiquorRequests getLiquorService = RETROFIT.getRestAdapterBuilder()
                .setEndpoint(URLInfo.DOMAIN_URL)
                .build()
                .create(ILiquorRequests.class);

        return getLiquorService.get("getLiquor" ,params[0]);
    }
}
