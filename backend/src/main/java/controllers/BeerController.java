package Controllers;

import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;
import Model.Beer;

/**
 * Created by shanembonner on 3/16/15.
 */
@RestController
public class BeerController {

    IDatabase db = DatabaseProvider.getInstance();

    public boolean addBeer(Beer beer) throws SQLException {

        try{
            return db.addNewBeer(beer);
            //TODO:
            //return beerService.addNewBeer(beer);
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

    public Beer getBeer(int id) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getBeer(id);
    }

    public List<Beer> getBeerList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getBeerList();
    }
}
