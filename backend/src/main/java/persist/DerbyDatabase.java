package persist;

import com.rateMyDrink.modelClasses.User;

import java.sql.Connection;
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
    private static final String DB_DIRECTORY = "Users/shanembonner/rateMyDrinkDB/rateMyDrink.db";
    private static final String DB_TABLENAME = "userList";

    @Override
    public boolean addNewUser(final User user, String hashedPassword) throws SQLException {
        return executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet generatedKeys = null;

                try{
                    stmt = conn.prepareStatement(
                            "insert into " + DB_TABLENAME + " (userName, password) values (?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

                    storeUserNoId(user, stmt, 1);

                    //attempt to insert the user
                    stmt.executeUpdate();

                    //determine auto-generated id
                    generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){
                        throw new SQLException("Could not get auto-generated key for inserted User");

                    }
                    return true;
                }finally{
                    DBUtil.closeQuietly(generatedKeys);
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
                    stmt = conn.prepareStatement("select * from " + DB_TABLENAME + " where userName = ?");
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
    public void deleteUserList() throws SQLException {
        executeTransaction(new Transaction<Boolean>(){
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = conn.prepareStatement("delete from " + DB_TABLENAME);
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
                PreparedStatement stmt = conn.prepareStatement("delete from " + DB_TABLENAME + " where userName = ?");
                stmt.setString(1, userName);
                stmt.executeUpdate();
                return true;
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
                    stmt = conn.prepareStatement("select * from " + DB_TABLENAME);
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
    public void replaceUser(final String oldUserName, final User newUser) throws SQLException {
        executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = conn.prepareStatement("update " + DB_TABLENAME + " set userName = ? where userName = ?");
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

    @Override
    public User loginUser(final String userName, final String password) throws SQLException {
        return executeTransaction(new Transaction<User>(){

            @Override
            public User execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;
                ResultSet resultSet = null;

                try{
                    stmt = conn.prepareStatement("select * from " + DB_TABLENAME + " where userName = ?");
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
    public void createTables() throws SQLException {
        executeTransaction(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;

                try{
                    stmt = conn.prepareStatement(
                            "create table " + DB_TABLENAME + " (" +
                            " id integer primary key not null generated always as identity," +
                            " userName varchar(80) unique," +
                            " password varchar(80)," +
                            ")"
                            //TODO: add other fields to the database as necessary
                    );
                    stmt.executeUpdate();

                    return true;

                }finally {
                    DBUtil.closeQuietly(stmt);
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

    public void loadInitialData() throws SQLException {
        executeTransaction(new Transaction<Boolean>() {

            @Override
            public Boolean execute(Connection conn) throws SQLException {
                PreparedStatement stmt = null;

                try{
                    stmt = conn.prepareStatement("insert into " + DB_TABLENAME + " (userName, password) values (?,?)");
                    storeUserNoId(new User("testUser", "password"), stmt, 1);
                    stmt.addBatch();

                    stmt.executeBatch();

                    return true;
                }finally{
                    DBUtil.closeQuietly(stmt);
                }
            }
        });
    }

    protected void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
        user.setId(resultSet.getInt(index++));
        user.setUserName(resultSet.getString(index++));
        //user.setUserPassword(resultSet.getString(index++));
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
