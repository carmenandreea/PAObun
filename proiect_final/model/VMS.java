/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Windows10
 */
public class VMS {
    
  HashSet<Campaign> vmsCampaignsList = new HashSet<Campaign>();
    HashSet<User> vmsUsersList = new HashSet<User>();
    LocalDateTime applicationDate;
    StringBuffer outputString = new StringBuffer();
    
    public Set<Campaign> getCampaigns()
    {
        return (Set<Campaign>) this.vmsCampaignsList;
    }
    
    public Campaign getCampaign(Integer id)
    {
        Iterator it = this.vmsCampaignsList.iterator();
        while(it.hasNext())
        {
            Campaign i = (Campaign)it.next();
            if(i.campaignID == id)
                return i;
        }
        return null;
    }
    
    public void addCampaign(Campaign campaign)
    {
        this.vmsCampaignsList.add(campaign);  
    }
    
    public void updateCampaign(Integer id, Campaign campaign)
    {
        Campaign oldCampaign = this.getCampaign(id);
        if(oldCampaign != null)
        {
            if(oldCampaign.campaignStatus == oldCampaign.campaignStatus.NEW)
            {
                oldCampaign.campaignName = campaign.campaignName;
                //oldCampaign.campaignDescription = campaign.campaignDescription;
                oldCampaign.campaignStartDate = campaign.campaignStartDate;
                oldCampaign.campaignEndDate = campaign.campaignEndDate;
                
                int campaignDistributedGiftsNumber = oldCampaign.campaignTotalGiftsNumber - oldCampaign.campaignAvailableGiftsNumber;
                if(campaign.campaignTotalGiftsNumber >= campaignDistributedGiftsNumber)
                {
                    oldCampaign.campaignTotalGiftsNumber = campaign.campaignTotalGiftsNumber;
                    oldCampaign.campaignAvailableGiftsNumber = oldCampaign.campaignTotalGiftsNumber - campaignDistributedGiftsNumber;
                }
                
                if(LocalDateTime.now().isAfter(oldCampaign.campaignEndDate))
                {
                    oldCampaign.campaignStatus = oldCampaign.campaignStatus.EXPIRED;
                }
                
                /*if(this.applicationDate.isAfter(oldCampaign.campaignEndDate))
                {
                    oldCampaign.campaignStatus = oldCampaign.campaignStatus.EXPIRED;
                }*/
            }
            else if(oldCampaign.campaignStatus == oldCampaign.campaignStatus.STARTED)
            {
                oldCampaign.campaignName = campaign.campaignName;
                oldCampaign.campaignEndDate = campaign.campaignEndDate;
                int campaignDistributedGiftsNumber = oldCampaign.campaignTotalGiftsNumber - oldCampaign.campaignAvailableGiftsNumber;
                if(campaign.campaignTotalGiftsNumber >= campaignDistributedGiftsNumber)
                {
                    oldCampaign.campaignTotalGiftsNumber = campaign.campaignTotalGiftsNumber;
                    oldCampaign.campaignAvailableGiftsNumber = oldCampaign.campaignTotalGiftsNumber - campaignDistributedGiftsNumber;
                }
                
                if(LocalDateTime.now().isAfter(oldCampaign.campaignEndDate))
                {
                    oldCampaign.campaignStatus = oldCampaign.campaignStatus.EXPIRED;
                }
                
                /*if(this.applicationDate.isAfter(oldCampaign.campaignEndDate))
                {
                    oldCampaign.campaignStatus = oldCampaign.campaignStatus.EXPIRED;
                }*/
            }
            
            Notification notification = new Notification();
            notification.notificationCampaignID = oldCampaign.campaignID;
            notification.notificationDate = this.applicationDate;
            notification.notificationStatus = notification.notificationStatus.EDIT;
            
            //oldCampaign.notifyAllObservers(notification);
        }
    }
    
    public void cancelCampaign(Integer id)
    {
        Campaign oldCampaign = this.getCampaign(id);
        if(oldCampaign != null)
        {
            if(oldCampaign.campaignStatus == oldCampaign.campaignStatus.NEW || oldCampaign.campaignStatus == oldCampaign.campaignStatus.STARTED)
            {
                oldCampaign.campaignStatus = oldCampaign.campaignStatus.CANCELLED;
            }
            
            Notification notification = new Notification();
            notification.notificationCampaignID = oldCampaign.campaignID;
            notification.notificationDate = this.applicationDate;
            notification.notificationStatus = notification.notificationStatus.CANCEL;
            
           // oldCampaign.notifyAllObservers(notification);
        }
    }
        
    public Set<User> getUsers()
    {
        return (Set<User>) this.vmsUsersList;
    }
    
    public void addUser(User user)
    {
        this.vmsUsersList.add(user);
    }
    
    private static VMS vmsSingleton = null; 
    private VMS() 
    { 
        try{
            //RandomAccessFile raf1 = new RandomAccessFile("./VMStests/test09/input/campaigns.txt","r");
            RandomAccessFile raf1 = new RandomAccessFile("./campaigns.txt","r");
            String s;
            s = raf1.readLine();
            s = raf1.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime applicationCurrentDate = LocalDateTime.parse(s, formatter);
            
            while((s = raf1.readLine()) != null)
            {
                String[] arrOfStr = s.split(";");
                
                int id = Integer.parseInt(arrOfStr[0]);
                String name = arrOfStr[1];
                String description = arrOfStr[2];
                LocalDateTime start_date = LocalDateTime.parse(arrOfStr[3], formatter);
                LocalDateTime end_date = LocalDateTime.parse(arrOfStr[4], formatter);
                int budget = Integer.parseInt(arrOfStr[5]);
                char stategy_type = arrOfStr[6].charAt(0);
                
                int bec = 0;
                
                if(applicationCurrentDate.isBefore(start_date))
                    bec = 0;
                else if(applicationCurrentDate.isAfter(start_date) && applicationCurrentDate.isBefore(end_date))
                    bec = 1;
                else if(applicationCurrentDate.isAfter(end_date))
                    bec = 2;
                
                this.applicationDate = applicationCurrentDate;
                Campaign newCampaign = new Campaign(id, name, description, start_date, end_date, budget, stategy_type, bec);
                this.addCampaign(newCampaign);
            }
            
            //RandomAccessFile raf2 = new RandomAccessFile("./VMStests/test09/input/users.txt","r");
            RandomAccessFile raf2 = new RandomAccessFile("./users.txt","r");
            s = raf2.readLine();
                
            while((s = raf2.readLine()) != null)
            {
                String[] arrOfStr = s.split(";");
                
                int id = Integer.parseInt(arrOfStr[0]);
                String name = arrOfStr[1];
                String password = arrOfStr[2];
                String email = arrOfStr[3];
                int bec = 0;
                
                if(arrOfStr[4].equals("ADMIN"))
                    bec = 0;
                else
                    bec = 1;
                
                User newUser = new User(id, name, password, email, bec);
                this.addUser(newUser);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    } 
    
    public static VMS getInstance() 
    { 
        if (vmsSingleton == null) 
            vmsSingleton = new VMS(); 
        return vmsSingleton; 
    } 
}
