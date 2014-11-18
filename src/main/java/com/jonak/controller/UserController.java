package com.jonak.controller;

// import custom
import com.jonak.lib.Emailer;
import com.jonak.lib.MySQLDatabase;
import com.jonak.lib.SessionLib;
import com.jonak.model.User;
import com.jonak.model.UserModel;

//import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

// import default

/**
 * user controller
 */
public class UserController extends BaseController
{
    public Vector<User> messages = new Vector<User>();

    public UserController(){ super(); }

    // test method
    public String test() throws SQLException
    {
        // this is how we will be using
        // get the user with id 1
        User user = User.find( 1 );

        if( user != null ) {
            System.out.printf( "Name: %s %s\n", user.getFirstName(), user.getLastName() );
            System.out.printf( "Email: %s \n", user.getEmail() );
            System.out.printf( "Address: %s \n", user.getAddress() );
            messages.add(user);
        } else {
            System.out.printf( "No user found!" );
        }
        return this.SUCCESS;
    }

    // new user register
    public String register() throws SQLException, ParseException
    {
        User nuser = new User();
        nuser.setEmail(ServletActionContext.getRequest().getParameter("email"));
        nuser.setPassword(ServletActionContext.getRequest().getParameter("password"));
        nuser.setFirstName(ServletActionContext.getRequest().getParameter("firstName"));
        nuser.setLastName(ServletActionContext.getRequest().getParameter("lastName"));
        nuser.setAddress(ServletActionContext.getRequest().getParameter("address"));
        nuser.setDistrict(ServletActionContext.getRequest().getParameter("district"));
        String month = ServletActionContext.getRequest().getParameter("month");
        String day = ServletActionContext.getRequest().getParameter("day");
        String year = ServletActionContext.getRequest().getParameter("year");

        String date_of_birth = month+"-"+day+"-"+year;
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date date = (Date)formatter.parse(date_of_birth);
        int timestamp = (int)date.getTime()/1000;
        System.out.println(timestamp);
        nuser.setDateOfBirth(timestamp);
        nuser.setGender(Integer.parseInt(ServletActionContext.getRequest().getParameter("gender")));
        nuser.setType(Integer.parseInt(ServletActionContext.getRequest().getParameter("type")));
        nuser.setLastLoginAt(0);
        nuser.setAllowMessage(Integer.parseInt(ServletActionContext.getRequest().getParameter("allow_message")));
        nuser.setStatus(1);
        Date date1 = new Date();
        timestamp = (int) date.getTime()/1000;
        nuser.setCreatedAt(timestamp);
        nuser.setKey("1");

        nuser.save();

        return this.SUCCESS;
    }

    // user login
    public void loginprocess() throws SQLException
    {
        // get params data
        String  email = this.request.getParameter("email"),
                password = this.request.getParameter("password");

        // check login
        User user = User.checkLogin(email, password);

        // if user login set session
        if( user != null ) {
            SessionLib.set("user_id", user.getId() );
            SessionLib.set("isLogin", "true" );

            try {
                this.response.sendRedirect("user/profile");
            } catch (Exception ex) {
                throw new RuntimeException("error redirecting: "+ex.getMessage());
            }

//            return this.SUCCESS;
        } else {
            // invalid email/pass
            try {
                this.response.sendRedirect("login-error");
            } catch (Exception ex) {
                throw new RuntimeException("error redirecting: "+ex.getMessage());
            }
            System.out.printf("No user found!");
        }
//        return this.ERROR;
    }

    public String forgetPassword() throws SQLException,ParseException
    {
        User nuser = User.getUser(); //get user id
        Date date = new Date();
        int timeStamp = (int) (date.getTime() / 1000); //generate key value
        nuser.setKey(Integer.toString(timeStamp));  //set key value
        nuser.save();
        Emailer.sendEmail(timeStamp); //send email to user
        return this.SUCCESS;
    }

    public String findUser() throws SQLException
    {
        User nuser = User.getUserId(); //get user id
        SessionLib.set("id", nuser.getId()); // saved id in the session
        return this.SUCCESS;
    }

    public String setPassword() throws SQLException
    {
        User nuser = User.find(SessionLib.get("user_id"));
        nuser.setPassword(ServletActionContext.getRequest().getParameter("password")); // reset password
        nuser.setKey("1"); //reset key value
        nuser.save();
        return this.SUCCESS;
    }

    public String noReset() throws SQLException
    {
        User nuser = User.getUserId();
        nuser.setKey("1"); //reset generated key
        nuser.save();
        return this.SUCCESS;
    }



    public Vector<User> getMessages() {
        return messages;
    }

    public void setMessages(Vector<User> messages) {
        this.messages = messages;
    }
}

