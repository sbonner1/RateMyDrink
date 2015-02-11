/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.shanembonner.RateMyDrink.Servlets;

import com.rateMyDrink.modelClasses.User;

import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AddUser;

public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        if(action != null){
            req.setAttribute("action", action);
        }
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String pathInfo = req.getPathInfo();

        //check the uri substring to see if there is an item to be added to the database.

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo(); //path
        String action = req.getParameter("action");

        //check to make sure there is no pathname after
        if(pathInfo == null){
            resp.getWriter().println("Post unsuccessful");
        }

        //case to handle adding a new user to the database
        if(action.equals("addUser")) {
            User newUser = null;
            String password = null;
            try {
                //newUser = JSON.getObjectMapper().readValue(req.getReader(), User.class);
                try {
                    newUser = JSON.getObjectMapper().readValue(req.getReader(), User.class);
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                }
                password = newUser.getUserPassword();
            } catch (Exception e) {
                e.getMessage();
            }

            AddUser addController = new AddUser();
            boolean success = false;
            try {
                success = addController.addNewUser(newUser, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");

                // JSON.getObjectMapper().writeValue(resp.getWriter(), newUser);

            }else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("User " + pathInfo + "already exists");
            }

        }



    }
}
