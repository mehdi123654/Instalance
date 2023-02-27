/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.controller;

import coursebase.dao.LessonDao;
import coursebase.entity.Lesson;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddLessonController implements Initializable {

    @FXML
    private Label CID;
    FileChooser fileChooser = new FileChooser();
    @FXML
    private TextField name;

    @FXML
    private Label fil;

    @FXML
    private Button searchbutt;
    @FXML
    private Button addLess;
    private String id;
    @FXML
    private Button shwall;

    /**
     * Initializes the controller class.
     *
     * @param username
     */
    public void displayName(String username) {
        CID.setText(username);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 fileChooser.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("pdf files",".pdf")
 );
        searchbutt.setOnAction(
                event -> {

                    fileChooser.setTitle("Open File");
                    File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted.
                    String p = file.getAbsolutePath().replace("\\", "@");
                    fil.setText(p);

                }
        );
        shwall.setOnAction(
                event -> {
                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/ShowCourse.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        addLess.setOnAction(
                event -> {
                    Lesson p = new Lesson(Integer.parseInt(CID.getText()), name.getText(), fil.getText());
                    LessonDao pdao = LessonDao.getInstance();
                    pdao.insert(p);

                    try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/ShowCourse.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
        );
        CID.setVisible(false);
    }

}
