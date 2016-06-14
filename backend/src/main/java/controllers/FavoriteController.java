package Controllers;

import java.sql.SQLException;
import java.util.List;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;
import Model.Drink;
import Model.Favorite;

/**
 * Created by shanembonner on 11/17/15.
 */
public class FavoriteController {
    public boolean addFavorite(Favorite favorite) throws SQLException {

        IDatabase db = DatabaseProvider.getInstance();
        try{
            return db.addNewFavorite(favorite);
        }catch(PersistenceException e){
            if(e.getCause() instanceof SQLException){
                SQLException sqlEx = (SQLException) e.getCause();

                System.out.println("SQLState is " + sqlEx.getSQLState());
                e.printStackTrace();
                return false;
            }

            throw e;
        }
    }

    public List<Drink> getFavoritesList(int userId) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getFavoritesForUser(userId);
    }
}
