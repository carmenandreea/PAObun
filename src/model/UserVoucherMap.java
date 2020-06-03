/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Windows10
 */
class UserVoucherMap extends ArrayMap<Integer,Set<NintendoGift>> {
    
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
            Set<NintendoGift> newList = new HashSet<NintendoGift>();
            newList.add(v);
            this.put(id, newList);
            isAdded = true;
        }
        return isAdded;
    }
    
    public boolean checkIfLast(NintendoGift v)
    {
        boolean isLast = true;
        int id = v.giftCampaignID;
        
        if(this.containsKey(id))
        {
            Set<NintendoGift> vList = (Set)this.get(id);
            for (Iterator<NintendoGift> it = vList.iterator(); it.hasNext();) {
                NintendoGift i = it.next();
                if(i.giftStatus == NintendoGift.giftStatusType.UNUSED)
                {
                    isLast = false;
                    break;
                }
            }
        }
        return isLast;
    }
}

