/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.bim.App;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.User;
import service.DBConnection;
import utils.ChildThread;
import utils.Parsing;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class AdministratorController implements Initializable {

    ChildThread thread;

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
    private Label lblUserID;
    @FXML
    private Button btnAddProyect;
    @FXML
    private Button btnModifyProyect;
    @FXML
    private Button btnDeleteProyect;
    private Label lblAddProyects;
    private Label lblModifyProyects;
    private Label lblDeleteProyects;
    @FXML
    private Label lblProyectCode;
    @FXML
    private TableView<User> tbvUsers;
    @FXML
    private TableColumn<User, String> columnID;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnLastName;
    @FXML
    private TableColumn<User, String> columnStatus;
    @FXML
    private TableColumn<User, String> columnRole;
    @FXML
    private Button btnSearchUser;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtUserLastName;
    @FXML
    private TextField txtUserEmail;
    @FXML
    private TextField txtUserUsername;
    @FXML
    private TextField txtUserPassword;
    @FXML
    private RadioButton rbtUserInactive;
    @FXML
    private RadioButton rbtUserActive;
    @FXML
    private RadioButton rbtUserAdministrator;
    @FXML
    private RadioButton rbtUserDesigner;
    @FXML
    private RadioButton rbtUserEngineer;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private ToggleGroup roleGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTableView();
        updateTableViewUsers();
    }

    private void clearTxtUser() {
        txtUserId.setText("");
        txtUserName.setText("");
        txtUserLastName.setText("");
        txtUserEmail.setText("");
        txtUserUsername.setText("");
        txtUserPassword.setText("");
    }

    private void fillTableView() {

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void updateTableViewUsers() {

        thread = new ChildThread("getAllUsers|", " ");
        thread.waitThreadEnd();

        List<User> listaUsers = Parsing.parsingAllUsers(thread.getResponse());

        ObservableList<User> userObservableList = FXCollections.observableArrayList(listaUsers);
        tbvUsers.setItems(userObservableList);
    }

    private void animationPaneMenu(int pos) {
        double targetWidth = pos;
        Duration duration = Duration.seconds(1);

        paneMenu.setTranslateX(0);

        TranslateTransition transition = new TranslateTransition(duration, paneMenu);
        transition.setToX(targetWidth);
        transition.play();
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

    private void userOPtions(boolean show1, boolean show2, boolean show3) {

        btnAddUser.setDisable(!show1);
        btnAddUser.setVisible(show1);
        btnSearchUser.setDisable(show1);
        btnSearchUser.setVisible(!show1);

        btnModifyUser.setDisable(!show2);
        btnModifyUser.setVisible(show2);

        btnDeleteUser.setDisable(!show3);
        btnDeleteUser.setVisible(show3);

        btnSearchUser.setDisable(show2 && show3);
        btnSearchUser.setVisible(show2 || show3);
    }

    private void showUsers(boolean show) {

        showBtnMenu(show);
        showMenuPane(!show);
        paneUsers.setDisable(!show);
        paneUsers.setVisible(show);

//        if (lblAddUsers.isHover()) {
//            lblUserID.setText("Identification:");
//            userOPtions(true, false, false);
//            clearTxtUser();
//        } else if (lblModifyUsers.isHover()) {
//            lblUserID.setText("Search user by ID:"); //posible cambio a solo "identification"
//            userOPtions(false, true, false);
//            clearTxtUser();
//        } else if (lblDeleteUsers.isHover()) {
//            lblUserID.setText("Search user by ID:");
//            userOPtions(false, false, true);
//            clearTxtUser();
//        }
    }

    private void proyectOptions(boolean show1, boolean show2, boolean show3) {

        btnAddProyect.setDisable(!show1);
        btnAddProyect.setVisible(show1);

        btnModifyProyect.setDisable(!show2);
        btnModifyProyect.setVisible(show2);

        btnDeleteProyect.setDisable(!show3);
        btnDeleteProyect.setVisible(show3);
    }

    private void showProyects(boolean show) {

        showBtnMenu(show);
        showMenuPane(!show);
        paneProyects.setDisable(!show);
        paneProyects.setVisible(show);

    }

    private void changePaneUsers(String labelText, boolean show1, boolean show2, boolean show3) {

        lblUserID.setText(labelText);
        userOPtions(show1, show2, show3);
        clearTxtUser();
    }

    private void changePaneProyect(String labelText, boolean show1, boolean show2, boolean show3) {

        lblProyectCode.setText(labelText);
        proyectOptions(show1, show2, show3);
    }

    private String getRole() {

        //En scene bulder colocar uno fijo, para no tener problems
        if (rbtUserAdministrator.isSelected()) {
            return rbtUserAdministrator.getText();
        } else if (rbtUserDesigner.isSelected()) {
            return rbtUserDesigner.getText();
        } else if (rbtUserEngineer.isSelected()) {
            return rbtUserEngineer.getText();
        } else {
            return "No se obtuvo ningun role";
        }
    }

    private void setRole(String user[]) {

        if ("Engineer".equals(user[6])) {
            rbtUserEngineer.setSelected(true);
        } else if ("Administrator".equals(user[6])) {
            rbtUserAdministrator.setSelected(true);
        } else {
            rbtUserDesigner.setSelected(true);
        }
    }

    private String getStatus() {

        if (rbtUserActive.isSelected()) {
            return rbtUserActive.getText();
        } else {
            return rbtUserInactive.getText();
        }
    }

    private void setStatus(String user[]) {

        if ("Inactive".equals(user[2])) {
            rbtUserInactive.setSelected(true);
        } else {
            rbtUserActive.setSelected(true);
        }
    }

    @FXML
    private void clickExit(ActionEvent event) throws IOException {
        App.setRoot("logIn");
    }

    @FXML
    private void clcikMenu(ActionEvent event) {
        showMenuPane(true);
        animationPaneMenu(335);
    }

    @FXML
    private void clickBack(ActionEvent event) {
        showMenuPane(false);

    }

    @FXML
    private void clickHome(ActionEvent event) {

        showHome(true);
        showUsers(false);
        showProyects(false);
        showMenuPane(false);
    }

    @FXML
    private void clickAddProyect(ActionEvent event) {
    }

    @FXML
    private void clickModifyProyect(ActionEvent event) {
    }

    @FXML
    private void clcikDeleteProyect(ActionEvent event) {
    }

    @FXML
    private void clickModifyUser(ActionEvent event) {
    }

    @FXML
    private void clickAddUser(ActionEvent event) {

        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String lastName = txtUserLastName.getText();
        String username = txtUserUsername.getText();
        String password = txtUserPassword.getText();
        String email = txtUserEmail.getText();
        String role = getRole();
        String status = getStatus();

        User user = new User(Integer.parseInt(id), name, lastName, status, username, email, password, role);
        thread = new ChildThread("newUser|", user.toString());

    }

    @FXML
    private void clickDeleteUser(ActionEvent event) {
    }

    @FXML
    private void clickGetID(MouseEvent event) {

        int index = tbvUsers.getSelectionModel().getFocusedIndex();

        String id = String.valueOf(columnID.getCellData(index));

        System.out.println("ID:" + id);

        txtUserId.setText(id);
    }

    @FXML
    private void clickSearchUser(ActionEvent event) {

        String id = txtUserId.getText();

        thread = new ChildThread("queryUser|", id + "|");
        thread.waitThreadEnd();

        String[] user = Parsing.parsingUser(thread.getResponse());

        txtUserName.setText(user[0]);
        txtUserLastName.setText(user[1]);
        setStatus(user);
        txtUserUsername.setText(user[3]);
        txtUserEmail.setText(user[4]);
        txtUserPassword.setText(user[5]);    
        setRole(user);

    }

    @FXML
    private void clcikShowAddProyect(ActionEvent event) {
        showHome(false);
        showUsers(false);
        showProyects(true);
        changePaneProyect("Proyect code:", true, false, false);
    }

    @FXML
    private void clickShowModifyProyect(ActionEvent event) {
        showHome(false);
        showUsers(false);
        showProyects(true);
        changePaneProyect("Search user by ID:", false, true, false);
    }

    @FXML
    private void clickShowDeleteProyect(ActionEvent event) {
        showHome(false);
        showUsers(false);
        showProyects(true);
        changePaneProyect("Search user by ID:", false, false, true);
    }

    @FXML
    private void clickShowAddUser(ActionEvent event) {
        showHome(false);
        showProyects(false);
        showUsers(true);
        changePaneUsers("Identification", true, false, false);
    }

    @FXML
    private void clcikShowModifyUser(ActionEvent event) {

        showHome(false);
        showProyects(false);
        showUsers(true);
        changePaneUsers("Search user by ID:", false, true, false);
    }

    @FXML
    private void clickShowDeleteUser(ActionEvent event) {

        showHome(false);
        showProyects(false);
        showUsers(true);
        changePaneUsers("Search user by ID:", false, false, true);
    }

}
