package com.rateMyDrink.DerbyDatabase;

import com.rateMyDrink.modelClasses.User;

import java.util.List;

/**
 * Created by shanembonner on 1/3/15.
 */
public interface IDatabase {

    void replaceUser(String oldUserName, User newUser);

    void deleteUserList();

    void deleteUser(String userName);

    List<User> getUserList();

    void replaceUserList(List<User> newUserList);

    public boolean addNewUser(User user, String hashedPassword);

    public User findUser(String userName);

    public User loginUser(String userName, String password);

    User getUser(String userName, String password);
}
