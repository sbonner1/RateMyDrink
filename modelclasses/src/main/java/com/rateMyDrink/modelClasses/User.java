package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 1/27/15.
 */
public class User implements Cloneable {
    private String userName;
    private String password;
    //private String passwordHash;
    private int id;
    private boolean loggedInStatus;

    public User() {
        this.userName = "";
        this.password = "";

    }

    public User(String userName, String password) {
        //TODO: find a good way to hash passwords, possibly do it here in the constructor?
        this.userName = userName;
        this.password = password;

    }

    //getter and setter methods

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserPassword(String password){
        this.password = password;
    }

    public String getUserPassword(){
        return password;
    }

    //set user id for the database
    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
        /*public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordHash() {
		return passwordHash;
	}
	*/
    }

    public void setLoggedInStatusTrue(){
        this.loggedInStatus = true;
    }

    public void setLoggedInStatusFalse() { this.loggedInStatus = false; }

    public boolean isLoggedIn(User user){
        return user.loggedInStatus;
    }



    @Override
    public User clone(){
        try {
            User dup = (User) super.clone();
            return dup;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Cloning for User class is not supported.", e);
        }
    }



}
