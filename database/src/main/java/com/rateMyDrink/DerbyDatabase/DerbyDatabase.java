package com.rateMyDrink.DerbyDatabase;

import com.rateMyDrink.modelClasses.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shanembonner on 1/8/15.
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
    public void replaceUser(String oldUserName, User newUser) {

    }

    @Override
    public void deleteUserList() {

    }

    @Override
    public void deleteUser(String userName) {

    }

    @Override
    public List<User> getUserList() {
        return null;
    }

    @Override
    public void replaceUserList(List<User> newUserList) {

    }

    @Override
    public boolean addNewUser(User user, String hashedPassword) {
        return false;
    }

    @Override
    public User findUser(String userName) {
        return null;
    }

    @Override
    public User loginUser(String userName, String password) {
        return null;
    }

    @Override
    public User getUser(String userName, String password) {
        return null;
    }

    private interface Transaction<ResultType> {
        public ResultType execute(Connection conn) throws SQLException;
    }

    public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) throws SQLException {


        return doExecuteTransaction(txn);

        //get try/catch loop to work
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
            DBUtil.closeQuietly((java.sql.Statement) conn); //TODO: FIX THIS TYPE CAST
        }

    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:" + DB_DIRECTORY + ";create=true");

        //set autocommit to false to allow multiple executions of
        //multiple queries/statements as part of the same transaction
        conn.setAutoCommit(false);

        return conn;
    }

    public void createTables(){
        //executeTransaction((con) ->)
    }
}
