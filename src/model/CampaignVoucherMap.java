/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.HashSet;
import java.util.Set;
import model.ArrayMap;
import model.Campaign;
import model.CampaignVoucherMap;

/**
 *
 * @author Windows10
 */
public class CampaignVoucherMap extends ArrayMap  {
    public boolean addVoucher(NintendoGift v)
    {
        boolean isAdded = false;
        int id = v.giftCampaignID;
        if(this.containsKey(id))
        {
            Set<NintendoGift> vList = (Set)this.get(id);
            vList.add(v);
            isAdded = true;
        }
        else
        {
            Set<NintendoGift> newList = new HashSet<>();
            newList.add(v);
            this.put(id, newList);
            isAdded = true;
        }
        return isAdded;
}

} 