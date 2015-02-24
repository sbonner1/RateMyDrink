/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.shanembonner.RateMyDrink.Servlets;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rateMyDrink.modelClasses.Drink;
import com.rateMyDrink.modelClasses.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AddDrink;
import controllers.AddUser;
import controllers.DeleteUser;
import controllers.DeleteUserList;
import controllers.GetUser;
import controllers.GetUserList;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, JsonMappingException {
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

            //newUser = JSON.getObjectMapper().readValue(req.getReader(), User.class);
            newUser = JSON.getObjectMapper().readValue(req.getReader(), User.class);
            password = newUser.getUserPassword();


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
                JSON.getObjectMapper().writeValue(resp.getWriter(), newUser);

            }else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("User " + pathInfo + "already exists");
            }

        }

        if(action.equals("addDrink")){
            Drink newDrink = null;

            newDrink = JSON.getObjectMapper().readValue(req.getReader(), Drink.class);

            AddDrink controller = new AddDrink();
            boolean success = false;

            try{
                success = controller.addNewDrink(newDrink);
            }catch(SQLException e){
                e.printStackTrace();
            }

            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                JSON.getObjectMapper().writeValue(resp.getWriter(), newDrink);

            }else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("User " + pathInfo + "already exists");
            }
        }

        if(action.equals("getUser")){
            //get the user name
            if(pathInfo.startsWith("/")) {
                pathInfo = pathInfo.substring(1);
            }
            String password = JSON.getObjectMapper().readValue(req.getReader(), String.class);
            User user = null;
            GetUser controller = new GetUser();
            try {
                user = controller.getUser(pathInfo,password);
                System.out.println("accessed database");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(user == null){
                //no such item, so return a NOT FOUND response
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("No such user: " + pathInfo);
                return;
            }
            System.out.println(user.getUserName());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), user);

        }

        if(action.equals("getUserList")){
            //retrieve inventory from database
            GetUserList getController = new GetUserList();
            List<User> userList = null;
            try {
                userList = getController.getUserList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //print userList to user's terminal
            String[] userNameList = new String[userList.size()]; //return the list of usernames for the scoreboard
                                                                 //as an array of strings to be displayed
            int count = 0;
            for(User user: userList){
                String userName = user.getUserName();
                userNameList[count] = userName;
                count++;
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), userNameList);
        }

        if(action.equals("loginUser")){

        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String action = req.getParameter("action");

        //if there is an item name, delete it. if not, delete whole database
        if(action.equals("deleteUser")){
            //get the item name
            if(pathInfo.startsWith("/")){
                pathInfo = pathInfo.substring(1);
            }
            //delete the item from inventory
            DeleteUser controller = new DeleteUser();
            try {
                controller.deleteUser(pathInfo);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //a controller to get the inventory after an item is deleted
            GetUserList responseController = new GetUserList();

            //retrieve inventory and show the update
            List<User> userList = null;
            try {
                userList = responseController.getUserList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), userList);
        }

        if(action.equals("deleteUserList")){
            //delete the entire list
            DeleteUserList controller = new DeleteUserList();
            try {
                controller.deleteUserList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.getWriter().println("User list deleted");
        }
    }
}
