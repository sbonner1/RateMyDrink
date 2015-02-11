package persist;

/**
 * Created by shanembonner on 1/20/15.
 */
public class DatabaseProvider {
    private static IDatabase theInstance;

    public static void setInstance(IDatabase db) {theInstance = db;}

    public static IDatabase getInstance(){
        if(theInstance == null){
            throw new IllegalStateException("No database instance found");
        }
        return theInstance;
    }
}
