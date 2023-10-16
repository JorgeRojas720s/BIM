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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class DesignerController implements Initializable {

    private boolean hiddenProyectList = false;

    @FXML
    private Button btnExit;
    @FXML
    private TableView<?> tbvProyectList;
    @FXML
    private TableColumn<?, ?> columnProyectName;
    @FXML
    private TableColumn<?, ?> columnProyectEngineer;
    @FXML
    private TableColumn<?, ?> columnProyectDesigner;
    @FXML
    private TableColumn<?, ?> columnProyectEndDate;
    @FXML
    private Button btnSelectObject;
    @FXML
    private AnchorPane paneProyectList;
    @FXML
    private Button btnShowList;
    @FXML
    private Button btnCloseList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void clickExit(ActionEvent event) throws IOException {
        App.setRoot("logIn");
    }

    @FXML
    private void clickHideList(ActionEvent event) {
        if (hiddenProyectList == false) {
            paneProyectList.setVisible(true);
            hiddenProyectList = true;
        } else if (hiddenProyectList == true) {
            paneProyectList.setVisible(false);
            hiddenProyectList = false;
        }
    }

    @FXML
    private void clickSelectObject(ActionEvent event) {
        
    }


}
