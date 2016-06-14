package Controllers;

import java.sql.SQLException;
import java.util.List;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;
import Model.User;

/**
 * Created by shanembonner on 11/17/15.
 */
public class UserController {

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


    public void deleteUser(String itemName) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        db.deleteUser(itemName);
    }

    public void deleteUserList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        db.deleteUserList();
    }

    public User getUser(String userName, String password) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getUser(userName, password);

    }

    public List<User> getUserList() throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getUserList();
    }

    public void replaceUser(String oldUser, User newUser) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        db.replaceUser(oldUser, newUser);
    }


}
