package cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers;

import com.rateMyDrink.modelClasses.Beer;

import junit.framework.TestCase;

public class GetBeerAsyncTest extends TestCase {

    public GetBeerAsync beerAsync;

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testDoInBackground() throws Exception {
        beerAsync = new GetBeerAsync();
        beerAsync.doInBackground(1);
        beerAsync.execute();
        Beer beer = beerAsync.get();
        assert(beer != null);
    }
}