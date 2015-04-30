package controllers;

import com.rateMyDrink.modelClasses.Liquor;

import java.sql.SQLException;
import java.util.List;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 4/27/15.
 */
public class GetLiquorList {
    public List<Liquor> getLiquorList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getLiquorList();
    }
}
