package controllers;

import com.rateMyDrink.modelClasses.User;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;
import persist.PersistenceException;

/**
 * Created by shanembonner on 2/10/15.
 */
public class AddUser {
    public boolean addNewUser(User user, String password) throws SQLException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        IDatabase db = DatabaseProvider.getInstance();
        try{
            return db.addNewUser(user, hashedPassword);
        }catch(PersistenceException e){
            if(e.getCause() instanceof SQLException){
                SQLException sqlEx = (SQLException) e.getCause();

                System.out.println("SQLState is " + sqlEx.getSQLState());
                return false;
            }

            throw e;
        }
    }
}
