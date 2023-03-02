/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PackagDao;
import Dao.ServiceDao;
import entities.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Comparator;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller class
 *
 * @author belhassan
 */
public class DisplayController implements Initializable {
    
    

     ObservableList<String> choices = FXCollections.observableArrayList(
    "Web",
    "Design",
    "Photography",
    "Video editing",
    "E-Commerce",
    "Others"
    
);
 
    FileChooser fileChooser = new FileChooser();
    @FXML
    private TableView<Service> servicetab;
    @FXML
    private TableColumn<Service, String> nmcol;

    @FXML
    private TableColumn<Service, String> desccol;

    @FXML
    private TableColumn<Service, Number> pricecol;
      @FXML
    private TableColumn<Service, String> catcol;
    @FXML
    private ChoiceBox<String> chbx;

    @FXML
    private WebView wb;
    @FXML
    private TextField pricefild;
    @FXML
    private Button delt;

    private ListData listdata;

    @FXML
    private TextField idfild;
      @FXML
    private TextField searchf;

    @FXML
    private TextField nmf;
 @FXML
    private Button st;
    @FXML
    private TextField descf;
    @FXML
    private Button ajout;
    @FXML
    private Button py;
    @FXML
    private Button butt;
    @FXML
    private TextField Pbasic;

    @FXML
    private TextField Pstandard;

    @FXML
    private TextField Pprem;

    @FXML
    private Button spri;
    @FXML
    private Label b;

    @FXML
    private Label s;

    @FXML
    private Label p;
     @FXML
    private Button sbt;
       @FXML
    private TextField iurl;

      @FXML
    private Button re;

    @FXML
    private Button upimg;
       @FXML
    private ImageView ifild;

   @FXML
    private AnchorPane AP;

    @FXML
    private Button sort;
    @FXML
    private StackPane SP;
private Stage stage;
    private Scene scene;
    private Parent root ;
 private static final String YOUTUBE_VIDEO_ID = "ggHACGb0mtU";


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
     chbx.getItems().addAll("All","Web", "Design","Photography","Video editing","E-Commerce","Others");
        chbx.getSelectionModel().selectFirst();
        
        chbx.setOnAction(event -> {
            String category = chbx.getValue();
            if (category.equals("All")) {
               servicetab.setItems(listdata.getServices());
            } else {
                ObservableList<Service> filteredList = listdata.getServices().filtered(product -> product.getCat().equals(category));
                servicetab.setItems(filteredList);
            }
        });
    
     
        ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddService.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AddServiceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        st.setOnAction(event -> {
                Notifications.create()
              .title("Statistics Notifications")
              .text("Statics calculating Underway !")
              .showWarning();
 
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Piechart.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PiechartController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        sort.setOnAction(e -> {
    servicetab.getItems().sort((item1, item2) -> Double.compare(item1.getPrix(), item2.getPrix()));
});

          re.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Show.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    
       

        listdata = new ListData();
        servicetab.setItems(listdata.getServices());

        nmcol.setCellValueFactory(cell -> cell.
                getValue().getNameProperty());

        desccol.setCellValueFactory(cell -> cell.
                getValue().getDescProperty());
        pricecol.setCellValueFactory(cell -> cell.
                getValue().getPrixProperty());
        catcol.setCellValueFactory(cell -> cell.
                getValue().getCatProperty());

        delt.setOnAction(event -> {
            Service s = servicetab.getSelectionModel().getSelectedItem();
            ServiceDao sdao = ServiceDao.getInstance();
            sdao.delete(s);
            
            servicetab.getItems().remove(s);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Service deleted!");
            alert.show();

        });
        upimg.setOnAction(
                event -> {
                    
                          
           Notifications.create()
              .title(" Notifications")
              .text("Upload Your Photo !")
                   
              .showWarning();
                    fileChooser.setTitle("Open File");
                    File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted.

                    if (file != null) {
                      
                        //creating the image object
                        InputStream stream;
                        try {
                            stream = new FileInputStream(file.getAbsolutePath());
                            Image image = new Image(stream);
                            String p= file.getAbsolutePath().replace("\\","@");
                            iurl.setText(p);
                          ifild.setImage(image);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(AddServiceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
);
        servicetab.setOnMouseClicked(event -> {
            idfild.setText(String.valueOf(listdata.getServices()
                    .get(servicetab.getSelectionModel().getSelectedIndex())
                    .getId()));

            nmf.setText(listdata.getServices()
                    .get(servicetab.getSelectionModel().getSelectedIndex())
                    .getName());

            descf.setText(listdata.getServices()
                    .get(servicetab.getSelectionModel().getSelectedIndex())
                    .getDesc());

            pricefild.setText(String.valueOf(listdata.getServices()
                    .get(servicetab.getSelectionModel().getSelectedIndex())
                    .getPrix()));
           iurl.setText(listdata.getServices()
                    .get(servicetab.getSelectionModel().getSelectedIndex())
                    .getFile());
              

            ServiceDao sdao = ServiceDao.getInstance();
            PackagDao pdao = PackagDao.getInstance();
            b.setText(sdao.inj(listdata.getServices().get(servicetab.getSelectionModel().getSelectedIndex())).get(0).getType());
            s.setText(sdao.inj(listdata.getServices().get(servicetab.getSelectionModel().getSelectedIndex())).get(1).getType());
            p.setText(sdao.inj(listdata.getServices().get(servicetab.getSelectionModel().getSelectedIndex())).get(2).getType());
            Pbasic.setText(String.valueOf(sdao.inj(listdata.getServices().get(servicetab.getSelectionModel().getSelectedIndex())).get(0).getPrice()));
            Pstandard.setText(String.valueOf(sdao.inj(listdata.getServices().get(servicetab.getSelectionModel().getSelectedIndex())).get(1).getPrice()));
            Pprem.setText(String.valueOf(sdao.inj(listdata.getServices().get(servicetab.getSelectionModel().getSelectedIndex())).get(2).getPrice()));

        });

 /* spri.setOnAction(event -> {

            ServiceDao sdao = ServiceDao.getInstance();
            int idPackage1 = sdao.inj(listdata.getServices().get(servicetab.getSelectionModel().getSelectedIndex())).get(0).getIdp();
           
            PackagDao pdao = PackagDao.getInstance();
            pdao.update(Integer.parseInt(Pbasic.getText()),idPackage1);
        

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("3asba");
            alert.show();

        });*/
 
 
        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        
        TextFormatter<String> f = new TextFormatter<>(numericFilter);
        pricefild.setTextFormatter(f);
        butt.setOnAction(event -> {

            ServiceDao sdao = ServiceDao.getInstance();
            sdao.update(Integer.parseInt(idfild.getText()), nmf.getText(), descf.getText(), Integer.parseInt(pricefild.getText()),iurl.getText()); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Service updtd!");
            alert.show();
            servicetab.getItems().clear();
            listdata = new ListData();
            servicetab.setItems(listdata.getServices());
            nmcol.setCellValueFactory(cell -> cell.
                    getValue().getNameProperty());
            desccol.setCellValueFactory(cell -> cell.
                    getValue().getDescProperty());
            pricecol.setCellValueFactory(cell -> cell.
                    getValue().getPrixProperty());
              catcol.setCellValueFactory(cell -> cell.
                    getValue().getCatProperty());

        });


sbt.setOnAction(event -> {
    String searchValue = searchf.getText().trim();
    if (searchValue.isEmpty()) {
       servicetab.setItems(listdata.getServices());
    } else {
        try {
            double searchPrice = Double.parseDouble(searchValue);
            ObservableList<Service> filteredProducts = listdata.getServices().filtered(product -> product.getPrix() == searchPrice);
            servicetab.setItems(filteredProducts);
        } catch (NumberFormatException e) {
            // Input is not a valid price
        }
    }
});



       

    }

}
