package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 12/27/14.
 */
public class User implements Cloneable {
    private String userName;
    private String password;
    private String passwordHash;
    private int id;
    private boolean adminStatus;

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

    public void setAdminStatusTrue(){
        this.adminStatus = true;
    }

    public boolean getAdminStatus(User user){
        return user.adminStatus;
    }

    public boolean isAdmin(boolean adminStatus){
        return this.adminStatus;
    }

    @Override
    public User clone(){
        try {
            User dup = (User) super.clone();
            return dup;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("This can't happen", e);
        }
    }


}
