/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Windows10
 */
public class Notification {
    enum NotificationType {
        EDIT,
        CANCEL
    }
    LocalDateTime notificationDate;
    NotificationType notificationStatus;
    int notificationCampaignID;
    Set<String> notificationVoucherCodesList = (Set<String>) new HashSet<String>();
    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        str.append(this.notificationCampaignID + ";");
        StringBuffer codesStr = new StringBuffer();
        codesStr.append("[");
        Iterator it = this.notificationVoucherCodesList.iterator();
        Object code = it.next();
        codesStr.append("" + code);
        while(it.hasNext())
        {
            codesStr.append(", " + it.next());
        }
        codesStr.append("]");
        str.append(codesStr);
        return str.toString();
    }
}
