package Controllers;

import java.sql.SQLException;
import java.util.List;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;
import Model.Liquor;

/**
 * Created by shanembonner on 11/17/15.
 */
public class LiquorController {

    public boolean addLiquor(Liquor liquor) throws SQLException {

        IDatabase db = DatabaseProvider.getInstance();
        try{
            return db.addNewLiquor(liquor);
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

    public Liquor getLiquor(int id) throws SQLException{
        IDatabase db = DatabaseProvider.getInstance();
        return db.getLiquor(id);
    }

    public List<Liquor> getLiquorList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getLiquorList();
    }
}
