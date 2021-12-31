package com.example.uberLikeAppWeb.Core;

import com.example.uberLikeAppWeb.Persistance.CustomerNotificationsPersistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Offer {
    private int ID;
    private Driver driver;
    private String customerID;
    private String source;
    private String destination;
    private double price;

    public Offer(String customerName, String source, String destination, Driver driver, double price){
        this.customerID = customerName;
        this.source = source;
        this.destination = destination;
        this.driver = driver;
        this.price = price;
        String sql = "INSERT INTO Offers (customerID,driverID,source,destination,price) VALUES ('" + customerID +
                "', '" + driver.getUserName() + "', '" + source + "', '" + destination + "', " +price +")";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public void setID(int id){
        ID = id;
    }
    public int getID(){
        return ID;
    }
    public Offer getInfo(Offer offer){
        String sql = "SELECT * FROM Offers WHERE customerID='" + customerID + "' AND source ='" + source + "' AND destination='" + destination +"' AND price=" + price;
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int offerID = rs.getInt("ID");
            String customerID = rs.getString("customerID");
            String source = rs.getString("source");
            String destination = rs.getString("destination");
            double price = rs.getDouble("price");
            offer = new Offer(customerID,source,destination,this.driver,price);
            offer.setID(offerID);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return offer;
    }
    public void publishOffer(){
        Offer offer = getInfo(this);
        new CustomerNotificationsPersistance().insert(customerID, "You got an offer ID: "+ offer.getID() + " from driver " + driver.getUserName() + " to go from " + source + " to " + destination);
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
