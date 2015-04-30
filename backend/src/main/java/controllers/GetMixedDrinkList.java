package controllers;

import com.rateMyDrink.modelClasses.MixedDrink;

import java.sql.SQLException;
import java.util.List;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 4/27/15.
 */
public class GetMixedDrinkList {
    public List<MixedDrink> getMixedDrinkList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getMixedDrinkList();
    }
}
