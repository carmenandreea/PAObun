/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
//import model.Campaign;
import model.User;

/**
 *
 * @author Windows10
 */

/*
public class VMS {

    public static void addCampaign(Campaign campaign) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static VMS getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
      Set<Campaign> vmsCampaignsList = new HashSet<>();
    Set<User> vmsUsersList = new HashSet<>();
    LocalDateTime applicationDate;
    StringBuffer outputString = new StringBuffer();
    
    public Set<Campaign> getCampaigns()
    {
        return this.vmsCampaignsList;
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
    
   /* public void addCampaign(Campaign campaign)
    {
        this.vmsCampaignsList.add(campaign);  
    }*/

  /*  public Set<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}*/
