package com.example.uberLikeAppWeb.Persistance;
import com.example.uberLikeAppWeb.Core.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverPersistance {
    public void insert(Driver driver){
        String sql = "INSERT INTO Drivers(driverID,email,password,mobileNumber,nationalID,drivingLicense,balance,isRegistered) VALUES ('" + driver.getUserName() +
                "', '" + driver.getEmail() + "', '" + driver.getPassword() + "', '" + driver.getMobileNo() + "', '" + driver.getNationalID() + "', '" + driver.getDrivingLicense() +
                "', " + driver.getBalance() + ", false)";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public void insert(String sql){
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public void remove(String username){
        String sql = "DELETE FROM Drivers WHERE driverID='" + username +"'";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public boolean check(Driver driver){
        String sql = "SELECT * FROM Drivers WHERE driverID='" + driver.getUserName() + "' AND password='" + driver.getPassword() +"'";
        boolean check = false;
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next() == false || !rs.getBoolean("isRegistered")){
                check = false;
            }else{
                check = true;
            }
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return check;
    }
    public String getPendingDrivers(){
        String sql = "SELECT * FROM Drivers WHERE isRegistered=false";
        String result = "";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result += rs.getString("driverID");
                result += "\n";
            }
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return result;
    }
    public Driver getInfo(String username){
        String sql = "SELECT * FROM Drivers WHERE driverID='" + username + "'";
        Driver driver = new Driver();
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String email = rs.getString("email");
            String mobileNo = rs.getString("mobileNumber");
            String nationalID = rs.getString("nationalID");
            String drivingLicense = rs.getString("drivingLicense");
            double balance = rs.getDouble("balance");
            driver.setUserName(username);
            driver.setEmail(email);
            driver.setMobileNo(mobileNo);
            driver.setNationalID(nationalID);
            driver.setDrivingLicense(drivingLicense);
            driver.setBalance(balance);
            driver.registered();
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return driver;
    }
    public void addFavArea(String driverID, String area){
        FavoriteAreasPersistance fav = new FavoriteAreasPersistance();
        fav.insert(driverID, area);
    }
    public void removeFavArea(String driverID, String area){
        FavoriteAreasPersistance fav = new FavoriteAreasPersistance();
        fav.remove(driverID, area);
    }
    public String listRequests(Driver driver){
        DriverNotificationsPersistance notifications = new DriverNotificationsPersistance();
        return notifications.retrieve(driver.getUserName());
    }
    public void clearNotifications(String driverID){
        String sql = "DELETE FROM DriverNotifications WHERE driverID='" + driverID + "'";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public Connection connect(){
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
