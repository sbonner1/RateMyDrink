package persist;

import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Drink;
import com.rateMyDrink.modelClasses.Liquor;
import com.rateMyDrink.modelClasses.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by shanembonner on 1/20/15.
 */
public interface IDatabase {

    void replaceUser(String oldUserName, User newUser) throws SQLException;

    void deleteUserList() throws SQLException;

    void deleteUser(String userName) throws SQLException;

    List<User> getUserList() throws SQLException;

    void replaceUserList(List<User> newUserList);

    public boolean addNewUser(User user, String hashedPassword) throws SQLException;

    public User findUser(String userName);

    public User loginUser(String userName, String password) throws SQLException;

    User getUser(String userName, String password) throws SQLException;

    Liquor getLiquor(int id) throws SQLException;

    boolean addNewDrink(Drink drink) throws SQLException;

    List<Drink> getDrinkList() throws SQLException;

    Beer getBeer(int id) throws SQLException;
}
