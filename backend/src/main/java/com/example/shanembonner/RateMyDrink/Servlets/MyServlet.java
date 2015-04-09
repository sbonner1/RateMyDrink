/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.shanembonner.RateMyDrink.Servlets;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Drink;
import com.rateMyDrink.modelClasses.Liquor;
import com.rateMyDrink.modelClasses.MixedDrink;
import com.rateMyDrink.modelClasses.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AddBeer;
import controllers.AddDrink;
import controllers.AddLiquor;
import controllers.AddMixedDrink;
import controllers.AddUser;
import controllers.DeleteDrink;
import controllers.DeleteUser;
import controllers.DeleteUserList;
import controllers.GetBeer;
import controllers.GetDrinkList;
import controllers.GetLiquor;
import controllers.GetMixedDrink;
import controllers.GetUser;
import controllers.GetUserList;

public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        String id_param = req.getParameter("id");
        String pathInfo = req.getPathInfo(); //path

        System.out.println(req.getQueryString());

        if(id_param == null){
            System.out.println("no id present");
        }

        if(action == null){
            System.out.println("action parameter is null");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.setContentType("text/plain");
            resp.getWriter().println("action parameter is null");
            return;
        }

        if(action.equals("getBeer")){

            if(id_param == null){
                resp = setHttpResponse(resp, "id parameter not found.", "text/plain", HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Beer beer = null;
            GetBeer controller = new GetBeer();

            int id = Integer.parseInt(id_param, 10);

            try{
                beer = controller.getBeer(id);
            }catch (SQLException e){
                e.printStackTrace();
            }

            if(beer == null){
                //no such item
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("No such beer");
                return;
            }

            System.out.println("beer found");
            System.out.println("name: " + beer.getDrinkName());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), beer);
        }

        if(action.equals("getLiquor")){
            if(pathInfo.startsWith("/")){
                pathInfo = pathInfo.substring(1);
            }
            int id = Integer.parseInt(pathInfo, 10);
            Liquor liquor = null;
            GetLiquor controller = new GetLiquor();

            try {
                liquor = controller.getLiquor(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(liquor == null){
                //no such item
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plan");
                resp.getWriter().println("No such liquor");
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), liquor);
        }

        if(action.equals("getMixedDrink")){
            if(pathInfo.startsWith("/")){
                pathInfo = pathInfo.substring(1);
            }
            int id = Integer.parseInt(pathInfo, 10);
            MixedDrink mixedDrink = null;

            GetMixedDrink controller = new GetMixedDrink();

            try{
                mixedDrink = controller.getMixedDrink(id);
            }catch(SQLException e) {
                e.printStackTrace();
            }

            if(mixedDrink == null){
                //no such mixed drink
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plan");
                resp.getWriter().println("No such mixed drink");
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), mixedDrink);
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

        if(action.equals("getDrinkList")){                      //TODO: need to send drink ids with this list <---
            GetDrinkList getController = new GetDrinkList();
            List<Drink> drinkList = null;

            try {
                drinkList = getController.getDrinkList();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //print drinkList to user's terminal
            String[] drinkNameList = new String[drinkList.size()]; //return the list of usernames for the scoreboard
            //as an array of strings to be displayed
            int count = 0;
            for(Drink drink: drinkList){
                String drinkName = drink.getDrinkName();
                drinkNameList[count] = drinkName;
                count++;
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), drinkList);

        }
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

        /**
         * to add a new beer object to the database
         */
        if(action.equals("addBeer")){
            Beer newBeer = new Beer();
            newBeer = JSON.getObjectMapper().readValue(req.getReader(), Beer.class);

            AddBeer controller = new AddBeer();
            boolean success = false;

            try{
                success = controller.addBeer(newBeer);
            }catch(SQLException e){
                e.printStackTrace();
            }

            if (success) {
                System.out.println("success adding beer");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                JSON.getObjectMapper().writeValue(resp.getWriter(), newBeer);

            }else{
                System.out.println("failed to add beer");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("failed to add beer to database.");
            }
        }

        /**
         * to add a new liquor object to the database
         */
        if(action.equals("addLiquor")) {
            System.out.println("action is addLiquor.");

            Liquor newLiquor = JSON.getObjectMapper().readValue(req.getReader(), Liquor.class);

            if(newLiquor == null){
                System.out.println("newLiquor object is null.");
                return;
            }

            AddLiquor controller = new AddLiquor();
            boolean success = false;

            try {
                System.out.println("attempting to add liquor to database.");
                success = controller.addLiquor(newLiquor);
            }catch(SQLException e){
                e.printStackTrace();
            }

            if (success) {
                System.out.println("success adding liquor");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                JSON.getObjectMapper().writeValue(resp.getWriter(), newLiquor);
                return;
            }else{
                System.out.println("failed to add liquor");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("failed to add liquor to database.");
                return;
            }
        }

        /**
         * to add a drink object to the database
         */
        if(action.equals("addDrink")){
            Drink newDrink = null;
            System.out.println("action: addDrink");
            System.out.println("pathinfo: " + pathInfo);
            newDrink = JSON.getObjectMapper().readValue(req.getReader(), Drink.class);

            AddDrink controller = new AddDrink();
            boolean success = false;

            try{
                success = controller.addNewDrink(newDrink);
            }catch(SQLException e){
                e.printStackTrace();
            }

            if (success) {
                System.out.println("success adding drink");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                JSON.getObjectMapper().writeValue(resp.getWriter(), newDrink);

            }else{
                System.out.println("failed to add drink");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("failed to add drink to database.");
            }
        }

        /**
         * to add a mixedDrink object to the database
         */
        if(action.equals("addMixedDrink")){
            System.out.println("action is addMixedDrink");
            MixedDrink mixedDrink = null;
            mixedDrink = JSON.getObjectMapper().readValue(req.getReader(), MixedDrink.class);

            AddMixedDrink controller = new AddMixedDrink();
            boolean success = false;

            try {
                System.out.println("trying to add mixed drink to database.");
                success = controller.addMixedDrink(mixedDrink);
            }catch(SQLException e){
                e.printStackTrace();
            }

            if (success) {
                System.out.println("success adding mixed drink");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.setContentType("application/json");
                JSON.getObjectMapper().writeValue(resp.getWriter(), mixedDrink);
                return;
            }else{
                System.out.println("failed to add mixed drink");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("text/plain");
                resp.getWriter().println("failed to add mixed drink to database.");
                return;
            }
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


        if(action.equals("loginUser")){

        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String action = req.getParameter("action");

        //if there is an item name, delete it. if not, delete whole database
        if(action.equals("deleteDrink")){
            if(pathInfo.startsWith("/")){
                pathInfo = pathInfo.substring(1);
            }

            Drink drink = JSON.getObjectMapper().readValue(req.getReader(), Drink.class);
            DeleteDrink controller = new DeleteDrink();

            try {
                controller.deleteDrink(drink);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            JSON.getObjectMapper().writeValue(resp.getWriter(), drink);

        }
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

    /**
     * sets the HttpServletResponse
     *
     * @param resp the HttpServletResponse
     * @param msg the message to be sent to the user
     * @param contentType the content-type of the message
     * @param status the HTTP Status code
     * @return the HttpServletResponse with above parameters applied
     * @throws IOException
     */
    private HttpServletResponse setHttpResponse(HttpServletResponse resp, String msg, String contentType, int status) throws IOException{

        System.out.println(msg);
        resp.setStatus(status);
        resp.setContentType(contentType);
        resp.getWriter().println(msg);

        return resp;
    }

}
