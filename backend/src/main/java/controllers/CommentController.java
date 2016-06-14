package Controllers;

import java.sql.SQLException;
import java.util.List;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.IDatabase;
import DatabaseManagers.PersistenceException;
import Model.Comment;

/**
 * Created by shanembonner on 11/17/15.
 */
public class CommentController {

    IDatabase db = DatabaseProvider.getInstance();

    public boolean addComment(Comment comment) throws SQLException {
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

    public List<Comment> getComments(int id, int startIndex, int endIndex) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getComments(id, startIndex, endIndex);
    }
}
