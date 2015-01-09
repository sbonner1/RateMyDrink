package com.rateMyDrink.DerbyDatabase;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by shanembonner on 1/8/15.
 */
public abstract class DBUtil {
    /**
     * Attempt to close a Statement.
     *
     * @param stmt the Statement to close
     */
    public static void closeQuietly(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // ignore
            }

        }
    }


}