package Controllers;

import Model.User;

import java.sql.SQLException;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;

/**
 * Created by shanembonner on 2/15/15.
 * controller to assist with logging in a user, provided they already exist in the database
 */
public class LoginController {
    public User loginUser(String userName, String password) throws SQLException {
        GetUser controller = new GetUser();
        User tempUser = controller.getUser(userName, password);
        String tempPassword = tempUser.getUserPassword();

        if(BCrypt.checkpw(password, tempPassword)){
            IDatabase db = DatabaseProvider.getInstance();
            return db.loginUser(userName, password);
        }else{
            return null;
        }

    }
  }
