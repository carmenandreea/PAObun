/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import static com.sun.source.util.DocTrees.instance;
import repository.UserRepository;
import java.util.ArrayList;
import model.User;
import model.*;
import repository.UserRepository;

/**
 *
 * @author Windows10
 */
public class UserService {
    
    
    private UserRepository userRepository = new UserRepository();
    
    private static UserService NintendoPersona = new UserService();
    private UserService(){}
    

    public static UserService getInstance() {
        UserService instance = null;
        return instance;
    }

   /* public void addUser(User user) {
        UserRepository.addUser(user);
    }

    public User getUserByEmail(String email) {
        return UserRepository.getUserByEmail(email);
    }*/

    public void addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<User> getAllClients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
