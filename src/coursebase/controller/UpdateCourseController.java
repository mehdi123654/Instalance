/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.controller;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import com.twilio.Twilio;
import coursebase.dao.CourseDao;
import coursebase.dao.LessonDao;
import coursebase.entity.Course;
import coursebase.entity.Lesson;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author user
 */
public class UpdateCourseController implements Initializable {

    @FXML
    private Button svl;
    @FXML
    private TextField id_field;

    @FXML
    private TextField title_field;

    @FXML
    private TextField video;
    @FXML
    private TextField price_field;

    ListLesson listdata;
    @FXML
    private Label photo_field;

    @FXML
    private Button update_butt;

    @FXML
    private Button kk;
    @FXML
    private Button back;
    @FXML
    private ChoiceBox<String> categ_choice;
    @FXML
    private TableView<Lesson> lesstable;
    @FXML
    private Button delall;
    @FXML
    private Button dl;

    @FXML
    private TableColumn<Lesson, String> lessonTitlefield;
    @FXML
    private Button lsnad;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button search;
    @FXML
    private TextField tl;

    @FXML
    private Button sf;

    @FXML
    private Label sl;

    @FXML
    private Label t;

    @FXML
    private Button cn;
    @FXML
    private Label f;
    @FXML
    private Button lsup;
      @FXML
    private AnchorPane a;
    private static final String ACCOUNT_SID = "AC1af823561ae9d4ad22591524de127ce6";
    private static final String AUTH_TOKEN = "be176fe5aec60b39d9f8fde9b73a6132";
    /**
     * Initializes the controller class.
     *
     * @param id
     * @param desc
     * @param title
     * @param price
     * @param photo
     * @param catg
     */
    FileChooser fileChooser = new FileChooser();

    public void displayName(String id, String desc, String title, String price, String photo, String catg) {

        id_field.setText(id);
        video.setText(desc);
        title_field.setText(title);
        price_field.setText(price);
        photo_field.setText(photo);
        categ_choice.setValue(catg);

        search.setOnAction(
                event -> {

                    fileChooser.setTitle("Open File");
                    File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted.

                    if (file != null) {
                        photo_field.setText(file.getAbsolutePath());
                        //creating the image object

                    }
                }
        );
        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> formatter = new TextFormatter<>(numericFilter);
        price_field.setTextFormatter(formatter);
        update_butt.setOnAction(
                event -> {
                    String selectedChoice = categ_choice.getSelectionModel().getSelectedItem();

                    Course p = new Course(Integer.parseInt(id_field.getText()), title_field.getText(), video.getText(), Integer.parseInt(price_field.getText()), selectedChoice, photo_field.getText());
                    CourseDao pdao = CourseDao.getInstance();
                    pdao.update(p);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Course updated succesfully !");
                    alert.show();

                });

        back.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/ShowCourse.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ListLesson listdata = new ListLesson(Integer.parseInt(id_field.getText()));
        lesstable.setItems(listdata.getJoins());

        lessonTitlefield.setCellValueFactory(cell -> cell.getValue().getNameProperty());

        id_field.setVisible(false);
        delall.setOnAction(event -> {

            ObservableList<Lesson> items = lesstable.getItems();

            items.stream().map((item) -> item.getLid()).forEachOrdered((valueOf) -> {
                LessonDao pdao = LessonDao.getInstance();
                pdao.delete(valueOf);
            });
            lesstable.getItems().clear();
        });
        dl.setOnAction(event -> {

            CourseDao pdao = CourseDao.getInstance();
            pdao.delete(Integer.parseInt(id_field.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("course deleted !");
            alert.show();
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/ShowCourse.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        kk.setOnAction(event -> {

            LessonDao dao = LessonDao.getInstance();
            dao.delete(lesstable.getSelectionModel().getSelectedItem().getLid());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Lesson deleted !");
            alert.show();
            Lesson selectedItem = lesstable.getSelectionModel().getSelectedItem();
            lesstable.getItems().remove(selectedItem);

        });
        categ_choice.setItems(FXCollections.observableArrayList("Development", "Bussiness", "Marketing", "Mathematics"));
        sf.setOnAction(
                event -> {

                    fileChooser.setTitle("Open File");
                    File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted.
                    String p = file.getAbsolutePath().replace("\\", "@");
                    sl.setText(p);

                }
        );
        
        tl.setVisible(false);
        sf.setVisible(false);
        sl.setVisible(false);
        t.setVisible(false);
        f.setVisible(false);
        cn.setVisible(false);
        svl.setVisible(false);

        lsnad.setOnAction(event -> {
            tl.setVisible(true);
            tl.setText("");
            sl.setText("");
            sf.setVisible(true);
            sl.setVisible(true);
            t.setVisible(true);
            f.setVisible(true);
            cn.setVisible(true);
            svl.setVisible(false);
            cn.setOnAction(eve -> {
                if (id_field.getText() != null && tl.getText() != null && tl.getText() != null && sl.getText() != null) {
                    Lesson p = new Lesson(Integer.parseInt(id_field.getText()), tl.getText(), sl.getText());
                    LessonDao pdao = LessonDao.getInstance();
                    pdao.insert(p);
                    lesstable.getItems().clear();
                    ListLesson listdatak = new ListLesson(Integer.parseInt(id_field.getText()));

                    lesstable.setItems(listdatak.getJoins());
                    lessonTitlefield.setCellValueFactory(cell -> cell.getValue().getNameProperty());
                    tl.setVisible(false);
                    sf.setVisible(false);
                    sl.setVisible(false);
                    t.setVisible(false);
                    f.setVisible(false);
                    cn.setVisible(false);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all fields!");
                    alert.show();
                }
            });

        });
        lsup.setOnAction(event -> {
            if (lesstable.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You must select a lesson to modify!");
                alert.show();
            } else {
                tl.setVisible(true);
                sf.setVisible(true);
                sl.setVisible(true);
                t.setVisible(true);
                f.setVisible(true);
                svl.setVisible(true);
                cn.setVisible(false);
                tl.setText(lesstable.getSelectionModel().getSelectedItem().getName());
                sl.setText(lesstable.getSelectionModel().getSelectedItem().getFile());
                svl.setOnAction(eve -> {
                    Lesson p = new Lesson(lesstable.getSelectionModel().getSelectedItem().getLid(), lesstable.getSelectionModel().getSelectedItem().getCid(), tl.getText(), sl.getText());
                    LessonDao s = LessonDao.getInstance();
                    s.update(p);

                    lesstable.getItems().clear();
                    ListLesson listdatak = new ListLesson(Integer.parseInt(id_field.getText()));

                    lesstable.setItems(listdatak.getJoins());
                    lessonTitlefield.setCellValueFactory(cell -> cell.getValue().getNameProperty());
                    tl.setVisible(false);
                    sf.setVisible(false);
                    sl.setVisible(false);
                    t.setVisible(false);
                    f.setVisible(false);
                    svl.setVisible(false);
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(
                            new PhoneNumber("+21656152776"), // recipient's phone number
                            new PhoneNumber("+12766638814"), // your Twilio phone number
                            "Lesson : " + p.getName() + " updated !"
                    ).create();
                    System.out.println("Sent message SID: " + message.getSid());

                });
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
