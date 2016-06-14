package Controllers;

import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;
import Model.Drink;

/**
 * Created by shanembonner on 11/17/15.
 */
@RestController
public class DrinkController {

    IDatabase db = DatabaseProvider.getInstance();

    public boolean addNewDrink(Drink drink) throws SQLException {
        try{
            return db.addNewDrink(drink);
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

    public Drink getDrink(int id) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getDrink(id);
    }

    public List<Drink> getDrinkList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getDrinkList();
    }

    public void deleteDrink(Drink drink) throws SQLException{
        IDatabase db = DatabaseProvider.getInstance();
        db.deleteDrink(drink);
    }
}
