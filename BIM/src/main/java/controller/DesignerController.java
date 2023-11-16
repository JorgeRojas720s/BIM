/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.bim.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class DesignerController implements Initializable {

    private boolean hiddenProyectList = false;
    Image imgCurrentObject = null;
    Rectangle currentShape = null;
    private boolean architecturalType = true;
    private double rotationDegrees = 0;
    private double flipValue = 0;
    
    Image imgDoorLarge = new Image(getClass().getResourceAsStream("/images/doorOne.png"), 60, 60, false, false);
    Image imgDoorMedium = new Image(getClass().getResourceAsStream("/images/doorTwo.png"), 55, 55, false, false);
    Image imgDoorSmall = new Image(getClass().getResourceAsStream("/images/doorThree.png"), 50, 50, false, false);

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
    @FXML
    private Canvas cnvWorkSpace;
    GraphicsContext gc;
    @FXML
    private ImageView imvDoor;
    @FXML
    private ImageView imvWall;
    @FXML
    private ImageView imvWindow;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClean;
    @FXML
    private Button btnSwitchPlant;
    @FXML
    private Label lblPlantType;
    @FXML
    private Button btnFlipDoor;
    @FXML
    private Button btnRotateDoor;
    @FXML
    private RadioButton rbtWallHorizontal;
    @FXML
    private ToggleGroup wallGroup;
    @FXML
    private RadioButton rbtWallVertical;
    @FXML
    private RadioButton rbtWindowHorizontal;
    @FXML
    private ToggleGroup windowGroup;
    @FXML
    private RadioButton rbtWindowVertical;
    @FXML
    private ChoiceBox<String> cboxDoorSize;
    private final ObservableList<String> typeOptions = FXCollections.observableArrayList("1 m", "0,90 m", "0,80 m");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = cnvWorkSpace.getGraphicsContext2D();
        flipValue = imvDoor.getScaleX() * 1;
        cboxDoorSize.setItems(typeOptions);
        cboxDoorSize.getSelectionModel().selectFirst();
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
    
    @FXML
    private void clickDragObject(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        Point2D canvasMouseLocation = cnvWorkSpace.sceneToLocal(new Point2D(event.getSceneX(), event.getSceneY()));
        double canvaMouseX = canvasMouseLocation.getX();
        double canvaMouseY = canvasMouseLocation.getY();

        if (isMouseInsideCanvas(mouseX, mouseY) && imgCurrentObject != null) {
            System.out.println("Mouse soltado sobre el Canvas (X: " + mouseX + ", Y: " + mouseY + ")");

            gc.drawImage(imgCurrentObject, canvaMouseX, canvaMouseY);
            imgCurrentObject = null;
        }
        else if (isMouseInsideCanvas(mouseX, mouseY) && currentShape != null){
            System.out.println("Mouse soltado sobre el Canvas (X: " + mouseX + ", Y: " + mouseY + ")");

            gc.setFill(currentShape.getFill());
            gc.fillRect(canvaMouseX, canvaMouseY, currentShape.getWidth(), currentShape.getHeight());        
            currentShape = null;
        }
        else{
            System.out.println("Mouse soltado fuera del Canvas (X: " + mouseX + ", Y: " + mouseY + ")");
        }
    }
    
    private boolean isMouseInsideCanvas(double mouseX, double mouseY) {
        double canvasX = cnvWorkSpace.getLayoutX();
        double canvasY = cnvWorkSpace.getLayoutY();
        double canvasWidth = cnvWorkSpace.getWidth();
        double canvasHeight = cnvWorkSpace.getHeight();

        return mouseX >= canvasX && mouseX <= (canvasX + canvasWidth) && mouseY >= canvasY && mouseY <= (canvasY + canvasHeight);
    }

    @FXML
    private void clickSaveImageViewPressed(MouseEvent event) {   
        if(architecturalType){
            saveDoor(event);
            saveWall(event);
            saveWindow(event);
        }
        else{
            //Metodos para la parte estructural
        }
    }
    
    private void saveDoor(MouseEvent event){
        if(event.getSource() == imvDoor && cboxDoorSize.getValue().equals("1 m")){
            imgCurrentObject = imgDoorLarge;
            imgCurrentObject = applyTransformations(imgCurrentObject);
        }
        else if(event.getSource() == imvDoor && cboxDoorSize.getValue().equals("0,90 m")){
            imgCurrentObject = imgDoorMedium;
            imgCurrentObject = applyTransformations(imgCurrentObject);
        }
        else if(event.getSource() == imvDoor && cboxDoorSize.getValue().equals("0,80 m")){
            imgCurrentObject = imgDoorSmall;
            imgCurrentObject = applyTransformations(imgCurrentObject);
        }
    }
    
    private Image applyTransformations(Image originalImage) {
        ImageView imageView = new ImageView(originalImage);
        imageView.setRotate(rotationDegrees);
        imageView.setScaleX(flipValue);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return imageView.snapshot(params, null);
    }
    
    private void saveWall(MouseEvent event){
        if(event.getSource() == imvWall && rbtWallHorizontal.isSelected()){
            currentShape = new Rectangle(225, 10, Color.web("#333333"));
        }
        else if(event.getSource() == imvWall && rbtWallVertical.isSelected()){
            currentShape = new Rectangle(10, 225, Color.web("#333333"));
        }
    }
    
    private void saveWindow(MouseEvent event){
        if(event.getSource() == imvWindow && rbtWindowHorizontal.isSelected()){
            currentShape = new Rectangle(225, 5, Color.web("#327fdd"));
        }
        else if (event.getSource() == imvWindow && rbtWindowVertical.isSelected()){
            currentShape = new Rectangle(5, 225, Color.web("#327fdd"));
        }
    }

    @FXML
    private void clickSwitchPlant(ActionEvent event) {
        architecturalType = !architecturalType;
    }

    @FXML
    private void clickFlipDoor(ActionEvent event) {
        flipValue = imvDoor.getScaleX() * -1;
        imvDoor.setScaleX(imvDoor.getScaleX() * -1);
    }

    @FXML
    private void clickRotateDoor(ActionEvent event) {
        rotationDegrees += 90;
        if(rotationDegrees == 360){
            rotationDegrees = 0;
        }
        imvDoor.setRotate(rotationDegrees);
    }
    
    @FXML
    private void clickSave(ActionEvent event) {
        
    }

    @FXML
    private void clickClean(ActionEvent event) {
        
    }
}
