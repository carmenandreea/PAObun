/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import model.User;
import java.util.ArrayList;
/**
 *
 * @author Windows10
 */
public class UserRepository {
    
    private ArrayList<User> users;
    public UserRepository()
    {
        users= new ArrayList<>();
    }
  
  public void add (User client) {
        users.add(client);
    }


public User getUserByEmail(String Email) {
        for(User client : users) 
        {
            if(!(Email == null ? client.getUserEmail() == null : Email.equals(client.getUserEmail())))
            {
            } else {
                return client;
            }
        }
        return null;
    }
}