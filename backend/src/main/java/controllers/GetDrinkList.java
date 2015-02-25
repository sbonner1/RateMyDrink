package controllers;

import com.rateMyDrink.modelClasses.Drink;

import java.sql.SQLException;
import java.util.List;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 2/24/15.
 */
public class GetDrinkList {
    public List<Drink> getDrinkList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getDrinkList();
    }
}

