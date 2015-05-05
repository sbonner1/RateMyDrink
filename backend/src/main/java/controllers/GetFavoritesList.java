package controllers;

import com.rateMyDrink.modelClasses.Drink;

import java.sql.SQLException;
import java.util.List;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 5/4/15.
 */
public class GetFavoritesList {
    public List<Drink> getFavoritesList(int userId) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getFavoritesForUser(userId);
    }
}
