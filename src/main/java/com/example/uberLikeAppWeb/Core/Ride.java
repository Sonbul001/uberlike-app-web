package com.example.uberLikeAppWeb.Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ride {
    private int ID;
    private String source;
    private String destination;
    private String customerID;
    private String driverID;
    private double price;
    private boolean isCompleted = false;


    public Ride(){
        this.source = "";
        this.destination = "";
        this.customerID = "";
        this.driverID = "";
        this.price = 0.0;
    }
    public Ride(String source, String destination, String customerID, String driverID, double price){
        this.source = source;
        this.destination = destination;
        this.customerID = customerID;
        this.driverID = driverID;
        this.price = price;
        String sql = "INSERT INTO Rides (customerID, driverID, source, destination, price) VALUES ('" + customerID + "', '" + driverID + "', '" + source + "', '" + destination + "'," + price +")";
        String sql2 = "SELECT ID FROM Rides WHERE customerID='" + customerID + "' AND driverID='" + driverID +"' AND source='" + source +"' AND destination='"+destination +"'";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.executeQuery(sql2);
            rs.next();
            this.ID = rs.getInt("ID");
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }

    }
    public Ride getInfo(int offerID){
        String sql = "SELECT * FROM Offers WHERE ID= " + offerID;
        Ride ride = new Ride();
        String customerID = "";
        String driverID = "";
        String source = "";
        String destination = "";
        double price = 0;
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            customerID = rs.getString("customerID");
            driverID = rs.getString("driverID");
            source = rs.getString("source");
            destination = rs.getString("destination");
            price = rs.getDouble("price");
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        ride = new Ride(source,destination,customerID,driverID,price);
        return ride;
    }
    public Ride getInfo(String driverID){
        String sql = "SELECT * FROM Rides WHERE driverID='" + driverID+"'";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            this.customerID = rs.getString("customerID");
            this.driverID = rs.getString("driverID");
            this.source = rs.getString("source");
            this.destination = rs.getString("destination");
            this.price = rs.getDouble("price");
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return this;
    }
    public void completed(){
        isCompleted = true;
        String sql = "UPDATE Rides SET isCompleted=true WHERE customerID='" + customerID + "' AND driverID='" + driverID +"'";
        String sql2 = "DELETE FROM Offers WHERE customerID='" + customerID + "' AND driverID='" + driverID +"' AND source='" + source +"' AND destination='"+destination +"'";
        String sql3 = "DELETE FROM Requests WHERE customerID='" + customerID + "' AND source='" + source +"' AND destination='"+destination +"'";
        String sql4 = "UPDATE Drivers SET balance = balance + " + price + " WHERE driverID='" + driverID +"'";
        try{
            Connection conn = connect();
            System.out.println("Ride.completed()");
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
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
