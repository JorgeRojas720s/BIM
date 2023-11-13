/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.bim.App;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
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
import model.Proyect;
import model.User;
import model.UserXProyect;
import service.DBConnection;
import utils.ChildThread;
import utils.Parsing;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class AdministratorController implements Initializable {

    String data = "";

    boolean setIdFromTblv;

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
    @FXML
    private TableView<?> tblvProyects;
    @FXML
    private TableColumn<?, ?> columProyect;
    @FXML
    private TableColumn<?, ?> columCode;
    @FXML
    private TableView<?> tblvProUsers;
    @FXML
    private TextField txtDesignerID;
    @FXML
    private TextField txtProyectName;
    @FXML
    private TextField txtProyectId;
    @FXML
    private DatePicker dtpStartDate;
    @FXML
    private DatePicker dtpFinishDate;
    @FXML
    private TextField txtEngineerID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fillTableView();

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

    private void updateTableViewUsers(String query) {

        ChildThread thread = new ChildThread("user",query, " ");
        thread.waitThreadEnd();
        data = thread.getResponse();

        List<User> listaUsers = Parsing.parsingAllUsers(data);

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
        
        String code = txtProyectId.getText();
        String name = txtProyectName.getText();
        String engineer = txtEngineerID.getText();
        String designer = txtDesignerID.getText();
        
        LocalDate startDate = dtpStartDate.getValue();
        String dateStart = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        LocalDate finishDate = dtpFinishDate.getValue();
        String dateFinish = finishDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        System.out.println("dateStart"+ dateStart);
        System.out.println("dateFinish"+ dateFinish);
        
        Proyect proyect = new Proyect(code,name,dateStart,dateFinish);
        ChildThread thread1 = new ChildThread("proyect","newProyect", proyect.toString());
        thread1.waitThreadEnd();
        
        UserXProyect userXproyect1 = new UserXProyect(engineer, code);
        ChildThread thread2 = new ChildThread("userXproyect","fillTable", userXproyect1.toString());
        thread2.waitThreadEnd();
        
        UserXProyect userXproyect2 = new UserXProyect(designer, code);
        ChildThread thread3 = new ChildThread("userXproyect","fillTable", userXproyect2.toString());
        thread3.waitThreadEnd();
        
        
    }

    @FXML
    private void clickModifyProyect(ActionEvent event) {
    }

    @FXML
    private void clcikDeleteProyect(ActionEvent event) {
    }

    @FXML
    private void clickModifyUser(ActionEvent event) {

        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String lastName = txtUserLastName.getText();
        String username = txtUserUsername.getText();
        String password = txtUserPassword.getText();
        String email = txtUserEmail.getText();
        String role = getRole();
        String status = getStatus();

        User user = new User(Integer.parseInt(id), name, lastName, status, username, email, password, role);
        ChildThread thread = new ChildThread("user","updateUser", user.toString());
        thread.waitThreadEnd();

        updateTableViewUsers("getAllUsers");
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
        ChildThread thread = new ChildThread("user","newUser", user.toString());
        thread.waitThreadEnd();

        updateTableViewUsers("getAllUsers");
    }

    @FXML
    private void clickDeleteUser(ActionEvent event) {

        String id = txtUserId.getText();

        ChildThread thread = new ChildThread("user","deleteUser", id + "|");
        thread.waitThreadEnd();

        updateTableViewUsers("getAllUsers"); // en este no da error

    }

    @FXML
    private void clickGetID(MouseEvent event) {

        int index = tbvUsers.getSelectionModel().getFocusedIndex();

        String id = String.valueOf(columnID.getCellData(index));
        String role = columnRole.getCellData(index);

        System.out.println("ID:" + id);
        if (setIdFromTblv) {
            txtUserId.setText(id);
        }
        if("Designer".equals(role)){
           txtDesignerID.setText(id); 
        }else if("Engineer".equals(role)){
            txtEngineerID.setText(id);
        }
        
    }

    @FXML
    private void clickSearchUser(ActionEvent event) {

        String id = txtUserId.getText();

        ChildThread thread = new ChildThread("user","queryUser", id + "|");
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
        moveTblvUsers(paneProyects);
        updateTableViewUsers("getEngAndDesig");
        showHome(false);
        showUsers(false);
        showProyects(true);
        changePaneProyect("Proyect code:", true, false, false);
    }

    @FXML
    private void clickShowModifyProyect(ActionEvent event) {
        moveTblvUsers(paneProyects);
        updateTableViewUsers("getEngAndDesig");
        showHome(false);
        showUsers(false);
        showProyects(true);
        changePaneProyect("Search Proyect by ID:", false, true, false);
    }

    @FXML
    private void clickShowDeleteProyect(ActionEvent event) {
        moveTblvUsers(paneProyects);
        updateTableViewUsers("getEngAndDesig");
        showHome(false);
        showUsers(false);
        showProyects(true);
        changePaneProyect("Search Proyect by ID:", false, false, true);
    }

    private void moveTblvUsers(AnchorPane pane) {

        if (!pane.getChildren().contains(tbvUsers)) {
            pane.getChildren().add(tbvUsers);
        }

    }

    @FXML
    private void clickShowAddUser(ActionEvent event) {
        updateTableViewUsers("getAllUsers");
        moveTblvUsers(paneUsers);
        setIdFromTblv = false;
        txtUserId.setEditable(true);
        showHome(false);
        showProyects(false);
        showUsers(true);
        changePaneUsers("Identification", true, false, false);
    }

    @FXML
    private void clcikShowModifyUser(ActionEvent event) {
        updateTableViewUsers("getAllUsers");
        moveTblvUsers(paneUsers);
        setIdFromTblv = true;
        txtUserId.setEditable(false);
        showHome(false);
        showProyects(false);
        showUsers(true);
        changePaneUsers("Search user by ID:", false, true, false);
    }

    @FXML
    private void clickShowDeleteUser(ActionEvent event) {
        updateTableViewUsers("getAllUsers");
        moveTblvUsers(paneUsers);
        setIdFromTblv = true;
        txtUserId.setEditable(true);
        showHome(false);
        showProyects(false);
        showUsers(true);
        changePaneUsers("Search user by ID:", false, false, true);
    }

}
