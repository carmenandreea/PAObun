/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

/**
 *
 * @author Windows10
 */
import DB.DBConnection;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.PaginaDeStart;
import model.NintendoGift;
import model.NintendoVoucher;

public class DBGift {

    public DBGift()
    {
    }

    public void addGift (NintendoGift c) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        String sqlAdd = "INSERT INTO cities VALUES (?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(sqlAdd);
        statement.setString(1, c.getUser());
        statement.setFloat(2, c.getGiftId());
        statement.setString(3, c.getGiftCode());

        statement.executeUpdate();
        DBConnection.closeDBConnection((com.mysql.jdbc.Connection) connection);
    }

    public void removeGift(NintendoGift c) throws SQLException {
        removeGift(c.getID());
    }

    public void removeGift(String name) throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        String sqlDelete = "DELETE FROM gifts WHERE name = ?";
        PreparedStatement statement =  connection.prepareStatement(sqlDelete);
        statement.setString(1, name);
        statement.executeUpdate();
        DBConnection.closeDBConnection((com.mysql.jdbc.Connection) connection);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<NintendoGift> getGifts() throws SQLException {
        Connection connection = DBConnection.getDBConnection();
        ArrayList<NintendoGift> gifts = new ArrayList<>();

        String sqlSelect = "SELECT * FROM gifts";
        PreparedStatement statement =  connection.prepareStatement(sqlSelect);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())    {
            NintendoGift c;
            c = new NintendoVoucher(resultSet.getString(1), resultSet.getFloat(2), resultSet.getFloat(3));
            gifts.add(c);
        }
        DBConnection.closeDBConnection((com.mysql.jdbc.Connection) connection);
        return gifts;
    }
}