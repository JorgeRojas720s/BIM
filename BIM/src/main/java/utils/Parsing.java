/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author jitor
 */
public class Parsing {

//    public static List<User> usersList = new ArrayList<>();

    public Parsing() {
    }

    public static ArrayList<User> parsingUsers(String aux) {
        
        ArrayList<User> usersList = new ArrayList<>();
        
        System.out.println("Users:  " + aux);

        String[] users = aux.split(";");
        for (String user : users) {
            String[] userData = user.split("\\|");
            if (userData.length == 5) {
                String userId = userData[0].substring(1);
                String userName = userData[1];
                String userLastName = userData[2];
                String userStatus = userData[3];
                String userRole = userData[4].substring(0, userData[4].length() - 1);

                System.out.println("userId: " + userId);
                System.out.println("userName: " + userName);
                System.out.println("userLastName: " + userLastName);
                System.out.println("userStatus: " + userStatus);
                System.out.println("userRole: " + userRole);

                User userObj = new User(Integer.parseInt(userId), userName, userLastName, userStatus, userRole);
                usersList.add(userObj);
            }
        }

        System.out.println("\nLista de usuarios:\n");
        for (User user : usersList) {
            System.out.println("User: " + user.getName()+ " - Pass: " + user.getPassword());
        }
        
        return usersList;
    }

}
