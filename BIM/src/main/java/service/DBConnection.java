/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;

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
    
    
    public void registerUsers(User user){
       
        try { connect(dbName);
        
        String sql = "INSERT INTO tbl_users (usr_id,usr_name,usr_lastName,usr_status,usr_username,usr_email, usr_password, usr_role) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setInt(1, user.getId());
        statement.setString(2, user.getName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getStatus());
        statement.setString(5, user.getUsername());
        statement.setString(6, user.getEmail());
        statement.setString(7, user.getPassword());
        statement.setString(8, user.getRole());

        statement.executeUpdate();
        statement.close();
        disconnect();
         
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se guardo el user");
        }
    } 
    
    
    public String logInUser(String usernameOrEmail, String password) {
        try {
            connect(dbName);

            String sql = "SELECT usr_role FROM tbl_users WHERE (usr_username = ? OR usr_email = ?) AND usr_password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usernameOrEmail);
            statement.setString(2, usernameOrEmail);
            statement.setString(3, password);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String role = result.getString("usr_role");
                return role; 
            }

            statement.close();
            disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al buscar el user");
        }

        return null; 
    }

        public List<User> getUsers() {
        List<User> listUsers = new ArrayList<>();
        
        try {
            connect(dbName);
            
            String query = "SELECT * FROM tbl_users ORDER BY usr_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
               
                Integer userId = resultSet.getInt("usr_id");
                String userName = resultSet.getString("usr_name");
                String userLastName = resultSet.getString("usr_lastName");
                String userStatus = resultSet.getString("usr_status");
                 String userRole = resultSet.getString("usr_role");
   
                
                User users = new User(userId, userName,userLastName,userStatus,userRole);
                listUsers.add(users);
            }

            resultSet.close();
            statement.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listUsers;
    }
    
    
}
