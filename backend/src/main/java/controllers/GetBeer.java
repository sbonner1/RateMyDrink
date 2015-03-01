package controllers;

import com.rateMyDrink.modelClasses.Beer;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 2/24/15.
 */
public class GetBeer {
    public Beer getBeer(int id) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getBeer(id);

    }
}
