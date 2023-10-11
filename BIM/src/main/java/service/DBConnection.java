/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Users;

/**
 *
 * @author jitor
 */
public class DBConnection {
    
    private static DBConnection instance = null;
    private Connection connection;
    private final String dbName = "db_bim";
 

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public void connect(String dbName) {
        String url = "jdbc:mariadb://localhost:3306/" + dbName;
        String user = "root";
        String pass = "";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println("Error, no se ha podido cargar el MariaDB JDBC Driver");
        } finally {
            System.out.println("CONECTADO!!");
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("DESCONECTADO!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void registerUsers(Users user){
       
        try { connect(dbName);
        
        String sql = "INSERT INTO tbl_users (usr_username,usr_email, usr_password, usr_role) VALUES (?,?,?,?)";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole());

        statement.executeUpdate();
        statement.close();
        disconnect();
         
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se guardo el user");
        }
    }
    
    
    
}
