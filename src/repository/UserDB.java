/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import model.User;
import model.VMS;
/**
 *
 * @author Windows10
 */
public class UserDB {
    
     private TreeSet<User> users;

    public UserDB() {
        users = new TreeSet<>();
    }

    public UserDB (ArrayList<User> users) {
        this.users = new TreeSet<>(users);
    }

    public void addUser(User c)
    {
        users.add(c);
    }

    public Boolean removeCar(String re)
    {
        User toDel = null;
        for(User c : users)
            if (re.equals(c.getUserEmail()))
            {
                toDel = c;
                break;
            }

        if(toDel != null)
            return false;
       else {
            users.remove(toDel);
            return true;
         }
    }

    public User getUserByEmail(String regNr) throws SQLException {
         for (Iterator<User> it = users.iterator(); it.hasNext();) {
             //User = it.next();
             User c = null;
                 return c;
         }
        return null;
    }

    public ArrayList<User>  getUsers() {
        return new ArrayList<>(users);
    }
    
    
}
