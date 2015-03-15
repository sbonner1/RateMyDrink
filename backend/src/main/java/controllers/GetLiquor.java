package controllers;

import com.rateMyDrink.modelClasses.Liquor;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 3/12/15.
 */
public class GetLiquor {
    public Liquor getLiquor(int id) throws SQLException{
        IDatabase db = DatabaseProvider.getInstance();
        return db.getLiquor(id);
    }
}
