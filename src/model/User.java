/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
/**
 *
 * @author Windows10
 */
public class User {
    
    int userID;
    String userEmail;
    String userName;
    protected String userPassword; 
    enum UserType {
        ADMIN,
        GUEST
    }
    UserType userStatus;
    //UserVoucherMap userVouchersDictionary = new UserVoucherMap();
    
    
     public Integer getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String name) {
        this.userName = name;
    }
    
     /*public User (String Name) {
        this.userName = Name;
        this.userID = ++userID;
    }*/
}

