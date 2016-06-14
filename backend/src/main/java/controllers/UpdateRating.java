package Controllers;

import Model.Drink;

import java.sql.SQLException;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;

/**
 * Created by shanembonner on 5/4/15.
 */
public class UpdateRating {
    public boolean updateRating(Drink drink, float rating) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        try {
            return db.updateRating(drink, rating);
        } catch (
                PersistenceException e) {
            if (e.getCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getCause();

                System.out.println("SQLState is " + sqlEx.getSQLState());
                e.printStackTrace();
                return false;
            }

            throw e;
        }
    }
}
