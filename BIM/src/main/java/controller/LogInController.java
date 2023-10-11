/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Users;
import service.DBConnection;


/**
 * FXML Controller class
 *
 * @author jitor
 */
public class LogInController implements Initializable {

    @FXML
    private TextField txtLogInUsername;
    @FXML
    private TextField txtLogInPassword;
    @FXML
    private Button btnSingIn;
    @FXML
    private RadioButton rbtRegisterEngineer;
    @FXML
    private ToggleGroup GroupWorkers;
    @FXML
    private RadioButton rbtRegisterDesigner;
    @FXML
    private RadioButton rbtRegisterAdministrator;
    @FXML
    private TextField txtRegisterUsername;
    @FXML
    private TextField txtRegisterEmail;
    @FXML
    private TextField txtRegisterPassword;
    @FXML
    private Button btnSingUp;
    @FXML
    private AnchorPane PaneSingIn;
    @FXML
    private AnchorPane PaneSingUp;
    @FXML
    private Button btnRegisterBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
    private void showAlert(String message) {
     
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
 
    }  
    
    public void changePanes(boolean show){
        
        PaneSingIn.setVisible(!show);
        PaneSingIn.setDisable(show);
        PaneSingUp.setVisible(show);
        PaneSingUp.setDisable(!show);
        
    }
    
    public String getRole(){
        
        if(rbtRegisterEngineer.isSelected()){
            return rbtRegisterEngineer.getText();
        }else if(rbtRegisterDesigner.isSelected()){
             return rbtRegisterDesigner.getText();
        }else if(rbtRegisterAdministrator.isSelected()){
             return rbtRegisterAdministrator.getText();
        }
        return rbtRegisterDesigner.getText();
    }

    @FXML
    private void clickSigIn(ActionEvent event) {
        
    }

    @FXML
    private void clickCreateAccount(MouseEvent event) {
        changePanes(true); 
    }

    @FXML
    private void clickSingUp(ActionEvent event) {
        
        if(txtRegisterUsername.getText().isEmpty() || txtRegisterEmail.getText().isEmpty() 
        || txtRegisterPassword.getText().isEmpty()){
            showAlert("Are you stupid? Txt is empty");
            
        }else{
            String username = txtRegisterUsername.getText();
            String email = txtRegisterEmail.getText();
            String password = txtRegisterPassword.getText();
            String role = getRole();
            Users user = new Users(username,password,email,role);
            DBConnection.getInstance().registerUsers(user);
            
        }
        

    }

    @FXML
    private void clickBack(ActionEvent event) {
        changePanes(false);
    }
    
}
