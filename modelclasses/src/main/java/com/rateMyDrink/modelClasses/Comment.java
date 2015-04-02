package com.rateMyDrink.modelClasses;

/**
<<<<<<< HEAD
 * Created by shanembonner on 3/30/15.
 */
public class Comment {
    private int drinkId;
    private String username;
    private String comment;
/**
 * Model object to represent a comment posted to a Drink
 */
    //constructor
    public Comment(String comment) {
        this.comment = comment;
    }

    //getter for the comment string
    public String getComment() {
        return comment;
    }

    //setter for the comment string
    public void setComment(String comment) {
        this.comment = comment;
    }

}
