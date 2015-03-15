package controllers;

import com.rateMyDrink.modelClasses.Drink;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 3/15/15.
 */
public class DeleteDrink {
    public void deleteDrink(Drink drink) throws SQLException{
        IDatabase db = DatabaseProvider.getInstance();
        db.deleteDrink(drink);
    }
}
