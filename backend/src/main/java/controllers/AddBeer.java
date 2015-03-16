package controllers;

import com.rateMyDrink.modelClasses.Beer;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;
import persist.PersistenceException;

/**
 * Created by shanembonner on 3/16/15.
 */
public class AddBeer {
    public boolean addBeer(Beer beer) throws SQLException {

        IDatabase db = DatabaseProvider.getInstance();
        try{
            return db.addNewBeer(beer);
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
}
