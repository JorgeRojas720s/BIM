/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import model.ConstructionObject;
import model.Proyect;
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

    public static ArrayList<Proyect> parsingAllProyects(String aux) {

        ArrayList<Proyect> proyectList = new ArrayList<>();

        System.out.println("Proyects:  " + aux);

        String[] proyects = aux.split(";");

        for (String proyect : proyects) {

            String[] proyectData = proyect.split("\\|");
            if (proyectData.length == 6) {

                String name = proyectData[0].substring(1);
                String code = proyectData[1];
                String startDate = proyectData[2];
                String finishDate = proyectData[3];
                String engineer = proyectData[4];
                String designer = proyectData[5].substring(0, proyectData[5].length());

                System.out.println("name: " + name);
                System.out.println("code: " + code);
                System.out.println("startDate: " + startDate);
                System.out.println("finishDate: " + finishDate);
                System.out.println("engineer: " + engineer);
                System.out.println("designer: " + designer);

                Proyect proyectObj = new Proyect(code, name, startDate, finishDate, engineer, designer);
                proyectList.add(proyectObj);
            }
        }

        System.out.println("\nLista de Proyectos:\n");
        for (Proyect proyect : proyectList) {
            System.out.println("name: " + proyect.getName() + " - code: " + proyect.getCode());
        }

        return proyectList;
    }

    public static String[] parsingProyect(String aux) {

        String[] proyect = aux.split("\\|");

        for (String i : proyect) {
            System.out.println(i);
        }

        return proyect;
    }

public static ArrayList<ConstructionObject> parsingAllObjects(String aux) {

    ArrayList<ConstructionObject> objectsList = new ArrayList<>();

    System.out.println("Objects: " + aux);

    String[] objects = aux.split(";");

    for (String object : objects) {

        String[] objectData = object.split("\\|");
        if (objectData.length == 7) {

            String posX = objectData[0].substring(1);
            String posY = objectData[1];
            String objectType = objectData[2];
            String rotation = objectData[3];
            String flip = objectData[4];
            String width = objectData[5];
            String height = objectData[6].substring(0, objectData[6].length());

            System.out.println("posX: " + posX);
            System.out.println("posY: " + posY);
            System.out.println("objectType: " + objectType);
            System.out.println("rotation: " + rotation);
            System.out.println("flip: " + flip);
            System.out.println("width: " + width);
            System.out.println("height: " + height);


            ConstructionObject objectObj = new ConstructionObject(Double.parseDouble(posX), 
                    Double.parseDouble(posY), objectType, Double.parseDouble(rotation), 
                    Double.parseDouble(flip), Double.parseDouble(width), Double.parseDouble(height));
            objectsList.add(objectObj);
            }
        }

        System.out.println("\nLista de obejtos:\n");
        for (ConstructionObject object : objectsList) {
            System.out.println("name: " + object.getObjectType());
        }

        return objectsList;
    }

}
