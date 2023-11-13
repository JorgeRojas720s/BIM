/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jitor
 */
public class UserXProyect {
    
    private String user;
    private String proyect;

    public UserXProyect(String user, String proyect) {
        this.user = user;
        this.proyect = proyect;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProyect() {
        return proyect;
    }

    public void setProyect(String proyect) {
        this.proyect = proyect;
    }

    @Override
    public String toString() {
        return  user + "|" + proyect;
    }
    
    
    
    
}
