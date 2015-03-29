package controllers;

import com.rateMyDrink.modelClasses.MixedDrink;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;
import persist.PersistenceException;

/**
 * Created by shanembonner on 3/23/15.
 */
public class AddMixedDrink {
    public boolean addMixedDrink(MixedDrink mixedDrink) throws SQLException {

        IDatabase db = DatabaseProvider.getInstance();
        try{
            return db.addNewMixedDrink(mixedDrink);
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
