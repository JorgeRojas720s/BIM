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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Button btnRegisterBack;
    @FXML
    private AnchorPane paneSingIn;
    @FXML
    private AnchorPane paneNewToBIM;
    @FXML
    private AnchorPane paneSingUp;
    @FXML
    private TextField txtRegisterName;
    @FXML
    private TextField txtRegisterLastName;
    @FXML
    private TextField txtRegisterId;
    //private ComboBox<String> cbxRegisterStatus;
    @FXML
    private ToggleGroup GroupStatus;
    @FXML
    private RadioButton rbtRegisterInactive;
    @FXML
    private RadioButton rbtRegisterActive;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // cbxRegisterStatus.getItems().addAll("Married", "Viuo", "na");
     
    }     
    
    private void showAlert(String message) {
     
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    public void changePanes(boolean show){
        
        paneSingIn.setVisible(!show);
        paneSingIn.setDisable(show);
        paneSingUp.setVisible(show);
        paneSingUp.setDisable(!show);    
        paneNewToBIM.setVisible(!show);
        paneNewToBIM.setDisable(show);
    }
    
    public String getStatus(){
        
        if(rbtRegisterActive.isSelected()){
            return rbtRegisterActive.getText();
        }else{
             return rbtRegisterInactive.getText();
        }
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
    private void clickSigIn(ActionEvent event) throws IOException {
        
        String usernameOrEmail = txtLogInUsername.getText();
        String password = txtLogInPassword.getText();
        
        String role = DBConnection.getInstance().logInUser(usernameOrEmail, password);
        System.out.println("Role:" + role);
        
        if("Designer".equals(role)){
            App.setRoot("designer");
        }else if("Engineer".equals(role)){
             App.setRoot("engineer");
        }else if("Administrator".equals(role)){
            App.setRoot("administrator");
        }else if(role == null){
            showAlert("No sea mamador");
        }
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
            int id = Integer.parseInt(txtRegisterId.getText());
            String name = txtRegisterName.getText();
            String lastName = txtRegisterLastName.getText();
            String status = getStatus();
            String username = txtRegisterUsername.getText();
            String password = txtRegisterPassword.getText();
            String email = txtRegisterEmail.getText();
            String role = getRole();
            
            Users user = new Users(id,name,lastName,status, username,password,email,role);
            DBConnection.getInstance().registerUsers(user);
            changePanes(false);
        }
    }

    @FXML
    private void clickBack(ActionEvent event) {
        changePanes(false);
    }
    
}
