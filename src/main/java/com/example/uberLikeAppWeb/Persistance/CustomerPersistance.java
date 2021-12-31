package com.example.uberLikeAppWeb.Persistance;
import com.example.uberLikeAppWeb.Core.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerPersistance {
    public void insert(Customer customer){
        String sql = "INSERT INTO Customers (customerID, email, password, mobileNumber, isRegistered) VALUES ('" + customer.getUserName() + "', '" + customer.getEmail() +
                "', '" + customer.getPassword() + "', '" + customer.getMobileNo() + "', true)";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
            customer.registered();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public boolean check(Customer customer){
        String sql = "SELECT * FROM Customers WHERE customerID='" + customer.getUserName() + "' AND password='" + customer.getPassword() +"'";
        boolean check = false;
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next() == false){
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
    public Customer getInfo(String username){
        String sql = "SELECT * FROM Customers WHERE customerID='" + username + "'";
        Customer customer = new Customer();
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String password = rs.getString("password");
            String email = rs.getString("email");
            String mobileNo = rs.getString("mobileNumber");
            customer.setUserName(username);
            customer.setEmail(email);
            customer.setMobileNo(mobileNo);
            customer.setPwd(password);
            customer.registered();
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
        return customer;
    }
    public String listOffers(Customer customer){
        CustomerNotificationsPersistance notification = new CustomerNotificationsPersistance();
        return notification.retrieve(customer.getUserName());
    }
    public void rateCompletedRide(int rating, Customer customer){
        String sql = "UPDATE Rides SET rating =" + rating + " WHERE ID = (SELECT ID FROM Rides WHERE id=(SELECT max(ID) FROM Rides) AND customerID ='" + customer.getUserName() +"')";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
            customer.registered();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public void clearNotifications(String customerID){
        String sql = "DELETE FROM CustomerNotifications WHERE customerID='" + customerID + "'";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
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
