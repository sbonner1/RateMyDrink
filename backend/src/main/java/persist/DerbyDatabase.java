package persist;

import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.BeerType;
import com.rateMyDrink.modelClasses.Drink;
import com.rateMyDrink.modelClasses.Liquor;
import com.rateMyDrink.modelClasses.LiquorType;
import com.rateMyDrink.modelClasses.MixedDrink;
import com.rateMyDrink.modelClasses.User;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanembonner on 1/20/15.
 */
public class DerbyDatabase implements IDatabase {
    static{
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Exception e) {
            throw new IllegalStateException("Could not load derby driver");
        }
    }

    private static final int MAX_ATTEMPTS = 10;
    //private static final String DB_DIRECTORY = "Users/shanembonner/rateMyDrinkDB/rateMyDrink.db";
    private static final String DB_DIRECTORY = "rateMyDrinkDB/rateMyDrink.db"; //josh's
    private static final String DB_USER_TABLENAME = "userList";
    private static final String DB_MAIN_DRINK_TABLENAME = "mainDrinkTable";
    private static final String DB_BEER_TABLENAME = "beerTable";
    private static final String DB_MIXED_DRINK_TABLENAME = "mixedDrinkTable";
    private static final String DB_LIQUOR_TABLENAME = "liquorTable";
    private static final String DB_COMMENT_TABLENAME = "commentTable";

    @Override
    public boolean addNewDrink(final Drink drink) throws SQLException {
        return executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                PreparedStatement stmt2 = null;
                ResultSet generatedKeys = null;

                try{
                    stmt = conn.prepareStatement(
                            "insert into " + DB_MAIN_DRINK_TABLENAME + " (drinkName, description, rating) values (?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

                    storeDrinkNoId(drink, stmt, 1);

                    //attempt to insert the drink
                    stmt.executeUpdate();

                    //determine auto-generated id
                    generatedKeys = stmt.getGeneratedKeys();
                    if(!generatedKeys.next()){
                        throw new SQLException("Could not get auto-generated key for inserted User");

                    }

                    //id is used to link the drink in the main table to an item in the subclass table
                    int drinkId = generatedKeys.getInt(1);
                    drink.setId(drinkId);

                    //Determine which of the subclasses the new drink is, and store it in the correct
                    //sub-table with the corresponding variables and storeNoId function call
                    if(drink instanceof Beer){
                        Beer tempBeer = (Beer) drink;
                        stmt2 = conn.prepareStatement(
                                "insert into " + DB_BEER_TABLENAME + "(drinkId, cals, beerType) values (?,?,?)"

                        );
                        storeBeerNoId(tempBeer, stmt, 1);
                        stmt2.executeUpdate();
                    }

                    if(drink instanceof MixedDrink){
                        //TODO:implement MixedDrink
                    }

                    if(drink instanceof Liquor){
                        Liquor tempLiquor = (Liquor) drink;
                        stmt2 = conn.prepareStatement(
                                "insert into " + DB_LIQUOR_TABLENAME + "(drinkId, content, liquorType) values (?,?,?)"

                        );
                        storeLiquorNoId(tempLiquor, stmt, 1);
                        stmt2.executeUpdate();
                    }

                    return true;
                }finally{
                    DBUtil.closeQuietly(generatedKeys);
                    DBUtil.closeQuietly(stmt);
                    DBUtil.closeQuietly(stmt2);
                }
            }
        });
    }
    @Override
    public boolean addNewUser(final User user, String hashedPassword) throws SQLException {
        return executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet generatedKeys = null;

                try{
                    stmt = conn.prepareStatement(
                            "insert into " + DB_USER_TABLENAME + " (userName, password) values (?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

                    storeUserNoId(user, stmt, 1);

                    //attempt to insert the user
                    stmt.executeUpdate();

                    //determine auto-generated id
                    generatedKeys = stmt.getGeneratedKeys();
                    if(!generatedKeys.next()){
                        throw new SQLException("Could not get auto-generated key for inserted User");

                    }
                    int userId = generatedKeys.getInt(1);

                    user.setId(userId);

                    return true;
                }finally{
                    DBUtil.closeQuietly(generatedKeys);
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }

    @Override
    public void deleteDrink(final Drink drink) throws SQLException {
        executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = conn.prepareStatement("delete from " + DB_MAIN_DRINK_TABLENAME + " where drinkName = ?");
                stmt.setString(1, drink.getDrinkName());


                if(drink instanceof Beer){
                    PreparedStatement stmt2 = conn.prepareStatement("delete from " + DB_BEER_TABLENAME + "where drinkId = ?");
                    stmt2.setInt(1, drink.getId());
                    stmt2.executeUpdate();
                }

                if(drink instanceof MixedDrink){
                    //TODO: delete Mixed DRINKS
                }

                if(drink instanceof Liquor){
                    PreparedStatement stmt2 = conn.prepareStatement("delete from " + DB_LIQUOR_TABLENAME + " where drinkId = ?");
                    stmt2.setInt(1, drink.getId());
                    stmt2.executeUpdate();
                }
                stmt.executeUpdate();

                return true;
            }
        });
    }

    @Override
    public void deleteUserList() throws SQLException {
        executeTransaction(new Transaction<Boolean>(){
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = conn.prepareStatement("delete from " + DB_USER_TABLENAME);
                stmt.executeUpdate();
                return true;
            }
        });
    }

    @Override
    public void deleteUser(final String userName) throws SQLException {
        executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = conn.prepareStatement("delete from " + DB_USER_TABLENAME + " where userName = ?");
                stmt.setString(1, userName);
                stmt.executeUpdate();
                return true;
            }
        });
    }

    @Override
    public Beer getBeer(final int id) throws SQLException {
        return executeTransaction(new Transaction<Beer>() {
            @Override
            public Beer execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;

                try{
                    stmt = conn.prepareStatement("select * from " + DB_BEER_TABLENAME + " where drinkId = ?");
                    stmt.setInt(1, id);

                    resultSet = stmt.executeQuery();

                    if(!resultSet.next()){
                        //no such beer
                        return null;
                    }

                    Beer beer = new Beer();
                    loadBeer(beer, resultSet, 1);
                    return beer;
                }finally{
                    DBUtil.closeQuietly(resultSet);
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }

    @Override
    public List<Drink> getDrinkList() throws SQLException {
        return executeTransaction(new Transaction<List<Drink>>() {
            @Override
            public List<Drink> execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;

                try{
                    stmt = conn.prepareStatement("select * from " + DB_MAIN_DRINK_TABLENAME);
                    resultSet = stmt.executeQuery();

                    List<Drink> result = new ArrayList<Drink>();
                    while(resultSet.next()){
                        Drink drink = new Drink();
                        loadDrink(drink, resultSet, 1);
                        result.add(drink);
                    }
                    return result;
                }finally{
                    DBUtil.closeQuietly(resultSet);
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }

    @Override
    public Liquor getLiquor(final int id) throws SQLException {
        return executeTransaction(new Transaction<Liquor>() {
            @Override
            public Liquor execute(Connection conn) throws SQLException{
                PreparedStatement stmt = null;
                ResultSet resultSet = null;
                try {
                    stmt = conn.prepareStatement("select * from " + DB_LIQUOR_TABLENAME + " where drinkId = ?");
                    stmt.setInt(1, id);

                    resultSet = stmt.executeQuery();

                    if(!resultSet.next()){
                        //no such liquor
                        return null;
                    }
                    Liquor liquor = new Liquor();
                    loadLiquor(liquor, resultSet, 1);
                    return liquor;
                }finally{
                    DBUtil.closeQuietly(resultSet);
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }


    @Override
    public User getUser(final String userName, final String password) throws SQLException {
        return executeTransaction(new Transaction<User>() {
            @Override
            public User execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;

                try{
                    stmt = conn.prepareStatement("select * from " + DB_USER_TABLENAME + " where userName = ?");
                    stmt.setString(1, userName);
                    stmt.setString(2, password);

                    resultSet = stmt.executeQuery();

                    if(!resultSet.next()){
                        //no such user
                        return null;
                    }

                    User user = new User(userName, password);
                    loadUser(user, resultSet, 1);
                    return user;
                }finally{
                    DBUtil.closeQuietly(resultSet);
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }


    @Override
    public List<User> getUserList() throws SQLException {
        return executeTransaction(new Transaction<List<User>>() {

            @Override
            public List<User> execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;

                try{
                    stmt = conn.prepareStatement("select * from " + DB_USER_TABLENAME);
                    resultSet = stmt.executeQuery();

                    List<User> result = new ArrayList<User>();
                    while(resultSet.next()){
                        User user = new User();
                        loadUser(user, resultSet, 1);
                        result.add(user);
                    }
                    return result;
                }finally{
                    DBUtil.closeQuietly(resultSet);
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }

    @Override
    public User loginUser(final String userName, final String password) throws SQLException {
        return executeTransaction(new Transaction<User>(){

            @Override
            public User execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;

                try{
                    stmt = conn.prepareStatement("select * from " + DB_USER_TABLENAME + " where userName = ?");
                    stmt.setString(1, userName);

                    resultSet = stmt.executeQuery();

                    if(!resultSet.next()){
                        //no such user
                        return null;
                    }

                    User user = new User(userName, password);
                    loadUser(user, resultSet, 1);
                    return user;
                }finally{
                    DBUtil.closeQuietly(resultSet);
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }

    @Override
    public void replaceUser(final String oldUserName, final User newUser) throws SQLException {
        executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = conn.prepareStatement("update " + DB_USER_TABLENAME + " set userName = ? where userName = ?");
                stmt.setString(1, newUser.getUserName());
                stmt.setString(2, oldUserName);
                stmt.executeUpdate();
                return true;
            }
        });
    }

    @Override
    public void replaceUserList(List<User> newUserList) {
        //TODO: replace user list
    }


    @Override
    public User findUser(String userName) {
        //TODO: find user
        return null;
    }


    private interface Transaction<ResultType> {
        public ResultType execute(Connection conn) throws SQLException;
    }

    public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) throws SQLException {

    try{
        return doExecuteTransaction(txn);
    } catch(SQLException e){
        throw new PersistenceException("Transaction failed", e);
    }

    }

    private <ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
        Connection conn = connect();

        try{
            int numAttempts = 0;
            boolean success = false;
            ResultType result = null;

            while(!success && numAttempts < MAX_ATTEMPTS){
                try{
                    result = txn.execute(conn);
                    conn.commit();
                    success = true;
                }catch(SQLException e) {
                    if(e.getSQLState() != null && e.getSQLState().equals("41000")){
                        //deadlock: retry unless max retry count has been reached
                        numAttempts++;
                    }else{
                        throw e;
                    }
                }
            }
            if(!success){
                throw new SQLException("Transaction failed (too many retries)");
            }

            return result;
        }finally{
            DBUtil.closeQuietly(conn);
        }

    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:" + DB_DIRECTORY + ";create=true");

        //set autocommit to false to allow multiple executions of
        //multiple queries/statements as part of the same transaction
        conn.setAutoCommit(false);

        return conn;
    }

    //this is the main part of the database. the "tables" store the information
    //that the client side needs

    //Each subclass database table will contain a drink_id variable, which
    //corresponds to its place in the main drink (superclass) table
    public void createTables() throws SQLException {
        executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null; //user database table
                PreparedStatement stmt2 = null; //main drink database table
                PreparedStatement stmt3 = null; //beer database table
                PreparedStatement stmt4 = null; //mixed drink database table
                PreparedStatement stmt5 = null; //liquor database table

                //TODO: link all subclass databases to main database
                //Connection connection = DriverManager.getConnection(connectionURL);
                DatabaseMetaData dbmd = conn.getMetaData();
               // ResultSet rs = dbmd.getTab
                try{
                    stmt = conn.prepareStatement(
                            "create table " + DB_USER_TABLENAME + " (" +
                            " id integer primary key not null generated always as identity," +
                            " userName varchar(80) unique," +
                            " password varchar(80)" + //TODO: How many characters is this?
                            ")"
                    );

                    stmt2 = conn.prepareStatement(
                            "create table " + DB_MAIN_DRINK_TABLENAME + " (" +
                            " id integer primary key not null generated always as identity," +
                            " drinkName varchar(200) unique," +
                            " description varchar(1500), " +
                            " rating float(1)" +
                             ")"
                     );

                    stmt3 = conn.prepareStatement(
                            "create table " + DB_BEER_TABLENAME + " (" +
                            //" id integer primary key not null generated always as identity," +
                            " drinkId integer," +
                            " cals integer," +
                            " abv double," +
                            " beerType integer" +
                            ")"
                    );

                    stmt4 = conn.prepareStatement(
                            "create table " + DB_MIXED_DRINK_TABLENAME + " (" +
                            "drinkId integer," +
                            "mainIng integer" + //TODO: FIGURE OUT HOW TO ADD STRING ARRAYS TO THE TABLE
                            ")"
                    );

                    stmt5 = conn.prepareStatement(
                            "create table " + DB_LIQUOR_TABLENAME + " (" +
                            "drinkId integer," +
                            "content float(1)," +
                            "liquorType integer" +
                            ")"
                    );

                    stmt.executeUpdate();
                    stmt2.executeUpdate();
                    stmt3.executeUpdate();
                    stmt4.executeUpdate();
                    stmt5.executeUpdate();

                    return true;

                }finally {
                    DBUtil.closeQuietly(stmt);
                    DBUtil.closeQuietly(stmt2);
                    DBUtil.closeQuietly(stmt3);
                    DBUtil.closeQuietly(stmt4);
                    DBUtil.closeQuietly(stmt5);
                }
            }
        } );

    }

    protected void storeUserNoId(User user, PreparedStatement stmt, int index) throws SQLException {
        //method assumes that the User doesn't have a valid id, so we are not attempting
        //to store the invalid id.
        stmt.setString(index++, user.getUserName());
        stmt.setString(index++, user.getUserPassword());

    }


    protected void storeDrinkNoId(Drink drink, PreparedStatement stmt, int index) throws SQLException {
        stmt.setString(index++, drink.getDrinkName());
        stmt.setString(index++, drink.getDescription());
        stmt.setFloat(index++, drink.getRating());

    }

    protected void storeBeerNoId(Beer beer, PreparedStatement stmt, int index) throws SQLException {
        stmt.setInt(index++, beer.getId());
        stmt.setInt(index++, beer.getCalories());
        stmt.setDouble(index++, beer.getABV());
        //stmt.setString(index++, beer.getBeerType());
        stmt.setInt(index++, beer.getBeerType().ordinal());
    }

    protected void storeMixedDrinkNoId(Drink drink, PreparedStatement stmt, int index) throws SQLException {
        //TODO: storeMixedDrinkNoId
    }

    protected void storeLiquorNoId(Liquor liquor, PreparedStatement stmt, int index) throws SQLException {
        stmt.setInt(index++, liquor.getId());
        stmt.setFloat(index++, liquor.getAlcoholContent());
        stmt.setInt(index++, liquor.getLiquorType().ordinal());
    }


    public void loadInitialData() throws SQLException {
        executeTransaction(new Transaction<Boolean>() {

            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                PreparedStatement stmt2 = null;
                PreparedStatement stmt3 = null;
                PreparedStatement stmt4 = null;

                try{
                    stmt = conn.prepareStatement("insert into " + DB_USER_TABLENAME + " (userName, password) values (?,?)");
                    storeUserNoId(new User("testUser", "password"), stmt, 1);
                    stmt.addBatch();
                    stmt.executeBatch();

                    stmt2 = conn.prepareStatement("insert into " + DB_MAIN_DRINK_TABLENAME + " (drinkName, description, rating) values (?,?,?)");
                    Drink drink = new Drink();
                    drink.setDrinkName("testDrink");
                    drink.setRating(5);
                    storeDrinkNoId(drink, stmt2, 1);
                    stmt2.addBatch();
                    stmt2.executeBatch();

                    stmt3 = conn.prepareStatement("insert into " + DB_BEER_TABLENAME + "(drinkId, cals, beerType) values (?,?,?)");
                    Beer beer = new Beer();
                    beer.setCalories(400);
                    beer.setBeerType(BeerType.LAGER);
                    stmt3.addBatch();
                    stmt3.executeBatch();

                    stmt4 = conn.prepareStatement("insert into " + DB_LIQUOR_TABLENAME + "(drinkId, content, liquorType) values (?,?,?)");
                    Liquor liquor = new Liquor();
                    liquor.setAlcoholContent(75.50f);
                    liquor.setLiquorType(LiquorType.RUM);
                    stmt4.addBatch();
                    stmt4.executeBatch();

                    return true;
                }finally{
                    DBUtil.closeQuietly(stmt);
                    DBUtil.closeQuietly(stmt2);
                    DBUtil.closeQuietly(stmt3);
                    DBUtil.closeQuietly(stmt4);
                }
            }
        });
    }

    protected void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
        user.setId(resultSet.getInt(index++));
        user.setUserName(resultSet.getString(index++));
        user.setUserPassword(resultSet.getString(index++));
    }

    protected void loadDrink(Drink drink, ResultSet resultSet, int index) throws SQLException {
        drink.setId(resultSet.getInt(index++));
        drink.setDrinkName(resultSet.getString(index++));
        drink.setDescription(resultSet.getString(index++));
        drink.setRating(resultSet.getFloat(index++));
    }

    protected void loadBeer(Beer beer, ResultSet resultSet, int index) throws SQLException {
        beer.setId(resultSet.getInt(index++));
        beer.setCalories(resultSet.getInt(index++));
        beer.setABV(resultSet.getInt(index++));

        int beerTypeOrdinal = resultSet.getInt(index++);
        BeerType[] beerTypes = BeerType.values();
        beer.setBeerType(beerTypes[beerTypeOrdinal]);
    }

    protected void loadLiquor(Liquor liquor, ResultSet resultSet, int index) throws SQLException {
        liquor.setId(resultSet.getInt(index++));
        liquor.setAlcoholContent(resultSet.getFloat(index++));
        int liquorTypeOrdinal = resultSet.getInt(index++);
        LiquorType[] liquorTypes = LiquorType.values();
        liquor.setLiquorType(liquorTypes[liquorTypeOrdinal]);

    }

    public static void main(String[] args) throws SQLException {
        DerbyDatabase db = new DerbyDatabase();
        System.out.println("Creating tables...");
        db.createTables();
        System.out.println("Loading intial data...");
        db.loadInitialData();
        System.out.println("Done!");

    }
}
