/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Windows10
 */
import model.PaginaDeStart;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;
import javax.swing.event.*;
public class Test {
    
    public boolean isAdmin(int id)
    {
        VMS vms = VMS.getInstance();
        
        Set<User> users = vms.getUsers();
        Iterator it = users.iterator();
        while(it.hasNext())
        {
            User i = (User)it.next();
            if(i.userID == id)
                if(i.userStatus == i.userStatus.ADMIN)
                    return true;
        }
        return false;
    }
    
    public boolean isGuest(int id)
    {
        VMS vms = VMS.getInstance();
        
        Set<User> users = vms.getUsers();
        Iterator it = users.iterator();
        while(it.hasNext())
        {
            User i = (User)it.next();
            if(i.userID == id)
                if(i.userStatus == i.userStatus.GUEST)
                    return true;
        }
        return false;
    }
    
    public boolean checkLogin(String user, String password)
    {
        boolean isCorrect = false;
        VMS vms = VMS.getInstance();
        Set<User> users = vms.getUsers();
        Iterator it = users.iterator();
        while(it.hasNext())
        {
            User i = (User)it.next();
            if(i.userName.equals(user) && i.userPassword.equals(password))
            {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }
    
    public boolean checkAdmin(String user)
    {
        boolean isCorrect = false;
        VMS vms = VMS.getInstance();
        Set<User> users = vms.getUsers();
        Iterator it = users.iterator();
        while(it.hasNext())
        {
            User i = (User)it.next();
            if(i.userName.equals(user) && i.userStatus == i.userStatus.ADMIN)
            {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }
    
    public void runEvents(int testNumber)
    {
        VMS x = VMS.getInstance();
     
        try {
            String events = "./VMStests/test0" + testNumber + "/input/events.txt";
            RandomAccessFile raf = new RandomAccessFile(events,"r");
            String s;
            String currentDate;
            currentDate = raf.readLine();
            s = raf.readLine();
            Test test = new Test();
            while((s = raf.readLine()) != null)
            {
                String[] arrOfStr = s.split(";");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                switch(arrOfStr[1]) 
                {
                    case "addCampaign":
                        if(test.isAdmin(Integer.parseInt(arrOfStr[0])))
                        {
                            int id = Integer.parseInt(arrOfStr[2]);
                            String name = arrOfStr[3];
                            String description = arrOfStr[4];
                            LocalDateTime start_date = LocalDateTime.parse(arrOfStr[5], formatter);
                            LocalDateTime end_date = LocalDateTime.parse(arrOfStr[6], formatter);
                            int budget = Integer.parseInt(arrOfStr[7]);
                            char strategy_type = arrOfStr[8].charAt(0);
                            
                            int bec = 0;
                            if(LocalDateTime.parse(currentDate, formatter).isBefore(start_date))
                                bec = 0;
                            else if(LocalDateTime.parse(currentDate, formatter).isAfter(start_date) && LocalDateTime.parse(currentDate, formatter).isBefore(end_date))
                                bec = 1;
                            else if(LocalDateTime.parse(currentDate, formatter).isAfter(end_date))
                                bec = 2;
                            Campaign newCampaign = new Campaign(id,name,description,start_date,end_date,budget,strategy_type,bec);
                            x.addCampaign(newCampaign);
                        }
                        break;
                    case "editCampaign":
                        if(test.isAdmin(Integer.parseInt(arrOfStr[0])))
                        {
                            int id = Integer.parseInt(arrOfStr[2]);
                            String name = arrOfStr[3];
                            String description = arrOfStr[4];
                            LocalDateTime start_date = LocalDateTime.parse(arrOfStr[5], formatter);
                            LocalDateTime end_date = LocalDateTime.parse(arrOfStr[6], formatter);
                            int budget = Integer.parseInt(arrOfStr[7]);
                            
                            Campaign newCampaign = new Campaign(id,name,description,start_date,end_date,budget,'A',0);
                            x.updateCampaign(id, newCampaign);
                        }
                        break;
                    case "cancelCampaign":
                        if(test.isAdmin(Integer.parseInt(arrOfStr[0])))
                        {
                            int id = Integer.parseInt(arrOfStr[2]);
                            
                            x.cancelCampaign(id);
                        }
                        break;
                    case "generateVoucher":
                        if(test.isAdmin(Integer.parseInt(arrOfStr[0])))
                        {                            
                            int id = Integer.parseInt(arrOfStr[2]);
                            String email = arrOfStr[3];
                            String voucher_type = arrOfStr[4];
                            float value = Float.parseFloat(arrOfStr[5]);
                            
                            Campaign campaign = x.getCampaign(id);
                            campaign.generateVoucher(email, voucher_type, value);
                        }
                        break;
                    case "redeemVoucher":
                        if(test.isAdmin(Integer.parseInt(arrOfStr[0])))
                        {
                            
                            int id = Integer.parseInt(arrOfStr[2]);
                            int voucher_id = Integer.parseInt(arrOfStr[3]);
                            LocalDateTime local_date = LocalDateTime.parse(arrOfStr[4], formatter);
                            
                            Campaign campaign = x.getCampaign(id);
                            model.HashSet<NintendoGift> vouchers = campaign.getVouchers();
                            Iterator it = vouchers.iterator();
                            while(it.hasNext())
                            {
                                NintendoGift i = (NintendoGift)it.next();
                                if(i.giftID == voucher_id)
                                {
                                    campaign.redeemVoucher(i.giftCode, local_date);
                                    break;
                                }
                            }
                        }
                        break;
                    case "getVouchers":
                        if(test.isGuest(Integer.parseInt(arrOfStr[0])))
                        { 
                            model.HashSet<NintendoGift> userVouchersSet = new HashSet<NintendoGift>();
                            int id = Integer.parseInt(arrOfStr[0]);
                            Iterator it = x.getUsers().iterator();
                            while(it.hasNext())
                            {
                                User i = (User)it.next();
                                if(i.userID == id)
                                {
                                    Iterator it2 = i.userVouchersDictionary.entrySet().iterator();
                                
                                    while(it2.hasNext())
                                    {
                                        Map.Entry entry = (Map.Entry)it2.next();
                                        Set<NintendoGift> vouchers = (Set<NintendoGift>)entry.getValue();
                                        Iterator it3 = vouchers.iterator();
                                        while(it3.hasNext())
                                        {
                                            NintendoGift v = (NintendoGift)it3.next();
                                            userVouchersSet.add(v);
                                        }
                                    }
                                    break;
                                }
                            }
                            List<NintendoGift> list = new ArrayList<NintendoGift>((Collection<? extends NintendoGift>) userVouchersSet); 
                            Collections.sort(list, new Comparator()
                            {
                                public int compare(Object arg0, Object arg1)
                                {
                                   NintendoGift v0 = (NintendoGift)arg0;
                                   NintendoGift v1 = (NintendoGift)arg1;
                                    return Integer.compare(v0.giftID, v1.giftID);
                                }
                            }
                            ); 
                            System.out.println(list);
                            x.outputString.append(list.toString() + '\n');
                        }
                        break;
                    case "getObservers":
                        if(test.isAdmin(Integer.parseInt(arrOfStr[0])))
                        {
                            int id = Integer.parseInt(arrOfStr[2]);
                            Campaign campaign = x.getCampaign(id);
                            Set<User> observers = (Set<User>) campaign.getObservers();
                            
                            List<User> list = new ArrayList<User>(observers);
                            
                            Collections.sort(list, new Comparator()
                            {
                                public int compare(Object arg0, Object arg1)
                                {
                                    User u0 = (User)arg0;
                                    User u1 = (User)arg1;
                                    return Integer.compare(u0.userID, u1.userID);
                                }
                            }
                            ); 
                            System.out.println(list);
                            x.outputString.append(list.toString() + '\n');
                        }
                        break;
                    case "getNotifications":
                        if(test.isGuest(Integer.parseInt(arrOfStr[0])))
                        {
                            int id = Integer.parseInt(arrOfStr[0]);
                            Iterator it = x.getUsers().iterator();
                            while(it.hasNext())
                            {
                                User i = (User)it.next();
                                if(i.userID == id)
                                {
                                    ArrayList<Notification> notifications = i.userNotificationsList;
                                    System.out.println(notifications);
                                    x.outputString.append(notifications.toString() + '\n');
                                    break;
                                }
                            }
                        }
                        break;
                    case "getVoucher":
                        if(test.isAdmin(Integer.parseInt(arrOfStr[0])))
                        {
                            int id = Integer.parseInt(arrOfStr[2]);
                            Campaign campaign = x.getCampaign(id);
                            //campaign.executeStrategy();
                        }
                        break;
                    default:
                        System.out.println("No event match!");
                }
            }
            
            RandomAccessFile outputFile = new RandomAccessFile("output.txt", "rw");
            outputFile.setLength(0);
            outputFile.writeChars(x.outputString.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
      }
