package controllers;

import com.rateMyDrink.modelClasses.User;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 2/15/15.
 */
public class ReplaceUser {
    public void replaceUser(String oldUser, User newUser) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        db.replaceUser(oldUser, newUser);
    }
}
