/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import model.Campaign;
import model.User;
import services.UserService;
import services.VMS;
/**
 *
 * @author Windows10
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        UserService userService = UserService.getInstance();

        userService.addUser(new User("Srina"));
        userService.addUser(new User("Silvian"));
        

        ArrayList<User> users = userService.getAllClients();

        System.out.println("Show all users:");

        for (Iterator<User> it = users.iterator(); it.hasNext();) {
            User user = it.next();
            System.out.println(user);
        }

        System.out.println("\nShow all students:");

        String name="Ana";
        String description="sta aici";
        int buget=500;
        char strategy_type;
        int becStatus=0;
        try{
         //   VMS.addCampaign(new Campaign(getCampaignId(), name, description, budget,  becStatus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nShow all events:");

}
}