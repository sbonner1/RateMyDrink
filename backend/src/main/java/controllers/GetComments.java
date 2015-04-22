package controllers;

import com.rateMyDrink.modelClasses.Comment;

import java.sql.SQLException;
import java.util.List;

import persist.DatabaseProvider;
import persist.IDatabase;

/**
 * Created by shanembonner on 4/10/15.
 */
public class GetComments {

    public List<Comment> getComments(int id, int startIndex, int endIndex) throws SQLException {
        IDatabase db = DatabaseProvider.getInstance();
        return db.getComments(id, startIndex, endIndex);
    }
}
