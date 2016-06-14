package Servlet;

/**
 * Created by shanembonner on 3/4/15.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import DatabaseManagers.DatabaseProvider;
import DatabaseManagers.DerbyDatabase;

public class DatabaseInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent e) {
        // Webapp is starting
        DatabaseProvider.setInstance(new DerbyDatabase());
        System.out.println("Initialized database!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {
        // webapp is shutting down

    }

}