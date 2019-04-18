/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Message;
import Entities.User;
import Services.MessageService;
import Services.Utils;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ConversationController implements Initializable {

    private User current;
    private User contacted;
    @FXML
    private ListView<VBox> messages;
    @FXML
    private TextArea nouveauMessage;

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public User getContacted() {
        return contacted;
    }

    public void setContacted(User contacted) {
        this.contacted = contacted;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            loadMessages();
        });
    }    

    public void loadMessages()
    {
        MessageService ms=new MessageService();
        ObservableList<Message> data;
        ObservableList<VBox> listHbox=FXCollections.observableArrayList();
        data = ms.getConversation(current.getId(), contacted.getId());
        for (Message m : data)
        {
            HBox h=new HBox();
            VBox v=new VBox();
            String nom;
            Label contenu=new Label(m.getContenu());
            if (m.getExpediteur()==current.getId()) {
                nom=Utils.upperCaseFirst(current.getFirstname());
                v.setAlignment(Pos.CENTER_RIGHT);
                contenu.setAlignment(Pos.CENTER_RIGHT);
            }
            else {
                nom=Utils.upperCaseFirst(contacted.getFirstname());
                v.setAlignment(Pos.CENTER_LEFT);
                contenu.setAlignment(Pos.CENTER_LEFT);
            }
            
            
            contenu.setFont(Font.font(18));
            contenu.setWrapText(true);
            contenu.setMaxWidth(700);
            
            Label heure=new Label("Par "+nom+", le "+Utils.formatDateTime(m.getDate()));
            heure.setFont(Font.font(10));
            
            v.getChildren().add(contenu);
            v.getChildren().add(heure);
            
            listHbox.add(v);
        }
        messages.setItems(listHbox);
        messages.scrollTo(messages.getItems().size()-1);
    }
    
    @FXML
    private void quitterAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void verifierAction(ActionEvent event) {
        if (nouveauMessage.getText().length()<1)
        {
            
        }
        else
        {
            MessageService ms=new MessageService();
            ms.envoyerMessage(new Message(0, current.getId(), contacted.getId(), true, new Timestamp(System.currentTimeMillis()), nouveauMessage.getText()));
            loadMessages();
        }
    }
    
}
