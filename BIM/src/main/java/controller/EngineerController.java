/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.bim.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class EngineerController implements Initializable {

    private boolean creatProyectPressed = false;

    @FXML
    private AnchorPane paneEngineerOptions;
    @FXML
    private Button btnCreateProyect;
    @FXML
    private Button btnModifyMaterials;
    @FXML
    private Button btnModifyProyect;
    @FXML
    private Button btnModifyObjects;

    //Cambiar tipos de objetos...
    @FXML
    private TableView<String> tbvProyectList;
    private ObservableList<String> proyectList = FXCollections.observableArrayList();
    @FXML
    private Label lblEngineerName;
    @FXML
    private TableColumn<String, String> columnProyectName;
    @FXML
    private TableColumn<String, String> columnProyectEngineer;
    @FXML
    private TableColumn<String, String> columnProyectDesigner;
    @FXML
    private TableColumn<String, String> columnProyectEndDate;

    @FXML
    private AnchorPane paneProyect;
    @FXML
    private TextField txtProyectName;
    @FXML
    private DatePicker dateStartProyect;
    @FXML
    private TextField txtProyectEngineer;
    @FXML
    private TextField txtProyectDesigner;
    @FXML
    private DatePicker dateEndProyect;
    @FXML
    private Button btnProyectProcess;
    @FXML
    private AnchorPane paneMaterial;
    @FXML
    private TextField txtMaterialUnit;
    @FXML
    private TextField txtMaterialName;
    @FXML
    private TextField txtMaterialId;
    @FXML
    private Button btnMaterialProcess;
    @FXML
    private TextField txtMaterialPrice;
    @FXML
    private ScrollPane scrMaterials;
    @FXML
    private Button btnAddMaterial;
    @FXML
    private AnchorPane paneObject;
    @FXML
    private TextField txtObjectCode;
    @FXML
    private Button btnObjectProcess;
    @FXML
    private ScrollPane scrObjects;
    @FXML
    private Label lblMessage;
    @FXML
    private Button btnGenerateReport;
    @FXML
    private Button btnExit;
    @FXML
    private AnchorPane paneProyectList;
    @FXML
    private AnchorPane paneReport;
    @FXML
    private Button btnDoNotCreate1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        proyectList.add("Proyecto 1");
        proyectList.add("Proyecto 2");
        proyectList.add("Proyecto 3");

        tbvProyectList.setItems(proyectList);
        
        columnProyectName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        columnProyectEngineer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        columnProyectDesigner.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        columnProyectEndDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    }

    private void showMessageLabelsVisible(boolean name, boolean message) {
        lblEngineerName.setVisible(name);
        lblMessage.setVisible(message);
    }

    private void showAnchorPanesVisible(boolean proyect, boolean material, boolean object, boolean list, boolean report) {
        paneProyect.setVisible(proyect);
        paneMaterial.setVisible(material);
        paneObject.setVisible(object);
        paneProyectList.setVisible(list);
        paneReport.setVisible(report);
    }

    private void setTextToProgressButtons(String btnPro, String btnMats, String btnObj) {
        btnProyectProcess.setText(btnPro);
        btnMaterialProcess.setText(btnMats);
        btnObjectProcess.setText(btnObj);
    }

    private void modifyLblAndProgressParameters() {
        showMessageLabelsVisible(false, false);
        setTextToProgressButtons("Modify", "Modify", "Modify");
    }

    private void correctlyModifiedMessage() {
        showMessageLabelsVisible(false, true);
        lblMessage.setText("Correctly Modified!!");
    }
    
    private void showDoNotButtonsVisible(boolean btn1, boolean btn2, boolean btn3){
        btnDoNotCreate1.setVisible(btn1);
    }

    @FXML
    private void clickCreateProyect(ActionEvent event) {
        creatProyectPressed = true;
        setTextToProgressButtons("Create", "...", "...");
        showAnchorPanesVisible(true, false, false, true, false);
        showMessageLabelsVisible(false, false);
        showDoNotButtonsVisible(true, true, true);
    }

    @FXML
    private void clickModifyProyect(ActionEvent event) {
        if (!creatProyectPressed && !tbvProyectList.getSelectionModel().isEmpty()) {
            showAnchorPanesVisible(true, false, false, true, false);
            modifyLblAndProgressParameters();
            showDoNotButtonsVisible(false, false, false);
            //Logica de los textFiel - llenar cuando se seleccionan para modificar despues
        }
    }

    @FXML
    private void clickModifyMaterials(ActionEvent event) {
        if (!creatProyectPressed && !tbvProyectList.getSelectionModel().isEmpty()) {
            showAnchorPanesVisible(false, true, false, true, false);
            modifyLblAndProgressParameters();
            showDoNotButtonsVisible(false, false, false);
        }
    }

    @FXML
    private void clickModifyObjects(ActionEvent event) {
        if (!creatProyectPressed && !tbvProyectList.getSelectionModel().isEmpty()) {
            showAnchorPanesVisible(false, false, true, true, false);
            modifyLblAndProgressParameters();
            showDoNotButtonsVisible(false, false, false);
        }
    }

    @FXML
    private void clickGenerateReport(ActionEvent event) {
        if (!creatProyectPressed && !tbvProyectList.getSelectionModel().isEmpty()) {
            showAnchorPanesVisible(false, false, false, false, true);
            modifyLblAndProgressParameters();
        }
    }

    @FXML
    private void clickAddMaterial(ActionEvent event) {

    }

    @FXML
    private void clickExit(ActionEvent event) throws IOException {
        App.setRoot("logIn");
    }

    @FXML
    private void clickDoNotCreate(ActionEvent event) {
        creatProyectPressed = false;
        showAnchorPanesVisible(false, false, false, true, false);
        showMessageLabelsVisible(false, true);
        lblMessage.setText("Process Canceled!!");
    }

    @FXML
    private void clickProyectProcess(ActionEvent event) {
        if (creatProyectPressed == true && !txtProyectName.getText().isEmpty() && !txtProyectEngineer.getText().isEmpty() && !txtProyectDesigner.getText().isEmpty()
                && dateStartProyect.getValue() != null && dateEndProyect.getValue() != null) {
            //Logica para el proceso de crear un proyecto
            creatProyectPressed = false;
            showAnchorPanesVisible(false, false, false, true, false);
            showMessageLabelsVisible(false, true);
            lblMessage.setText("Proyect Created!!");
        } else if (creatProyectPressed == false && !txtProyectName.getText().isEmpty() && !txtProyectEngineer.getText().isEmpty() && !txtProyectDesigner.getText().isEmpty()
                && dateStartProyect.getValue() != null && dateEndProyect.getValue() != null) {
            //Logica para modificar un proyecto
            showAnchorPanesVisible(false, false, false, true, false);
            correctlyModifiedMessage();
        }
    }
    
    @FXML
    private void clickMaterialProcess(ActionEvent event) {
        if (creatProyectPressed == false && !txtMaterialName.getText().isEmpty() && !txtMaterialId.getText().isEmpty() && !txtMaterialPrice.getText().isEmpty()
                && !txtMaterialUnit.getText().isEmpty()) {
            //Logica para modificar un material
            showAnchorPanesVisible(false, false, false, true, false);
            correctlyModifiedMessage();
        }
    }

    @FXML
    private void clickObjectProcess(ActionEvent event) {
        if (creatProyectPressed == false && !txtObjectCode.getText().isEmpty()) {
            //Logica para modificar un objeto
            showAnchorPanesVisible(false, false, false, true, false);
            correctlyModifiedMessage();
        }
    }
}
