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
import model.User;
import services.UserService;
import model.PaginaDeStart;
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

        userService.addUser(new User("Sonia"));
        userService.addUser(new User("Simona"));
        

        ArrayList<User> users = userService.getAllClients();

        System.out.println("Show all users:");

        for (Iterator<User> it = users.iterator(); it.hasNext();) {
            User user = it.next();
            System.out.println(user);
        }

        System.out.println("\nShow all students:");

        String name="Nintendo";
        String description="campanie de vara";
        int buget=500;
        char strategy_type;
        int becStatus=0;
        try{
         //   VMS.addCampaign(new Campaign(getCampaignId(), name, description, budget,  becStatus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nShow all events:");
        System.out.println("Connected");
        
         //Test object = new Test();
        //object.runEvents(9);
        
        PaginaDeStart obiect = new PaginaDeStart();
        obiect.setVisible(true);
        
        
        
        
        
}
    
    
    
}
