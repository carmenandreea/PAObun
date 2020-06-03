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
    UserVoucherMap userVouchersDictionary = new UserVoucherMap();
    ArrayList<Notification> userNotificationsList = new ArrayList<>();
    public User(String srina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
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
    
    
      public void update(Notification notification)
    {
        Set<String> notificationCodesList = (Set<String>) new HashSet<String>();
        int campaignID = notification.notificationCampaignID;
        Iterator it = this.userVouchersDictionary.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
            if((int)entry.getKey() == campaignID)
            {
                Iterator vIt = ((Set<NintendoGift>)entry.getValue()).iterator();
                while(vIt.hasNext())
                {
                   NintendoGift i = (NintendoGift)vIt.next();
                    notificationCodesList.add(i.giftCode);
                }    
                break;
            }
        }

        Notification newNotification = new Notification();
        newNotification.notificationCampaignID = notification.notificationCampaignID;
        newNotification.notificationDate = notification.notificationDate;
        newNotification.notificationStatus = notification.notificationStatus;
        newNotification.notificationVoucherCodesList = notificationCodesList;
        this.userNotificationsList.add(newNotification);  
    }
    
    
    
  /*  class userVouchersDictionary {

        static void addVoucher(NintendoGift v) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public userVouchersDictionary() {
        }
    }*/
    
    public User(int id, String name, String password, String email, int typeBec)
    {
        this.userID = id;
        this.userName = name;
        this.userPassword = password;
        this.userEmail = email;
        
        if(typeBec == 0)
            this.userStatus = this.userStatus.ADMIN;
        else
            this.userStatus = this.userStatus.GUEST;
    }
    
    @Override
    public String toString()
    {
        return "[" + this.userID + ";" + this.userName + ";" + this.userEmail + ";" + this.userStatus + "]";
    }
    
   
    
}

