package controllers;

import com.rateMyDrink.modelClasses.MixedDrink;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 3/29/15.
 */
public class GetMixedDrink {
    public MixedDrink getMixedDrink(int id) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getMixedDrink(id);
    }
}
