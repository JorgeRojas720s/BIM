/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.bim.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.ConstructionObject;
import utils.DraggableImage;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class EngineerController implements Initializable {

    private boolean creatProyectPressed = false;
    private List<ConstructionObject> objectList = new ArrayList<>();
    private Canvas cnvAuxSpace;
    private GraphicsContext gc;
    
    private Image imgDoorLarge = new Image(getClass().getResourceAsStream("/images/doorOne.png"), 30, 30, false, false);
    private Image imgDoorMedium = new Image(getClass().getResourceAsStream("/images/doorTwo.png"), 27, 27, false, false);
    private Image imgDoorSmall = new Image(getClass().getResourceAsStream("/images/doorThree.png"), 24, 24, false, false);
    private Image imgColOneCrown = new Image(getClass().getResourceAsStream("/images/col1_crown.png"), 20, 20, false, false);
    private Image imgColTwo = new Image(getClass().getResourceAsStream("/images/col2.png"), 20, 20, false, false);
    private Image imgColThree = new Image(getClass().getResourceAsStream("/images/col3.png"), 20, 20, false, false);
    private Image imgColFour = new Image(getClass().getResourceAsStream("/images/col4.png"), 20, 20, false, false);

    @FXML
    private AnchorPane paneEngineerOptions;
    @FXML
    private Button btnCreateProyect;
    @FXML
    private Button btnViewMaterials;
    @FXML
    private Button btnModifyProyect;
    @FXML
    private Button btnViewObjects;

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
    private ScrollPane scrMaterials;
    @FXML
    private AnchorPane paneObject;
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
    @FXML
    private Label txtPlanEngineer;
    @FXML
    private Label txtPlanProyect;
    @FXML
    private Label txtPlanDate;
    @FXML
    private Label txtPlanDesigner;
    @FXML
    private Label txtPlanName;
    @FXML
    private ImageView imvArchitecturalPlant;
    @FXML
    private ImageView imvStructuralPlant;

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
        
        gc = cnvAuxSpace.getGraphicsContext2D();
    }

    private void showMessageLabelsVisible(boolean name, boolean message) {
        lblEngineerName.setVisible(name);
        lblMessage.setVisible(message);
    }

    private void showAnchorPanesVisible(boolean proyect, boolean material, boolean object, boolean proyectList, boolean report) {
        paneProyect.setVisible(proyect);
        paneMaterial.setVisible(material);
        paneObject.setVisible(object);
        paneProyectList.setVisible(proyectList);
        paneReport.setVisible(report);
    }

    private void setTextToProgressButtons(String btnPro) {
        btnProyectProcess.setText(btnPro);
    }

    private void modifyLblAndProgressParameters() {
        showMessageLabelsVisible(false, false);
        setTextToProgressButtons("Modify");
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
        setTextToProgressButtons("Create");
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
        }
    }

    @FXML
    private void clickViewMaterials(ActionEvent event) {
        if (!creatProyectPressed) {
            showAnchorPanesVisible(false, true, false, true, false);
            modifyLblAndProgressParameters();
            showDoNotButtonsVisible(false, false, false);
        }
    }

    @FXML
    private void clickViewObjects(ActionEvent event) {
        if (!creatProyectPressed) {
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
            
            loadCanvasObjects();
        }
    }
    
    private void loadCanvasObjects(){
        
        int plantSelector = 0;
        while(plantSelector != 2){
            for(ConstructionObject obj : objectList){
                if(obj.getObjectType().substring(0, 3).equals("col") && plantSelector == 1){
                    if(obj.getObjectType().equals("col1")){
                        loadColumn(obj, imgColOneCrown);
                    }
                    else if(obj.getObjectType().equals("col2")){
                        loadColumn(obj, imgColTwo);
                    }
                    else if(obj.getObjectType().equals("col3")){
                        loadColumn(obj, imgColThree);
                    }
                    else if(obj.getObjectType().equals("col4")){
                        loadColumn(obj, imgColFour);
                    }
                    else if(obj.getObjectType().equals("crown")){
                        loadColumn(obj, imgColOneCrown);
                    }
                }
                else if (plantSelector == 0){
                    if(obj.getObjectType().equals("door")){
                        loadDoor(obj);
                    }
                    else if(obj.getObjectType().equals("wall")){
                        loadWall(obj);
                    }
                    else if(obj.getObjectType().equals("window")){
                        loadWindow(obj);
                    }
                }
            }
            if(plantSelector == 0){
                imvArchitecturalPlant.setImage(cnvAuxSpace.snapshot(null, null));
            }
            else if(plantSelector == 1){
                imvStructuralPlant.setImage(cnvAuxSpace.snapshot(null, null));
            }
            plantSelector++;
        }
    }
    
    private void loadDoor(ConstructionObject obj){
        ImageView imageView = null;
        if(obj.getHeight() == 30){
            imageView = new ImageView(applyToLoadTransformations(imgDoorLarge, obj));
        }
        else if(obj.getHeight() == 27){
            imageView = new ImageView(applyToLoadTransformations(imgDoorMedium, obj));
        }
        else if(obj.getHeight() == 24){
            imageView = new ImageView(applyToLoadTransformations(imgDoorSmall, obj));
        }
        drawCanvas(obj, imageView);
    }
    
    private void loadWall(ConstructionObject obj){
        Rectangle wallRectangle = new Rectangle(obj.getWidth(), obj.getHeight(), Color.web("#333333"));
        drawCanvas(obj, wallRectangle);
    }
    
    private void loadWindow(ConstructionObject obj){
        Rectangle windowRectangle = new Rectangle(obj.getWidth(), obj.getHeight(), Color.web("#327fdd"));
        drawCanvas(obj, windowRectangle);
    }
    
    private void loadColumn(ConstructionObject obj, Image columnImage){
        ImageView imageView = new ImageView(applyToLoadTransformations(columnImage, obj));
        drawCanvas(obj, imageView);
    }
    
    private Image applyToLoadTransformations(Image originalImage, ConstructionObject obj) {
        ImageView imageView = new ImageView(originalImage);
        imageView.setFitHeight(originalImage.getHeight());
        imageView.setFitWidth(originalImage.getWidth());
        imageView.setRotate(obj.getRotation());
        imageView.setScaleX(obj.getFlip());

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return imageView.snapshot(params, null);
    }
    
    private void drawCanvas(ConstructionObject object, Rectangle rectangle) {
        gc.setFill(rectangle.getFill());
        gc.fillRect(object.getPosX(), object.getPosY(), rectangle.getWidth(), rectangle.getHeight());
    }
    
    private void drawCanvas(ConstructionObject object, ImageView imageView) {
        gc.drawImage(imageView.getImage(), object.getPosX(), object.getPosY());
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
}
