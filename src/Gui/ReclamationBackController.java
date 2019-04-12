/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reclamation;
import Entities.Service;
import Entities.User;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ReclamationBackController implements Initializable {

    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<Reclamation, User> UserReclamant;
    @FXML
    private TableColumn<Reclamation, User> UserReclame;
    @FXML
    private TableColumn<Reclamation, Date> dateReclamation;
    @FXML
    private TableColumn<Reclamation, Service> Service;
    @FXML
    private TableColumn<Reclamation, Date> dateRealisation;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private ComboBox<String> typeReclamation;
    @FXML
    private TextField textfield;
    @FXML
    private Pagination paginator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {

            UserReclamant.setCellValueFactory(new PropertyValueFactory<>("userReclamant"));
            UserReclame.setCellValueFactory(new PropertyValueFactory<>("userReclame"));
            dateReclamation.setCellValueFactory(new PropertyValueFactory<>("DateReclamation"));
            Service.setCellValueFactory(new PropertyValueFactory<>("idServiceRealise"));
            dateRealisation.setCellValueFactory(new PropertyValueFactory<>("dateRealisation"));
            objet.setCellValueFactory(new PropertyValueFactory<>("Objet"));
            description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            ReclamationService recServ = new ReclamationService();
            ReclamationService r = new ReclamationService();
            ObservableList<Reclamation> listRec = FXCollections.observableArrayList();
            listRec = recServ.getAllReclamation();
            ObservableList<String> listType = FXCollections.observableArrayList();
            listType.addAll("Archivé", "Non Traité", "Toutes les reclamation");
            typeReclamation.setItems(listType);
            paginator.setPageFactory(this::createPage);

            ObservableList data = tableReclamation.getItems();
            textfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (oldValue != null && (newValue.length() < oldValue.length())) {
                    tableReclamation.setItems(data);
                }
                String value = newValue.toLowerCase();
                ObservableList<Reclamation> subentries = FXCollections.observableArrayList();

                long count = tableReclamation.getColumns().stream().count();
                for (int i = 0; i < tableReclamation.getItems().size(); i++) {
                    for (int j = 0; j < count; j++) {
                        String entry = "" + tableReclamation.getColumns().get(j).getCellData(i);
                        System.out.println(entry);
                        if (entry.toLowerCase().contains(value)) {
                            subentries.add(tableReclamation.getItems().get(i));
                            break;
                        }
                    }
                }
                tableReclamation.setItems(subentries);
            });
        });
    }

    private Node createPage(int pageIndex) {
        ReclamationService recServ = new ReclamationService();

        ObservableList<Reclamation> data = FXCollections.observableArrayList();
        data = recServ.getAllReclamation();
        int fromIndex = pageIndex * 10;
        int toIndex = Math.min(fromIndex + 10, data.size());
        tableReclamation.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        return tableReclamation;
    }

    @FXML
    private void typeReclamationSelected(ActionEvent event) {
        UserReclamant.setCellValueFactory(new PropertyValueFactory<>("userReclamant"));
        UserReclame.setCellValueFactory(new PropertyValueFactory<>("userReclame"));
        dateReclamation.setCellValueFactory(new PropertyValueFactory<>("DateReclamation"));
        Service.setCellValueFactory(new PropertyValueFactory<>("idServiceRealise"));
        dateRealisation.setCellValueFactory(new PropertyValueFactory<>("dateRealisation"));
        objet.setCellValueFactory(new PropertyValueFactory<>("Objet"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ReclamationService recServ = new ReclamationService();
        ReclamationService r = new ReclamationService();
        if (typeReclamation.getValue() == "Archivé") {
            ObservableList<Reclamation> listRec = FXCollections.observableArrayList();
            listRec = recServ.getReclamationArchive();
            tableReclamation.setItems(listRec);
        }
        if (typeReclamation.getValue() == "Non Traité") {
            ObservableList<Reclamation> listRec = FXCollections.observableArrayList();
            listRec = recServ.getReclamationNonTraite();
            tableReclamation.setItems(listRec);
        }
        if (typeReclamation.getValue().equals("Toutes les reclamation")) {
            ObservableList<Reclamation> listRec = FXCollections.observableArrayList();
            listRec = recServ.getAllReclamation();
            tableReclamation.setItems(listRec);
        }
    }

    @FXML
    private void detailsReclamation(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/detailReclamationBack.fxml"));
                Parent Rec = fxmlLoader.load();
                Scene scene = new Scene(Rec);
                DetailReclamationBackController controller = fxmlLoader.<DetailReclamationBackController>getController();
                Reclamation rec = new Reclamation();
                rec = tableReclamation.getSelectionModel().getSelectedItem();
                controller.setReclamation(rec);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.show();
                stage.setScene(scene);

            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

}
