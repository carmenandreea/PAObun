/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import repository.UserRepository;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author Windows10
 */
public class UserService {
    
    
    private UserRepository userRepository = new UserRepository();
    
    private static UserService NintendoPersona = new UserService();
    private UserService(){}
    
   
  
}
