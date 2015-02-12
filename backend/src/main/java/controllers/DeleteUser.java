package controllers;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 2/11/15.
 */
public class DeleteUser {
    public void deleteUser(String itemName) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        db.deleteUser(itemName);
    }
}
