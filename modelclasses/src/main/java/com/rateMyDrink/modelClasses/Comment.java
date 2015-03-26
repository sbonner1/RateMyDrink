package com.rateMyDrink.modelClasses;

/**
 * Model object to represent a comment posted to a Drink
 */
public class Comment {
    private String comment;

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
