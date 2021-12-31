package com.example.uberLikeAppWeb.Persistance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverNotificationsPersistance {
    public void insert(String driverID, String message){
        String sql = "INSERT INTO DriverNotifications VALUES ('" + driverID + "', '" + message +"')";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public String retrieve(String driverID){
        String sql = "SELECT * FROM DriverNotifications WHERE driverID ='" + driverID +"'";
        String message = "";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                message += rs.getString("message");
                message += "\n";
            }
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return message;
    }
    public Connection connect() {
        Connection conn = null;
        String url = "jdbc:sqlite:uberlike.db";
        try {
            conn = DriverManager.getConnection(url);
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
