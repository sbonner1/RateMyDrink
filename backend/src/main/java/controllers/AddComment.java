package controllers;

import com.rateMyDrink.modelClasses.Comment;

import java.sql.SQLException;

import persist.DatabaseProvider;
import persist.IDatabase;
import persist.PersistenceException;

/**
 * Created by shanembonner on 4/10/15.
 */
public class AddComment {
    public boolean addComment(Comment comment) throws SQLException {

        IDatabase db = DatabaseProvider.getInstance();
        try{
            return db.addNewComment(comment);
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
