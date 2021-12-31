package com.example.uberLikeAppWeb.Persistance;
import com.example.uberLikeAppWeb.Core.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPersistance {
    public void insert(Admin admin){
        String sql = "INSERT INTO Admins VALUES ('" + admin.getUserName() + "', '" + admin.getPassword() + "')";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public boolean check(Admin admin){
        String sql = "SELECT * FROM Admins WHERE userName='" + admin.getUserName() + "' AND password='" + admin.getPassword() +"'";
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
