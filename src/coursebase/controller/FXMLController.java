/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursebase.controller;

import coursebase.entity.Lesson;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLController implements Initializable {

    ScrollPane scrollPane;

    @FXML
    private Button buy;

    @FXML
    private TableView<Lesson> lesstable;

    @FXML
    private TableColumn<Lesson, String> cl;
    @FXML
    private Label coursename;
    @FXML
    private WebView wb;

    @FXML
    private Button b;

    @FXML
    private AnchorPane s;
    private Stage stage;
    private Scene scene;
    private Parent root;

    void displayName(String h, String a, String vid) {
         b.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/coursebase/view/explore.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        lesstable.getParent().setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                lesstable.getSelectionModel().clearSelection();
                wb.setVisible(true);
                //  scrollPane.setVisible(false);
                wb.toFront();

            }
        });
        wb.getEngine().load(vid);
        coursename.setText(a);

        ListLesson listdata = new ListLesson(Integer.parseInt(h));
        lesstable.setItems(listdata.getJoins());

        cl.setCellValueFactory(cell -> cell.getValue().getNameProperty());
        lesstable.setOnMouseClicked(event -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You must pay to access lessons!");
            alert.show();
        });
        buy.setOnAction(event -> {
            buy.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can now access all lessons!");
            alert.show();
            lesstable.setOnMouseClicked(e -> {

                wb.setVisible(false);

                String p = lesstable.getSelectionModel().getSelectedItem().getFile().replace("@", "\\");

                PDDocument document;
                try {
                    // do something with the document
                    document = PDDocument.load(new File(p));
                    PDFRenderer renderer = new PDFRenderer(document);
                    VBox j = new VBox();
                    for (int i = 0; i < document.getNumberOfPages(); i++) {
                        // Render the PDF page as a BufferedImage
                        java.awt.image.BufferedImage bImage = renderer.renderImageWithDPI(i, 72);

                        // Convert the BufferedImage to a JavaFX Image
                        Image image = SwingFXUtils.toFXImage(bImage, null);

                        // Create an ImageView for the Image
                        ImageView imageView = new ImageView(image);

                        // Add the ImageView to the VBox
                        j.getChildren().add(imageView);
                        scrollPane = new ScrollPane(j);
                        scrollPane.setTranslateX(100);
                        scrollPane.setTranslateY(125);
                        // Set the preferred size of the ScrollPane
                        scrollPane.setPrefSize(600, 500);
                        s.getChildren().add(scrollPane);

                    }
                    document.close();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
