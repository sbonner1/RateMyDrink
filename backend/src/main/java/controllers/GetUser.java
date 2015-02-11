package controllers;

import com.rateMyDrink.modelClasses.User;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 2/10/15.
 */
public class GetUser {
    public User getUser(String userName, String password) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getUser(userName, password);

    }
}
