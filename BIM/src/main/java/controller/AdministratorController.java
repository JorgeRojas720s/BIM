/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.bim.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class AdministratorController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private AnchorPane paneUsers;
    @FXML
    private Button btnModifyUser;
    @FXML
    private Button btnAddUser;
    @FXML
    private Button btnDeleteUser;
    @FXML
    private AnchorPane paneProyects;
    @FXML
    private Button btnMenu;
    @FXML
    private AnchorPane paneMenu;
    @FXML
    private Button clickBack;
    @FXML
    private AnchorPane paneHome;
    @FXML
    private Button btnHome;
    @FXML
    private Label lblAddUsers;
    @FXML
    private Label lblModifyUsers;
    @FXML
    private Label lblDeleteUsers;

    private int aux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void showMenuPane(boolean show) {
        paneMenu.toFront();
        showBtnMenu(!show);
        paneMenu.setDisable(!show);
        paneMenu.setVisible(show);
    }

    private void showBtnMenu(boolean show) {
        btnMenu.setDisable(!show);
        btnMenu.setVisible(show);
    }

    private void showHome(boolean show) {
        paneHome.setDisable(!show);
        paneHome.setVisible(show);
    }

    private void showUsers(boolean show, int aux) {

        showBtnMenu(show);
        showMenuPane(!show);
        paneUsers.setDisable(!show);
        paneUsers.setVisible(show);
        System.out.println("AUX" +aux);
        
        if (aux == 1) {
            System.out.println("Juepu");
                     btnModifyUser.setDisable(show);
            btnModifyUser.setVisible(!show);
            
                btnDeleteUser.setDisable(show);
            btnDeleteUser.setVisible(!show);
            
            
            btnAddUser.setDisable(!show);
            btnAddUser.setVisible(show);
            aux = 0;
        } else if (aux == 2) {
            System.out.println("Porque");
            btnModifyUser.setDisable(!show);
            btnModifyUser.setVisible(show);
            
            //acomodar este despiche mañana
                   btnDeleteUser.setDisable(show);
            btnDeleteUser.setVisible(!show);
            
            
            btnAddUser.setDisable(show);
            btnAddUser.setVisible(!show);
            
            aux = 0;
        } else if (aux == 3) {
            System.out.println("No sirve");
            btnDeleteUser.setDisable(!show);
            btnDeleteUser.setVisible(show);
            
                      btnModifyUser.setDisable(show);
            btnModifyUser.setVisible(!show);
            
              
            btnAddUser.setDisable(show);
            btnAddUser.setVisible(!show);
            
            aux = 0;
        }

    }

    @FXML
    private void clcikShowAddProyect(MouseEvent event) {
    }

    @FXML
    private void clickShowModifyProyect(MouseEvent event) {
    }

    @FXML
    private void clickShowDeleteProyect(MouseEvent event) {
    }

    @FXML
    private void clickShowAddUser(MouseEvent event) {
        aux = 1;
        showHome(false);
        showUsers(true, aux);
    }

    @FXML
    private void clcikShowModifyUser(MouseEvent event) {
        aux = 2;
        showHome(false);
        showUsers(true, aux);
    }

    @FXML
    private void clickShowDeleteUser(MouseEvent event) {
        aux = 3;
        showHome(false);
        showUsers(true, aux);
    }

    @FXML
    private void clickExit(ActionEvent event) throws IOException {
        App.setRoot("logIn");
    }

    @FXML
    private void clickBtnModifyUser(ActionEvent event) {
    }

    @FXML
    private void clickBtnAddUser(ActionEvent event) {
    }

    @FXML
    private void clcikMenu(ActionEvent event) {
        showMenuPane(true);
    }

    @FXML
    private void clickBack(ActionEvent event) {
        showMenuPane(false);
    }

    @FXML
    private void clickHome(ActionEvent event) {
        aux = 0;
        showHome(true);
        showUsers(false, aux);
        showMenuPane(false);
    }

}
