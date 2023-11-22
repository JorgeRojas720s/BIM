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
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.ConstructionObject;
import model.Proyect;
import model.User;
import utils.ChildThread;
import utils.DraggableImage;
import utils.Parsing;

/**
 * FXML Controller class
 *
 * @author jitor
 */
public class DesignerController implements Initializable {

    ChildThread thread;

    private String userId;
    private String data = "";
    private String code = "";
    private String constructionsPaperName = "";
    private String idConstructionPaper;
    private boolean hiddenProyectList = false;
    private Image imgCurrentObject = null;
    private Rectangle currentShape = null;
    private boolean plantType = true;
    private double doorRotDegrees = 0;
    private double doorFlipValue = 0;
    private double colOneRotDegrees = 0;
    private double colTwoRotDegrees = 0;
    private double colThreeRotDegrees = 0;
    private double colFourRotDegrees = 0;
    private double crownBeamRotDegrees = 0;
    private String currentObjectName;
    private List<ConstructionObject> objectList = new ArrayList<>();
    private ConstructionObject selectedObject;
    private boolean objectFound = false;
    private double newY;
    private double newX;
    private double objectHeight = 0;
    private double objectWidth = 0;
    private static final int PIXELS_PER_METER = 30;
   
    private List<DraggableImage> dragImgArquitectural = new ArrayList<>();
    private List<DraggableImage> dragImgStructural = new ArrayList<>();
    private DraggableImage selectedImage = null;
    private DraggableImage selectedImageToScroll = null;
    private double offsetX, offsetY;

    private Image imgDoorLarge = new Image(getClass().getResourceAsStream("/images/doorOne.png"), 30, 30, false, false);
    private Image imgDoorMedium = new Image(getClass().getResourceAsStream("/images/doorTwo.png"), 27, 27, false, false);
    private Image imgDoorSmall = new Image(getClass().getResourceAsStream("/images/doorThree.png"), 24, 24, false, false);
    private Image imgColOneCrown = new Image(getClass().getResourceAsStream("/images/col1_crown.png"), 20, 20, false, false);
    private Image imgColTwo = new Image(getClass().getResourceAsStream("/images/col2.png"), 20, 20, false, false);
    private Image imgColThree = new Image(getClass().getResourceAsStream("/images/col3.png"), 20, 20, false, false);
    private Image imgColFour = new Image(getClass().getResourceAsStream("/images/col4.png"), 20, 20, false, false);

    private int totalDoors = 0, totalWalls = 0, totalWindows = 0;
    
    @FXML
    private Button btnExit;
    @FXML
    private TableView<Proyect> tbvProyectList;
    @FXML
    private TableColumn<Proyect, String> columnProyectName;
    @FXML
    private TableColumn<Proyect, String> columnProyectEndDate;
    @FXML
    private Button btnSelectProyect;
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
    @FXML
    private ImageView imvColOne;
    @FXML
    private ImageView imvColTwo;
    @FXML
    private ImageView imvColThree;
    @FXML
    private ImageView imvColFour;
    @FXML
    private ImageView imvCrownBeam;
    @FXML
    private Button btnRotateColOne;
    @FXML
    private Button btnRotateColTwo;
    @FXML
    private Button btnRotateColThree;
    @FXML
    private Button btnRotateColFour;
    @FXML
    private Button btnRotateCrownBeam;
    @FXML
    private ImageView imvTempWorkImg;
    @FXML
    private TableColumn<Proyect, String> columnProyectCode;
    @FXML
    private TableColumn<Proyect, String> columnProyectStartDate;
    @FXML
    private Label lblObjectWidth;
    @FXML
    private Label lblObjectHeight;
    @FXML
    private Label lblObjectRotation;
    @FXML
    private Label lblObjectX;
    @FXML
    private Label lblObjectY;
    @FXML
    private Label lblTotalDoors;
    @FXML
    private Label lblTotalWalls;
    @FXML
    private Label lblTotalWindows;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userId = LogInController.userID;
        updateTableViewProyects();
        fillTableViewProyects();

        gc = cnvWorkSpace.getGraphicsContext2D();
        doorFlipValue = imvDoor.getScaleX() * 1;
        cboxDoorSize.setItems(typeOptions);
        cboxDoorSize.getSelectionModel().selectFirst();

        lblPlantType.setText("ARCHITECTURAL");
        disableImageViewObjects(false);

        initCanvaEvents();
    }
    
    private void animationPaneMenu(int pos) {
        double targetWidth = pos;
        Duration duration = Duration.seconds(0.5);

        paneProyectList.setTranslateX(0);

        TranslateTransition transition = new TranslateTransition(duration, paneProyectList);
        transition.setToX(targetWidth);
        transition.play();
    }
    
    private void fillTableViewProyects() {

        columnProyectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnProyectCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        columnProyectStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnProyectEndDate.setCellValueFactory(new PropertyValueFactory<>("finishDate"));

    }

    private void updateTableViewProyects() {

        thread = new ChildThread("proyect", "getDesignerProyects", userId);
        thread.waitThreadEnd();
        data = thread.getResponse();

        List<Proyect> listaProyects = Parsing.parsingAllProyects(data);

        ObservableList<Proyect> proyectObservableList = FXCollections.observableArrayList(listaProyects);
        tbvProyectList.setItems(proyectObservableList);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    private void showInputDialog() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Introduce Data");
        inputDialog.setHeaderText(null);
        inputDialog.setContentText("Construction Paper:");

        inputDialog.showAndWait().ifPresent(result -> {
           
            constructionsPaperName = result;
        });
    }
    
    private void initCanvaEvents() {
        cnvWorkSpace.setFocusTraversable(true);
        cnvWorkSpace.setOnMouseClicked((MouseEvent event) -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            clickObject(mouseX, mouseY);
            updateObjectLabelInfo();
        });

        cnvWorkSpace.setOnMousePressed((MouseEvent event) -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            selectedImage = null;
            clickObject(mouseX, mouseY);

            if (plantType) {
                for (DraggableImage image : dragImgArquitectural) {
                    if (image.contains(mouseX, mouseY)) {
                        selectedImage = image;
                        offsetX = mouseX - image.getX();
                        offsetY = mouseY - image.getY();

                        selectedImageToScroll = selectedImage;
                        break;
                    }
                }
            } else {
                for (DraggableImage image : dragImgStructural) {
                    if (image.contains(mouseX, mouseY)) {
                        selectedImage = image;
                        offsetX = mouseX - image.getX();
                        offsetY = mouseY - image.getY();

                        selectedImageToScroll = selectedImage;
                        break;
                    }
                }
            }
        });

        cnvWorkSpace.setOnMouseDragged((MouseEvent event) -> {
            if (selectedImage != null && selectedObject != null) {
                double mouseX = event.getX();
                double mouseY = event.getY();

                selectedImage.setX(mouseX - offsetX);
                selectedImage.setY(mouseY - offsetY);

                selectedObject.setPosX(mouseX - offsetX);
                selectedObject.setPosY(mouseY - offsetY);

                redrawCanvas();
            }
        });

        cnvWorkSpace.setOnScroll((ScrollEvent event) -> {
            if (selectedObject != null && plantType) {
                double deltaY = event.getDeltaY();

                if (selectedObject.getObjectType().equals("wall") || selectedObject.getObjectType().equals("window")) {
                    resizeWallOrWindow(deltaY);
                }

                redrawCanvas();
            }
        });

        cnvWorkSpace.setOnMouseReleased((MouseEvent event) -> {
            selectedImage = null;
        });
    }

    private void resizeWallOrWindow(double deltaY) {
        double scaleFactor = 1.1;

        double minWidth = 7.5;
        double minHeight = 7.5;
        double maxWidth = 900;

        double currentWidth = selectedObject.getWidth();
        double currentHeight = selectedObject.getHeight();

        if (deltaY < 0) {
            if (selectedObject.getRotation() == 90.0) {
                selectedObject.setWidth(Math.max(currentWidth / scaleFactor, minWidth));
            } else {
                selectedObject.setHeight(Math.max(currentHeight / scaleFactor, minHeight));
            }
        } else {
            if (selectedObject.getRotation() == 90.0) {
                selectedObject.setWidth(Math.min(currentWidth * scaleFactor, maxWidth));
            } else {
                selectedObject.setHeight(Math.min(currentHeight * scaleFactor, maxWidth));
            }
        }

        if (selectedImageToScroll != null) {
            Node shape = (Node) selectedImageToScroll.getShape();
            if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                rectangle.setWidth(selectedObject.getWidth());
                rectangle.setHeight(selectedObject.getHeight());
            }
        }
    }


    private void redrawCanvas() {
        gc.clearRect(0, 0, cnvWorkSpace.getWidth(), cnvWorkSpace.getHeight());

        if (plantType) {
            for (DraggableImage draggableImage : dragImgArquitectural) {
                Node shape = (Node) draggableImage.getShape();

                if (shape instanceof ImageView) {
                    ImageView imageView = (ImageView) shape;
                    gc.drawImage(imageView.getImage(), draggableImage.getX(), draggableImage.getY());
                } else if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;
                    gc.setFill(rectangle.getFill());
                    gc.fillRect(draggableImage.getX(), draggableImage.getY(), rectangle.getWidth(), rectangle.getHeight());
                }
            }
        } else {
            for (DraggableImage draggableImage : dragImgStructural) {
                Node shape = (Node) draggableImage.getShape();

                if (shape instanceof ImageView) {
                    ImageView imageView = (ImageView) shape;
                    gc.drawImage(imageView.getImage(), draggableImage.getX(), draggableImage.getY());
                } else if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;
                    gc.setFill(rectangle.getFill());
                    gc.fillRect(draggableImage.getX(), draggableImage.getY(), rectangle.getWidth(), rectangle.getHeight());
                }
            }
        }
        updateObjectLabelInfo();
    }

    private void updateObjectLabelInfo(){
        if(selectedObject != null){
            lblObjectX.setText(String.valueOf(selectedObject.getPosX()));
            lblObjectY.setText(String.valueOf(selectedObject.getPosY()));
            lblObjectHeight.setText(String.valueOf(selectedObject.getHeight()/PIXELS_PER_METER));
            lblObjectWidth.setText(String.valueOf(selectedObject.getWidth()/PIXELS_PER_METER));
            lblObjectRotation.setText(String.valueOf(selectedObject.getRotation()));
        } else {
            lblObjectX.setText("...");
            lblObjectY.setText("...");
            lblObjectHeight.setText("...");
            lblObjectWidth.setText("...");
            lblObjectRotation.setText("...");
        }
    }
    
    private void ubdateObjectTotals(){
        totalDoors = 0;
        totalWalls = 0;
        totalWindows = 0;
        for(ConstructionObject obj : objectList){
            if (obj.getObjectType().equals("door")){
                totalDoors++;
            }
            else if (obj.getObjectType().equals("wall")){
                totalWalls++;
            }
            else if (obj.getObjectType().equals("window")){
                totalWindows++;
            }
        }
        
        lblTotalDoors.setText(String.valueOf(totalDoors));
        lblTotalWalls.setText(String.valueOf(totalWalls));
        lblTotalWindows.setText(String.valueOf(totalWindows));
    }
    
    public void clickObject(double mouseX, double mouseY){
        objectFound = false;
        
        for (ConstructionObject object : objectList) {
            double objX = object.getPosX();
            double obgY = object.getPosY();

            if (object.getObjectType().equals("door")) {
                objectFound = verifyClickDoor(object, mouseX, mouseY, objX, obgY);
                if (objectFound) {
                    break;
                }
            } else if (object.getObjectType().equals("wall")) {
                objectFound = verifyClickWall(object, mouseX, mouseY, objX, obgY);
                if (objectFound) {
                    break;
                }
            } else if (object.getObjectType().equals("window")) {
                objectFound = verifyClickWindow(object, mouseX, mouseY, objX, obgY);
                if (objectFound) {
                    break;
                }
            } else if (object.getObjectType().equals("col1") || object.getObjectType().equals("col2") || object.getObjectType().equals("col3")
                    || object.getObjectType().equals("col4") || object.getObjectType().equals("crown")) {
                objectFound = verifyClickCol(object, mouseX, mouseY, objX, obgY);
                if (objectFound) {
                    break;
                }
            }
        }
    }

    private boolean verifyClickDoor(ConstructionObject obj, double mouseX, double mouseY, double objX, double obgY) {
        boolean clicked = false;
        if (mouseX >= objX && mouseX <= (objX + obj.getWidth()) && mouseY >= obgY && mouseY <= (obgY + obj.getHeight())) {
            selectedObject = obj;
            System.out.println(selectedObject.getObjectType());
            clicked = true;
        }
        return clicked;
    }

    private boolean verifyClickWall(ConstructionObject obj, double mouseX, double mouseY, double objX, double obgY) {
        boolean clicked = false;
        if (mouseX >= objX && mouseX <= (objX + obj.getWidth()) && mouseY >= obgY && mouseY <= (obgY + obj.getHeight())) {
            selectedObject = obj;
            System.out.println("WALL-> H: " + obj.getHeight() + ", W: " + obj.getWidth());
            clicked = true;
        }
        return clicked;
    }

    private boolean verifyClickWindow(ConstructionObject obj, double mouseX, double mouseY, double objX, double obgY) {
        boolean clicked = false;
        if (mouseX >= objX && mouseX <= (objX + obj.getWidth()) && mouseY >= obgY && mouseY <= (obgY + obj.getHeight())) {
            selectedObject = obj;
            System.out.println("WINDOW-> H: " + obj.getHeight() + ", W: " + obj.getWidth());
            clicked = true;
        }
        return clicked;
    }

    private boolean verifyClickCol(ConstructionObject obj, double mouseX, double mouseY, double objX, double obgY) {
        boolean clicked = false;
        if (mouseX >= objX && mouseX <= (objX + obj.getWidth()) && mouseY >= obgY && mouseY <= (obgY + obj.getHeight())) {
            selectedObject = obj;
            System.out.println(selectedObject.getObjectType());
            clicked = true;
        }
        return clicked;
    }

    @FXML
    private void clickExit(ActionEvent event) throws IOException {
        App.setRoot("logIn");
    }

    @FXML
    private void clickHideList(ActionEvent event) {
        if (hiddenProyectList == false) {
            hiddenProyectList = true;
            btnShowList.setVisible(false);
            animationPaneMenu(414);
        } else if (hiddenProyectList == true) {
            hiddenProyectList = false;
            btnShowList.setVisible(true);
            animationPaneMenu(-414);
        }
    }

    @FXML
    private void clickDragObject(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        Point2D canvasMouseLocation = cnvWorkSpace.sceneToLocal(new Point2D(event.getSceneX(), event.getSceneY()));
        double canvaMouseX = canvasMouseLocation.getX();
        double canvaMouseY = canvasMouseLocation.getY();

        if (isMouseInsideCanvas(mouseX, mouseY) && imgCurrentObject != null) {

            gc.drawImage(imgCurrentObject, canvaMouseX, canvaMouseY);
            createObject(canvaMouseX, canvaMouseY);
        } else if (isMouseInsideCanvas(mouseX, mouseY) && currentShape != null) {

            gc.setFill(currentShape.getFill());
            gc.fillRect(canvaMouseX, canvaMouseY, currentShape.getWidth(), currentShape.getHeight());
            createObject(canvaMouseX, canvaMouseY);
        }
        imgCurrentObject = null;
        currentShape = null;
        currentObjectName = "";
    }

    private void createObject(double canvaMouseX, double canvaMouseY) {
        ConstructionObject object = null;
        DraggableImage draggableImage = null;
        if (plantType) {
            switch (currentObjectName) {
                case "door":
                    createDoor(object, draggableImage, canvaMouseX, canvaMouseY);
                    break;
                case "wall":
                    createWall(object, draggableImage, canvaMouseX, canvaMouseY);
                    break;
                case "window":
                    createWindow(object, draggableImage, canvaMouseX, canvaMouseY);
                    break;
                default:
                    break;
            }
        } else {
            switch (currentObjectName) {
                case "col1":
                    createColumn(object, draggableImage, canvaMouseX, canvaMouseY, colOneRotDegrees);
                    break;
                case "col2":
                    createColumn(object, draggableImage, canvaMouseX, canvaMouseY, colTwoRotDegrees);
                    break;
                case "col3":
                    createColumn(object, draggableImage, canvaMouseX, canvaMouseY, colThreeRotDegrees);
                    break;
                case "col4":
                    createColumn(object, draggableImage, canvaMouseX, canvaMouseY, colFourRotDegrees);
                    break;
                case "crown":
                    createColumn(object, draggableImage, canvaMouseX, canvaMouseY, crownBeamRotDegrees);
                    break;
                default:
                    break;
            }
        }
        ubdateObjectTotals();
    }

    private void createDoor(ConstructionObject object, DraggableImage draggableImage, double canvaMouseX, double canvaMouseY) {
        double x = 0;
        double y = 0;
        switch (cboxDoorSize.getValue()) {
            case "1 m":
                x = 30.0;
                y = 30.0;
                break;
            case "0,90 m":
                x = 27.0;
                y = 27.0;
                break;
            case "0,80 m":
                x = 24.0;
                y = 24.0;
                break;
            default:
                break;
        }
        ImageView doorImageView = new ImageView(imgCurrentObject);
        doorImageView.setFitWidth(x);
        doorImageView.setFitHeight(y);

        draggableImage = new DraggableImage(doorImageView, canvaMouseX, canvaMouseY, cnvWorkSpace);
        dragImgArquitectural.add(draggableImage);

        object = new ConstructionObject(canvaMouseX, canvaMouseY, currentObjectName, doorRotDegrees, doorFlipValue, x, y);
        System.out.println("DOOR ROTATION-> "+object.getRotation());
        objectList.add(object);
    }

    private void createWall(ConstructionObject object, DraggableImage draggableImage, double canvaMouseX, double canvaMouseY) {
        if (rbtWallHorizontal.isSelected()) {
            object = new ConstructionObject(canvaMouseX, canvaMouseY, currentObjectName, 90.0, 0.0, 7.0, 100.0);
            objectList.add(object);

            Rectangle wallRectangle = new Rectangle(100, 7, Color.web("#333333"));
            draggableImage = new DraggableImage(wallRectangle, canvaMouseX, canvaMouseY, cnvWorkSpace);
            dragImgArquitectural.add(draggableImage);
        } else if (rbtWallVertical.isSelected()) {
            object = new ConstructionObject(canvaMouseX, canvaMouseY, currentObjectName, 0.0, 0.0, 100.0, 7.0);
            objectList.add(object);

            Rectangle wallRectangle = new Rectangle(7, 100, Color.web("#333333"));
            draggableImage = new DraggableImage(wallRectangle, canvaMouseX, canvaMouseY, cnvWorkSpace);
            dragImgArquitectural.add(draggableImage);
        }
    }

    private void createWindow(ConstructionObject object, DraggableImage draggableImage, double canvaMouseX, double canvaMouseY) {
        if (rbtWindowHorizontal.isSelected()) {
            object = new ConstructionObject(canvaMouseX, canvaMouseY, currentObjectName, 90.0, 0.0, 5.0, 100.0);
            objectList.add(object);

            Rectangle windowRectangle = new Rectangle(100, 5, Color.web("#327fdd"));
            draggableImage = new DraggableImage(windowRectangle, canvaMouseX, canvaMouseY, cnvWorkSpace);
            dragImgArquitectural.add(draggableImage);
        } else if (rbtWindowVertical.isSelected()) {
            object = new ConstructionObject(canvaMouseX, canvaMouseY, currentObjectName, 0.0, 0.0, 100.0, 5.0);
            objectList.add(object);

            Rectangle windowRectangle = new Rectangle(5, 100, Color.web("#327fdd"));
            draggableImage = new DraggableImage(windowRectangle, canvaMouseX, canvaMouseY, cnvWorkSpace);
            dragImgArquitectural.add(draggableImage);
        }
    }

    private void createColumn(ConstructionObject object, DraggableImage draggableImage, double canvaMouseX, double canvaMouseY, double degrees) {
        ImageView colImageView = new ImageView(imgCurrentObject);
        colImageView.setFitWidth(imgCurrentObject.getWidth());
        colImageView.setFitHeight(imgCurrentObject.getHeight());

        draggableImage = new DraggableImage(colImageView, canvaMouseX, canvaMouseY, cnvWorkSpace);
        dragImgStructural.add(draggableImage);

        object = new ConstructionObject(canvaMouseX, canvaMouseY, currentObjectName, degrees, 1, 20, 20);
        objectList.add(object);
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
        if (plantType) {
            saveDoor(event);
            saveWall(event);
            saveWindow(event);
        } else {
            saveColOne(event);
            saveColTwo(event);
            saveColThree(event);
            saveColFour(event);
            saveColCrownBeam(event);
        }
    }

    private void saveDoor(MouseEvent event) {
        if (event.getSource() == imvDoor && cboxDoorSize.getValue().equals("1 m")) {
            imgCurrentObject = imgDoorLarge;
            imgCurrentObject = applyTransformations(imgCurrentObject, doorRotDegrees, doorFlipValue);
            currentObjectName = "door";
        } else if (event.getSource() == imvDoor && cboxDoorSize.getValue().equals("0,90 m")) {
            imgCurrentObject = imgDoorMedium;
            imgCurrentObject = applyTransformations(imgCurrentObject, doorRotDegrees, doorFlipValue);
            currentObjectName = "door";
        } else if (event.getSource() == imvDoor && cboxDoorSize.getValue().equals("0,80 m")) {
            imgCurrentObject = imgDoorSmall;
            imgCurrentObject = applyTransformations(imgCurrentObject, doorRotDegrees, doorFlipValue);
            currentObjectName = "door";
        }
    }

    private Image applyTransformations(Image originalImage, double rotation, double flip) {
        ImageView imageView = new ImageView(originalImage);
        imageView.setFitHeight(originalImage.getHeight());
        imageView.setFitWidth(originalImage.getWidth());
        imageView.setRotate(rotation);
        imageView.setScaleX(flip);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return imageView.snapshot(params, null);
    }

    private void saveWall(MouseEvent event) {
        if (event.getSource() == imvWall && rbtWallHorizontal.isSelected()) {
            currentShape = new Rectangle(100, 7, Color.web("#333333"));
            currentObjectName = "wall";
        } else if (event.getSource() == imvWall && rbtWallVertical.isSelected()) {
            currentShape = new Rectangle(7, 100, Color.web("#333333"));
            currentObjectName = "wall";
        }
    }

    private void saveWindow(MouseEvent event) {
        if (event.getSource() == imvWindow && rbtWindowHorizontal.isSelected()) {
            currentShape = new Rectangle(100, 5, Color.web("#327fdd"));
            currentObjectName = "window";
        } else if (event.getSource() == imvWindow && rbtWindowVertical.isSelected()) {
            currentShape = new Rectangle(5, 100, Color.web("#327fdd"));
            currentObjectName = "window";
        }
    }

    private void saveColOne(MouseEvent event) {
        if (event.getSource() == imvColOne) {
            imgCurrentObject = imgColOneCrown;
            imgCurrentObject = applyTransformations(imgCurrentObject, colOneRotDegrees, 1);
            currentObjectName = "col1";
        }
    }

    private void saveColTwo(MouseEvent event) {
        if (event.getSource() == imvColTwo) {
            imgCurrentObject = imgColTwo;
            imgCurrentObject = applyTransformations(imgCurrentObject, colTwoRotDegrees, 1);
            currentObjectName = "col2";
        }
    }

    private void saveColThree(MouseEvent event) {
        if (event.getSource() == imvColThree) {
            imgCurrentObject = imgColThree;
            imgCurrentObject = applyTransformations(imgCurrentObject, colThreeRotDegrees, 1);
            currentObjectName = "col3";
        }
    }

    private void saveColFour(MouseEvent event) {
        if (event.getSource() == imvColFour) {
            imgCurrentObject = imgColFour;
            imgCurrentObject = applyTransformations(imgCurrentObject, colFourRotDegrees, 1);
            currentObjectName = "col4";
        }
    }

    private void saveColCrownBeam(MouseEvent event) {
        if (event.getSource() == imvCrownBeam) {
            imgCurrentObject = imgColOneCrown;
            imgCurrentObject = applyTransformations(imgCurrentObject, crownBeamRotDegrees, 1);
            currentObjectName = "crown";
        }
    }

    @FXML
    private void clickSwitchPlant(ActionEvent event) {
        plantType = !plantType;
        paltLabel();

        if (!plantType) {
            Image tempWorkImage = cnvWorkSpace.snapshot(null, null);
            imvTempWorkImg.setImage(tempWorkImage);
        } else {
            imvTempWorkImg.setImage(null);
        }

        redrawCanvas();
    }

    private void paltLabel() {
        if (plantType) {
            lblPlantType.setText("ARCHITECTURAL");
            disableImageViewObjects(false);
        } else {
            lblPlantType.setText("STRUCTURAL");
            disableImageViewObjects(true);
        }
    }

    private void disableImageViewObjects(boolean disable) {
        imvDoor.setDisable(disable);
        imvWall.setDisable(disable);
        imvWindow.setDisable(disable);
        imvColOne.setDisable(!disable);
        imvColTwo.setDisable(!disable);
        imvColThree.setDisable(!disable);
        imvColFour.setDisable(!disable);
        imvCrownBeam.setDisable(!disable);
    }

    @FXML
    private void clickFlipDoor(ActionEvent event) {
        doorFlipValue = imvDoor.getScaleX() * -1;
        imvDoor.setScaleX(imvDoor.getScaleX() * -1);
    }

    @FXML
    private void clickRotateObject(ActionEvent event) {
        if (event.getSource() == btnRotateDoor) {
            rotateDoor();
        } else if (event.getSource() == btnRotateColOne) {
            rotateColOne();
        } else if (event.getSource() == btnRotateColTwo) {
            rotateColTwo();
        } else if (event.getSource() == btnRotateColThree) {
            rotateColThree();
        } else if (event.getSource() == btnRotateColFour) {
            rotateColFour();
        } else if (event.getSource() == btnRotateCrownBeam) {
            rotateCrownBeam();
        }
    }

    private void rotateDoor() {
        doorRotDegrees += 90;
        if (doorRotDegrees == 360) {
            doorRotDegrees = 0;
        }
        imvDoor.setRotate(doorRotDegrees);
    }

    private void rotateColOne() {
        colOneRotDegrees += 90;
        if (colOneRotDegrees == 360) {
            colOneRotDegrees = 0;
        }
        imvColOne.setRotate(colOneRotDegrees);
    }

    private void rotateColTwo() {
        colTwoRotDegrees += 90;
        if (colTwoRotDegrees == 360) {
            colTwoRotDegrees = 0;
        }
        imvColTwo.setRotate(colTwoRotDegrees);
    }

    private void rotateColThree() {
        colThreeRotDegrees += 90;
        if (colThreeRotDegrees == 360) {
            colThreeRotDegrees = 0;
        }
        imvColThree.setRotate(colThreeRotDegrees);
    }

    private void rotateColFour() {
        colFourRotDegrees += 90;
        if (colFourRotDegrees == 360) {
            colFourRotDegrees = 0;
        }
        imvColFour.setRotate(colFourRotDegrees);
    }

    private void rotateCrownBeam() {
        crownBeamRotDegrees += 90;
        if (crownBeamRotDegrees == 360) {
            crownBeamRotDegrees = 0;
        }
        imvCrownBeam.setRotate(crownBeamRotDegrees);
    }

    @FXML
    private void clickSelectProyect(ActionEvent event) {
        int index = tbvProyectList.getSelectionModel().getFocusedIndex();

        code = String.valueOf(columnProyectCode.getCellData(index));
        
        thread = new ChildThread("proyect", "queryProyect", code);
        thread.waitThreadEnd();
        
        String aux[] =  Parsing.parsingProyect(thread.getResponse());
        
        String koka = aux[5];
        
        thread = new ChildThread("object", "getObjects", koka);
        thread.waitThreadEnd();

        objectList = Parsing.parsingAllObjects(thread.getResponse());
        
        loadCanvasObjects();
        redrawCanvas();
        ubdateObjectTotals();
    }
    
    private void loadCanvasObjects(){
        selectedObject = null;
        dragImgArquitectural.clear();
        dragImgStructural.clear();
        
        for(ConstructionObject obj : objectList){
            DraggableImage draggableImage = null;
            if(obj.getObjectType().substring(0, 3).equals("col")){
                if(obj.getObjectType().equals("col1")){
                    loadColumn(draggableImage, obj, imgColOneCrown);
                }
                else if(obj.getObjectType().equals("col2")){
                    loadColumn(draggableImage, obj, imgColTwo);
                }
                else if(obj.getObjectType().equals("col3")){
                    loadColumn(draggableImage, obj, imgColThree);
                }
                else if(obj.getObjectType().equals("col4")){
                    loadColumn(draggableImage, obj, imgColFour);
                }
                else if(obj.getObjectType().equals("crown")){
                    loadColumn(draggableImage, obj, imgColOneCrown);
                }
            }
            else{
                if(obj.getObjectType().equals("door")){
                    loadDoor(draggableImage, obj);
                }
                else if(obj.getObjectType().equals("wall")){
                    loadWall(draggableImage, obj);
                }
                else if(obj.getObjectType().equals("window")){
                    loadWindow(draggableImage, obj);
                }
            }
        }
    }
    
    private void loadDoor(DraggableImage draggableImage, ConstructionObject obj){
        ImageView imageView;
        if(obj.getHeight() == 30){
            imageView = new ImageView(applyToLoadTransformations(imgDoorLarge, obj));
            draggableImage = new DraggableImage(imageView, obj.getPosX(), obj.getPosY(), cnvWorkSpace);
            dragImgArquitectural.add(draggableImage);
        }
        else if(obj.getHeight() == 27){
            imageView = new ImageView(applyToLoadTransformations(imgDoorMedium, obj));
            draggableImage = new DraggableImage(imageView, obj.getPosX(), obj.getPosY(), cnvWorkSpace);
            dragImgArquitectural.add(draggableImage);
        }
        else if(obj.getHeight() == 24){
            imageView = new ImageView(applyToLoadTransformations(imgDoorSmall, obj));
            draggableImage = new DraggableImage(imageView, obj.getPosX(), obj.getPosY(), cnvWorkSpace);
            dragImgArquitectural.add(draggableImage);
        }
    }
    
    private void loadWall(DraggableImage draggableImage, ConstructionObject obj){
        Rectangle wallRectangle = new Rectangle(obj.getWidth(), obj.getHeight(), Color.web("#333333"));
        draggableImage = new DraggableImage(wallRectangle, obj.getPosX(), obj.getPosY(), cnvWorkSpace);
        dragImgArquitectural.add(draggableImage);
    }
    
    private void loadWindow(DraggableImage draggableImage, ConstructionObject obj){
        Rectangle windowRectangle = new Rectangle(obj.getWidth(), obj.getHeight(), Color.web("#327fdd"));
        draggableImage = new DraggableImage(windowRectangle, obj.getPosX(), obj.getPosY(), cnvWorkSpace);
        dragImgArquitectural.add(draggableImage);
    }
    
    private void loadColumn(DraggableImage draggableImage, ConstructionObject obj, Image columnImage){
        ImageView imageView = new ImageView(applyToLoadTransformations(columnImage, obj));
        draggableImage = new DraggableImage(imageView, obj.getPosX(), obj.getPosY(), cnvWorkSpace);
        dragImgStructural.add(draggableImage);
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

    @FXML
    private void clickSave(ActionEvent event) {
        
        if("".equals(code)){
            showAlert("Before select project");
            return;
        }
        
        showInputDialog();
        
        thread = new ChildThread("constructionPaper", "newConstructionPaper", constructionsPaperName);
        thread.waitThreadEnd();
        
        thread = new ChildThread("constructionPaper", "getConstructionPaper", constructionsPaperName);
        thread.waitThreadEnd();
        
        idConstructionPaper = thread.getResponse();
        
        thread = new ChildThread("proyect", "updateProyectByCode", code+"|"+idConstructionPaper);
        thread.waitThreadEnd();
  
        saveObjects();
    }

    private void saveObjects() {
        for (ConstructionObject object : objectList) {
            thread = new ChildThread("object", "newObject", object.toString() + "|"+idConstructionPaper);
            thread.waitThreadEnd();
        }
        if ("Object created!".equals(thread.getResponse())) {
            showAlert("Construction paper saved!");
        }
    }

    @FXML
    private void clickClean(ActionEvent event) {
        if (selectedObject != null) {
            int indexArqui = 0;
            int indexStruct = 0;
            for (ConstructionObject obj : objectList){
                if(obj.equals(selectedObject)){
                    selectedObject = null;
                    objectList.remove(obj);
                    break;
                }

                if (obj.getObjectType().substring(0, 3).equals("col")) {
                    indexStruct++;
                } else {
                    indexArqui++;
                }
            }

            if (plantType) {
                dragImgArquitectural.remove(indexArqui);
            } else {
                dragImgStructural.remove(indexStruct);
            }
            redrawCanvas();
            ubdateObjectTotals();
        }
    }
}
