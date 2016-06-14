package Controllers;

import java.sql.SQLException;
import java.util.List;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;
import Model.MixedDrink;

/**
 * Created by shanembonner on 11/17/15.
 */
public class MixedDrinkController {
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

    public MixedDrink getMixedDrink(int id) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getMixedDrink(id);
    }

    public List<MixedDrink> getMixedDrinkList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getMixedDrinkList();
    }
}
