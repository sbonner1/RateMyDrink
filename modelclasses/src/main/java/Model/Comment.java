package Model;

/**
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
    public Comment() {
        //this.comment = comment;
    }

    /**
     * constructor to initialize all fields
     *
     * @param id id of the associated drink
     * @param username name of the user that posted this comment
     * @param comment the comment
     */
    public Comment(int id, String username, String comment){
        this.drinkId = id;
        this.username = username;
        this.comment = comment;
    }

    public void setDrinkId(int value){
        this.drinkId = value;
    }

    public int getDrinkId(){
        return this.drinkId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
       return this.username;
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
