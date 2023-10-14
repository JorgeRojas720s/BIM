/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jitor
 */
public class User {

    private int id;
    private String name;
    private String lastName;
    private String status;
    private String username;
    private String password;
    private String email;
    private String role;

    public User(int id, String name, String lastName, String status, String username, String password, String email, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(int id, String name, String lastName, String status,String role) {

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", status=" + status + ", username=" + username + ", password=" + password + ", email=" + email + ", role=" + role + '}';
    }

}
