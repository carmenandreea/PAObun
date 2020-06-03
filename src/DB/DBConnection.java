/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import com.mysql.jdbc.Connection;
import java.sql.*;
import javax.print.DocFlavor.STRING;

/**
 *
 * @author Windows10
 */

//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.print.DocFlavor.STRING;

public class DBConnection {

    /*private static final String USERNAME="root";
    private static final String PASSWORD="9975carmen";
    private static final String CONN_STRING="jdbc:mysql://localhost:3306/nintendo";
    
    public static void main (STRING[] args) {
        Connection conn=null;
        
        try {
            conn=(Connection) DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected");
        }
        catch (SQLException e){
            System.err.println(e);
        }
    }*/
    
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String username = "root";
            String password = "9975carmen";
            String url = "jdbc:mysql://localhost:3306/nintendo?autoReconnect=true";
            this.connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }


}