package controllers;

import com.rateMyDrink.modelClasses.Beer;

import java.sql.SQLException;
import java.util.List;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 4/27/15.
 *
 * Class to control getting the entire database list of Beer objects
 */
public class GetBeerList {
    public List<Beer> getBeerList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getBeerList();
    }
}
