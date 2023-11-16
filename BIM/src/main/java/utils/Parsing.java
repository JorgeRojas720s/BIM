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

    public static ArrayList<User> parsingAllUsers(String aux) {

        ArrayList<User> usersList = new ArrayList<>();

        System.out.println("Users:  " + aux);

        String[] users = aux.split(";");

        for (String user : users) {

            String[] userData = user.split("\\|");
            if (userData.length == 5) {

                String id = userData[0].substring(1);
                String name = userData[1];
                String lastName = userData[2];
                String status = userData[3];
                String role = userData[4].substring(0, userData[4].length());

                System.out.println("userId: " + id);
                System.out.println("Name: " + name);
                System.out.println("userLastName: " + lastName);
                System.out.println("userStatus: " + status);
                System.out.println("userRole: " + role);

                User userObj = new User(Integer.parseInt(id), name, lastName, status, role);
                usersList.add(userObj);
            }
        }

        System.out.println("\nLista de usuarios:\n");
        for (User user : usersList) {
            System.out.println("User: " + user.getName() + " - Pass: " + user.getLastName());
        }

        return usersList;
    }

    public static String[] parsingUser(String aux) {

        String[] user = aux.split("\\|");

        for (String i : user) {
            System.out.println(i);
        }

        return user;
    }

}
