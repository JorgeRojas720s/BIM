/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.bim.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Proyect;
import model.User;
import utils.ChildThread;
import utils.Parsing;
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
    private boolean switchTblv = false;
    private boolean allowProyect = true;
<<<<<<< HEAD
    private boolean aux;
    private String proyectName = "";
    private String startDate = "";
    private String engineerName = "";
    private String designerName = "";
    private String constructionPaperName = "";

=======
    private boolean panelControll;
>>>>>>> fabiux
    ChildThread thread;

    private List<ConstructionObject> objectList = new ArrayList<>();
    private Canvas cnvAuxSpace = new Canvas(909, 631);
    private GraphicsContext gc;

    private Image imgDoorLarge = new Image(getClass().getResourceAsStream("/images/doorOne.png"), 30, 30, false, false);
    private Image imgDoorMedium = new Image(getClass().getResourceAsStream("/images/doorTwo.png"), 27, 27, false, false);
    private Image imgDoorSmall = new Image(getClass().getResourceAsStream("/images/doorThree.png"), 24, 24, false, false);
    private Image imgColOneCrown = new Image(getClass().getResourceAsStream("/images/col1_crown.png"), 20, 20, false, false);
    private Image imgColTwo = new Image(getClass().getResourceAsStream("/images/col2.png"), 20, 20, false, false);
    private Image imgColThree = new Image(getClass().getResourceAsStream("/images/col3.png"), 20, 20, false, false);
    private Image imgColFour = new Image(getClass().getResourceAsStream("/images/col4.png"), 20, 20, false, false);

    private String data = "";
    private String code = "";
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
    private TableView<Proyect> tbvProyectList;
    private ObservableList<String> proyectList = FXCollections.observableArrayList();
    @FXML
    private Label lblEngineerName;
    @FXML
    private TableColumn<Proyect, String> columnProyectName;
    @FXML
    private TableColumn<Proyect, String> columnProyectEndDate;

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
    private TextField txtProyectCode;
    @FXML
    private TableColumn<Proyect, String> columnProyectCode;
    @FXML
    private TableColumn<Proyect, String> columnProyectStartDate;
    @FXML
    private Button btnSwitchTblv;
    @FXML
    private TableView<User> tblvUserList;
    @FXML
    private TableColumn<User, String> columnUserId;
    @FXML
    private TableColumn<User, String> columnUserName;
    @FXML
    private TableColumn<User, String> columnUserRole;
    @FXML
    private TableColumn<User, String> columnUserStatus;
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
    @FXML
    private Label lblEngineerLastName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setNameEngineer(LogInController.userName);
        gc = cnvAuxSpace.getGraphicsContext2D();

        updateTableViewUsers();
        updateTableViewProyects();
        fillTableViewProyects();
        fillTableViewUsers();
    }
    
    private void setNameEngineer(String name) {

        thread = new ChildThread("user", "getName", name);
        thread.waitThreadEnd();
        String adminData[] = Parsing.parsingUser(thread.getResponse());

        lblEngineerName.setText(adminData[0]);
        lblEngineerLastName.setText(adminData[1]);
    }

    private void fillTableViewUsers() {

        columnUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnUserRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        columnUserStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void updateTableViewUsers() {

        thread = new ChildThread("user", "getEngAndDesig", " ");
        thread.waitThreadEnd();
        data = thread.getResponse();

        List<User> listaUsers = Parsing.parsingAllUsers(data);

        ObservableList<User> userObservableList = FXCollections.observableArrayList(listaUsers);
        tblvUserList.setItems(userObservableList);
    }

    private void fillTableViewProyects() {

        columnProyectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnProyectCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        columnProyectStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnProyectEndDate.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
    }

    private void updateTableViewProyects() {

        thread = new ChildThread("proyect", "getAllProyects", " ");
        thread.waitThreadEnd();
        data = thread.getResponse();

        List<Proyect> listaProyects = Parsing.parsingAllProyects(data);

        ObservableList<Proyect> proyectObservableList = FXCollections.observableArrayList(listaProyects);
        tbvProyectList.setItems(proyectObservableList);
    }

    private void showMessageLabelsVisible(boolean name, boolean message) {
        lblEngineerName.setVisible(name);
        lblMessage.setVisible(message);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    private void showDoNotButtonsVisible(boolean btn1, boolean btn2, boolean btn3) {
        btnDoNotCreate1.setVisible(btn1);
    }

    @FXML
    private void clickCreateProyect(ActionEvent event) {
        clearProyects();
        panelControll = true;
        creatProyectPressed = true;
        setTextToProgressButtons("Create");
        showAnchorPanesVisible(true, false, false, true, false);
        showMessageLabelsVisible(false, false);
        showDoNotButtonsVisible(true, true, true);
    }

    @FXML
    private void clickModifyProyect(ActionEvent event) {
        panelControll = false;
        if (!creatProyectPressed && !tbvProyectList.getSelectionModel().isEmpty()) {
            showAnchorPanesVisible(true, false, false, true, false);
            modifyLblAndProgressParameters();
            showDoNotButtonsVisible(false, false, false);
        }
    }

    private void addProyect() {

        String codex = txtProyectCode.getText();
        String name = txtProyectName.getText();
        String engineer = txtProyectEngineer.getText();
        String designer = txtProyectDesigner.getText();

        LocalDate startDate = dateStartProyect.getValue();
        String dateStart = (startDate != null) ? startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

        LocalDate finishDate = dateEndProyect.getValue();
        String dateFinish = (finishDate != null) ? finishDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

        if (dateStart == null || dateFinish == null) {
            return;
        }

        if (!"".equals(name)) {
            Proyect proyect = new Proyect(codex, name, dateStart, dateFinish, engineer, designer);
            thread = new ChildThread("proyect", "newProyect", proyect.toString());
            thread.waitThreadEnd();

            if ("The project already exists".equals(thread.getResponse())) {
                allowProyect = false;
                showAlert("The project already exists");
            }
        }
        updateTableViewProyects();
    }

    private void modifyProyect() {

        String codex = txtProyectCode.getText();
        String name = txtProyectName.getText();
        String engineer = txtProyectEngineer.getText();
        String designer = txtProyectDesigner.getText();
        LocalDate startDate = dateStartProyect.getValue();
        String dateStart = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate finishDate = dateEndProyect.getValue();
        String dateFinish = finishDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Proyect proyect = new Proyect(codex, name, dateStart, dateFinish, engineer, designer);
        thread = new ChildThread("proyect", "updateProyect", proyect.toString());
        thread.waitThreadEnd();

        serverResponses("Updated project", thread);
        serverResponses("Project not found", thread);
        serverResponses("Invalid engineer or designer", thread);
        updateTableViewProyects();
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
<<<<<<< HEAD

    private void getProyect() {

=======
    
    private void getProyect(){
>>>>>>> fabiux
        int index = tbvProyectList.getSelectionModel().getFocusedIndex();

        code = String.valueOf(columnProyectCode.getCellData(index));

        thread = new ChildThread("proyect", "queryProyect", code);
        thread.waitThreadEnd();

        String dataParse[] = Parsing.parsingProyect(thread.getResponse());

        proyectName = dataParse[0];
        startDate = dataParse[1];
        String engineerId = dataParse[3];
        String designerId = dataParse[4];
        String constructionPaperId = dataParse[5];

        getEngineerData(engineerId);
        getDesignerData(designerId);
        getContructionPaperData(constructionPaperId);

        thread = new ChildThread("object", "getObjects", constructionPaperId);
        thread.waitThreadEnd();

        objectList = Parsing.parsingAllObjects(thread.getResponse());

    }

    private void getEngineerData(String engineerId) {

        thread = new ChildThread("user", "queryUser", engineerId);
        thread.waitThreadEnd();

        String engineerData[] = Parsing.parsingUser(thread.getResponse());

        engineerName = engineerData[0];

    }

    private void getDesignerData(String designerId) {

        thread = new ChildThread("user", "queryUser", designerId);
        thread.waitThreadEnd();

        String designerData[] = Parsing.parsingUser(thread.getResponse());

        designerName = designerData[0];
    }

    private void getContructionPaperData(String constructionPaperId) {

        thread = new ChildThread("constructionPaper", "getNameConstructionPaper", constructionPaperId);
        thread.waitThreadEnd();

        constructionPaperName = thread.getResponse();
    }

    @FXML
    private void clickGenerateReport(ActionEvent event) {
        if (!creatProyectPressed && !tbvProyectList.getSelectionModel().isEmpty()) {
            showAnchorPanesVisible(false, false, false, false, true);
            modifyLblAndProgressParameters();
            getProyect();
            loadCanvasObjects();
        }
    }
<<<<<<< HEAD

    private void loadCanvasObjects() {

=======
    
    private void loadCanvasObjects(){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
>>>>>>> fabiux
        int plantSelector = 0;
        while (plantSelector != 2) {
            for (ConstructionObject obj : objectList) {
                if (obj.getObjectType().substring(0, 3).equals("col") && plantSelector == 1) {
                    if (obj.getObjectType().equals("col1")) {
                        loadColumn(obj, imgColOneCrown);
                    } else if (obj.getObjectType().equals("col2")) {
                        loadColumn(obj, imgColTwo);
                    } else if (obj.getObjectType().equals("col3")) {
                        loadColumn(obj, imgColThree);
                    } else if (obj.getObjectType().equals("col4")) {
                        loadColumn(obj, imgColFour);
                    } else if (obj.getObjectType().equals("crown")) {
                        loadColumn(obj, imgColOneCrown);
                    }
                } else if (plantSelector == 0) {
                    if (obj.getObjectType().equals("door")) {
                        loadDoor(obj);
                    } else if (obj.getObjectType().equals("wall")) {
                        loadWall(obj);
                    } else if (obj.getObjectType().equals("window")) {
                        loadWindow(obj);
                    }
                }
            }
            if (plantSelector == 0) {
                imvArchitecturalPlant.setImage(cnvAuxSpace.snapshot(null, null));
            } else if (plantSelector == 1) {
                imvStructuralPlant.setImage(cnvAuxSpace.snapshot(null, null));
            }
            plantSelector++;
        }
    }

    private void loadDoor(ConstructionObject obj) {
        ImageView imageView = null;
        if (obj.getHeight() == 30) {
            imageView = new ImageView(applyToLoadTransformations(imgDoorLarge, obj));
        } else if (obj.getHeight() == 27) {
            imageView = new ImageView(applyToLoadTransformations(imgDoorMedium, obj));
        } else if (obj.getHeight() == 24) {
            imageView = new ImageView(applyToLoadTransformations(imgDoorSmall, obj));
        }
        drawCanvas(obj, imageView);
    }

    private void loadWall(ConstructionObject obj) {
        Rectangle wallRectangle = new Rectangle(obj.getWidth(), obj.getHeight(), Color.web("#333333"));
        drawCanvas(obj, wallRectangle);
    }

    private void loadWindow(ConstructionObject obj) {
        Rectangle windowRectangle = new Rectangle(obj.getWidth(), obj.getHeight(), Color.web("#327fdd"));
        drawCanvas(obj, windowRectangle);
    }

    private void loadColumn(ConstructionObject obj, Image columnImage) {
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

    private void showCreateProyect() {

        if (handleExpetionsProyects("Please fill out all fields")) {

            addProyect();
            if (allowProyect) {
                creatProyectPressed = false;
                showAnchorPanesVisible(false, false, false, true, false);
                showMessageLabelsVisible(false, true);
                lblMessage.setText("Proyect Created!!");
                clearProyects();
            }
            allowProyect = true;
        }
    }

    private void showModifyProyect() {

        clearProyects();
        showAnchorPanesVisible(false, false, false, true, false);
        if (handleExpetionsProyects("Please fill out all fields")) {
            modifyProyect();
            correctlyModifiedMessage();
        }
    }

    @FXML
    private void clickProyectProcess(ActionEvent event) {
        if (panelControll) {
            showCreateProyect();
        } else {
            showModifyProyect();
        }
    }

    private boolean handleExpetionsProyects(String message) {

        if ("".equals(txtProyectCode.getText()) || "".equals(txtProyectName.getText())
                || "".equals(txtProyectEngineer.getText()) || "".equals(txtProyectDesigner.getText())
                || dateStartProyect.getValue() == null || dateEndProyect.getValue() == null) {

            showAlert(message);
            return false;
        }
        return true;
    }

    @FXML
    private void clikSwitchTblv(ActionEvent event) {

        if (switchTblv) {
            tbvProyectList.setVisible(true);
            tbvProyectList.setDisable(false);
            tblvUserList.setVisible(false);
            tblvUserList.setDisable(true);
            switchTblv = false;
        } else {
            tblvUserList.setVisible(true);
            tblvUserList.setDisable(false);
            tbvProyectList.setVisible(false);
            tbvProyectList.setDisable(true);
            switchTblv = true;
        }

    }

    private void clearProyects() {

        txtProyectCode.setText("");
        txtProyectName.setText("");
        txtProyectEngineer.setText("");
        txtProyectDesigner.setText("");
        dateStartProyect.setValue(null);
        dateEndProyect.setValue(null);
    }

    @FXML
    private void clickGetProyectCode(MouseEvent event) {
        if(!creatProyectPressed){
            int index = tbvProyectList.getSelectionModel().getFocusedIndex();
            String codex = columnProyectCode.getCellData(index);

            thread = new ChildThread("proyect", "queryProyect", codex + "|");
            thread.waitThreadEnd();

            if ("No se encontro".equals(thread.getResponse())) {
                showAlert("There is no project with that code");
                return;
            }

            String[] proyect = Parsing.parsingProyect(thread.getResponse());

            String name = proyect[0];

            String starDate = proyect[1];
            LocalDate startDate = LocalDate.parse(starDate);

            String endDate = proyect[2];
            LocalDate finishDate = LocalDate.parse(endDate);

            String designer = proyect[3];
            String engineer = proyect[4];

            txtProyectCode.setText(codex);
            txtProyectName.setText(name);
            txtProyectEngineer.setText(engineer);
            txtProyectDesigner.setText(designer);
            dateStartProyect.setValue(startDate);
            dateEndProyect.setValue(finishDate);
        }

    }

    @FXML
    private void clickGetUserId(MouseEvent event) {

        int index = tblvUserList.getSelectionModel().getFocusedIndex();

        String id = String.valueOf(columnUserId.getCellData(index));
        String role = columnUserRole.getCellData(index);

        System.out.println("ID:" + id);

        if ("Designer".equals(role)) {
            txtProyectDesigner.setText(id);
        } else if ("Engineer".equals(role)) {
            txtProyectEngineer.setText(id);
        }
    }

    private void serverResponses(String text, ChildThread threadS) {

        if (text.equals(threadS.getResponse())) {
            showAlert(text);
        }
    }
}
