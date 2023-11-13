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
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class DesignerController implements Initializable {

    private boolean hiddenProyectList = false;
    Image imgCurrentObject = null;
    
    Image imgDoor = new Image(getClass().getResourceAsStream("/images/door.png"), 125, 125, false, false);

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = cnvWorkSpace.getGraphicsContext2D();
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
        double canvaMauseX = canvasMouseLocation.getX();
        double canvaMauseY = canvasMouseLocation.getY();

        if (isMouseInsideCanvas(mouseX, mouseY) && imgCurrentObject != null) {
            System.out.println("Mouse soltado sobre el Canvas (X: " + mouseX + ", Y: " + mouseY + ")");

            gc.drawImage(imgCurrentObject, canvaMauseX, canvaMauseY);
        }
        else{
            System.out.println("Mouse soltado fuera el Canvas (X: " + mouseX + ", Y: " + mouseY + ")");
        }
        imgCurrentObject = null;
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
        if(event.getSource() == imvDoor){
            imgCurrentObject = imgDoor;
        }
        
        System.out.println("GUARDADAAAAA");
    }


}
