package controllers;

import com.rateMyDrink.modelClasses.User;

import java.sql.SQLException;
import java.util.List;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 2/11/15.
 */
public class GetUserList {
    public List<User> getUserList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getUserList();
    }
}
