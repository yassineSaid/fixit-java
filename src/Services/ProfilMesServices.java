/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ServiceUser;
import Gui.ProfilController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author SELON
 */
public class ProfilMesServices extends ListCell<ServiceUser>{

    public ProfilMesServices() {
    }
    
    
    protected void updateItem(ServiceUser item, boolean bln) {
            super.updateItem(item, bln);
            if (item != null) {
                try {
                    ServiceUserService ss=new ServiceUserService();
                    Text nom = new Text(ss.getServiceName(item.getIdService()));
                    Text description = new Text(item.getDescription());
                    Text prix = new Text(Float.toString(item.getPrix()));
                    Text s =new Text("SCoins");
                    description.setWrappingWidth(300);;
                    nom.setStyle("-fx-font-size: 25 arial;");
                    description.setStyle("-fx-font-size: 15 arial;"
                            + "-fx-pref-width: 158px;");
                    VBox vBox = new VBox(nom, description);
                    vBox.setStyle("-fx-font-color: transparent;");
                    vBox.setSpacing(10);
                    
                    Image image = new Image("file:/wamp64/www/fixit/web/uploads/images/service/" + ss.getServiceImage(item.getIdService()), 120, 120, false, false);
                    ImageView img = new ImageView(image);
                    
                    HBox hBox = new HBox(img, vBox,prix,s);
                    hBox.setStyle("-fx-font-color: transparent;");
                    hBox.setSpacing(10);
                    setGraphic(hBox);
                    /*   listCategorie.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                    
                    CategorieService a = (CategorieService) listCategorie.getItems().get(listCategorie.getSelectionModel().getSelectedIndex());
                    loadServiceFromDatabase(a.getId());
                    listService.setCellFactory(lv -> new PoulesService());
                    System.out.println("test");
                    }
                    });*/
                } catch (SQLException ex) {
                    Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    
}
