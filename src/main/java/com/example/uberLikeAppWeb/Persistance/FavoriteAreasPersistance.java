package com.example.uberLikeAppWeb.Persistance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FavoriteAreasPersistance {
    public void insert(String driverID, String area){
        String sql = "INSERT INTO FavoriteAreas VALUES ('" + driverID + "','" + area + "')";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }
    public void remove(String driverID, String area){
        String sql = "DELETE FROM FavoriteAreas WHERE driverID='" + driverID + "' AND area='" + area + "'";
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
