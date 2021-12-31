package com.example.uberLikeAppWeb.Core;

import com.example.uberLikeAppWeb.Persistance.DriverNotificationsPersistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RideRequest {
    private String source;
    private String destination;
    private Customer customer;

    public RideRequest(String source, String destination, Customer customer){
        this.source = source;
        this.destination = destination;
        this.customer = customer;
        String sql = "INSERT INTO Requests (source, destination, customerID) VALUES ('" + source + "', '" + destination + "', '" +
                customer.getUserName() + "')";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public void publishRequest(){
        String sql2 = "SELECT driverID FROM FavoriteAreas WHERE area='" + source +"'";
        DriverNotificationsPersistance notification = new DriverNotificationsPersistance();
        ArrayList<String> driverList = new ArrayList<>();
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);
            while(rs.next()){
                String driverID = rs.getString("driverID");
                driverList.add(driverID);
                System.out.println(driverID);
                System.out.println(customer.getUserName());
            }
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        System.out.println("RideRequest.publishRequest()");
        for(String str : driverList){
            notification.insert(str, customer.getUserName() + " published a request from " + source + " to " +destination);
        }
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
