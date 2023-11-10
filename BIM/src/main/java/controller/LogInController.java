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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.User;
import service.DBConnection;
import utils.ChildThread;
import utils.RemoteConnection;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class LogInController implements Initializable {

    Image showPasswordImage;
    boolean showPasswordFlag = false;

//    String respuesta;
//    Thread thread;
     
     ChildThread thread;

    //FXML...
    @FXML
    private TextField txtLogInUsername;
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
    @FXML
    private PasswordField pswLogInPassword;
    @FXML
    private TextField txtShowLoginPassword;
    @FXML
    private Button btnShowPassword;

    @FXML
    private ImageView imgShowPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
//    public void threadToServeer(String data) {
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String param = String.valueOf(data);
//                respuesta = RemoteConnection.connectToServer("POST", param);
//                System.out.println("Respuesta del servidor: " + respuesta);
//            }
//        });
//        thread.start();
//        waitThreadEnd();
//    }
//    
//    public String obtenerRespuesta() {
//        return respuesta;
//    }
//    
//    public void waitThreadEnd(){
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clear() {
        txtRegisterName.setText("");
        txtRegisterLastName.setText("");
        txtRegisterId.setText("");
        txtRegisterUsername.setText("");
        txtRegisterEmail.setText("");
        txtRegisterPassword.setText("");
        rbtRegisterActive.setSelected(true);
        rbtRegisterDesigner.setSelected(true);
        txtLogInUsername.setText("");
        pswLogInPassword.setText("");
        txtShowLoginPassword.setText("");
    }

    public void changePanes(boolean show) {

        paneSingIn.setVisible(!show);
        paneSingIn.setDisable(show);
        paneSingUp.setVisible(show);
        paneSingUp.setDisable(!show);
        paneNewToBIM.setVisible(!show);
        paneNewToBIM.setDisable(show);
    }

    public String getStatus() {

        if (rbtRegisterActive.isSelected()) {
            return rbtRegisterActive.getText();
        } else {
            return rbtRegisterInactive.getText();
        }
    }

    public String getRole() {

        if (rbtRegisterEngineer.isSelected()) {
            return rbtRegisterEngineer.getText();
        } else if (rbtRegisterDesigner.isSelected()) {
            return rbtRegisterDesigner.getText();
        } else if (rbtRegisterAdministrator.isSelected()) {
            return rbtRegisterAdministrator.getText();
        }
        return rbtRegisterDesigner.getText();
    }

    public void showPassword(boolean show) {

        pswLogInPassword.setDisable(show);//true
        pswLogInPassword.setVisible(!show);

        txtShowLoginPassword.setDisable(!show);
        txtShowLoginPassword.setVisible(show);//true
    }

    @FXML
    private void clickSigIn(ActionEvent event) throws IOException {
        
        //Luego colocar que si un usuario esta inactivo, no le permita ingresar 
        String usernameOrEmail = txtLogInUsername.getText();
        String password = null;
        if (pswLogInPassword.isVisible()) {
            password = pswLogInPassword.getText();
        } else if (txtShowLoginPassword.isVisible()) {
            password = txtShowLoginPassword.getText();
        }

        thread = new ChildThread("consulta|",usernameOrEmail + "|" + password);
        thread.waitThreadEnd();

        System.out.println("Role:" + thread.obtenerRespuesta());

        if ("Designer".equals(thread.obtenerRespuesta())) {
            App.setRoot("designer");
        } else if ("Engineer".equals(thread.obtenerRespuesta())) {
            App.setRoot("engineer");
        } else if ("Administrator".equals(thread.obtenerRespuesta())) {
            App.setRoot("administrator");
        } else if (thread.obtenerRespuesta() == null) {
            showAlert("Datos invalidos");
        }

    }

    @FXML
    private void clickCreateAccount(MouseEvent event) {
        clear();
        changePanes(true);
    }

    @FXML
    private void clickSingUp(ActionEvent event) {
       
        if (txtRegisterUsername.getText().isEmpty() || txtRegisterEmail.getText().isEmpty()
            || txtRegisterPassword.getText().isEmpty()) {
            showAlert("Txt is empty");
        } else {
            int id = Integer.parseInt(txtRegisterId.getText());
            String name = txtRegisterName.getText();
            String lastName = txtRegisterLastName.getText();
            String status = getStatus();
            String username = txtRegisterUsername.getText();
            String password = txtRegisterPassword.getText();
            String email = txtRegisterEmail.getText();
            String role = getRole();

            User user = new User(id, name, lastName, status, username, email, password, role);
            
            thread = new ChildThread("newUser|",user.toString());
            
//            DBConnection.getInstance().registerUsers(user);
            changePanes(false);
        }
    }

    @FXML
    private void clickBack(ActionEvent event) {
        changePanes(false);
        clear();
    }

    @FXML
    private void clcikShowPassword(ActionEvent event) {
        String imagePath = "/images/";
        if (!showPasswordFlag) {
            showPassword(true);
            txtShowLoginPassword.setText(pswLogInPassword.getText());
            btnShowPassword.toFront();
            imagePath += "eyeCloseWhite.png";
            showPasswordImage = new Image(getClass().getResource(imagePath).toExternalForm());
            imgShowPassword.setImage(showPasswordImage);
            showPasswordFlag = true;
        } else {
            showPassword(false);
            pswLogInPassword.setText(txtShowLoginPassword.getText());
            imagePath += "eyeOpenWhite.png";
            showPasswordImage = new Image(getClass().getResource(imagePath).toExternalForm());
            imgShowPassword.setImage(showPasswordImage);
            showPasswordFlag = false;
        }
    }
}
