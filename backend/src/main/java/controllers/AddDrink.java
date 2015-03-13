package controllers;

import com.rateMyDrink.modelClasses.Drink;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;
import persist.PersistenceException;

/**
 * Created by shanembonner on 2/24/15.
 */
public class AddDrink {
    public boolean addNewDrink(Drink drink) throws SQLException {

        IDatabase db = DatabaseProvider.getInstance();
        try{
            return db.addNewDrink(drink);
        }catch(PersistenceException e){
            if(e.getCause() instanceof SQLException){
                SQLException sqlEx = (SQLException) e.getCause();
                //TODO: check whether it's a uniqueness constraint
                System.out.println("SQLState is " + sqlEx.getSQLState());
                e.printStackTrace();
                return false;
            }

            throw e;
        }
    }
}
