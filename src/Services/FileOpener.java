/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author SL-WASSIM
 */
import Services.CategorieOutilService;
import Entities.CategorieOutil;
import Services.OutilService;
import Entities.Outil;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/*public class test {
     public static void main(String[] args) {
        CategorieOutilService sm = new CategorieOutilService();
         CategorieOutil M1 =new CategorieOutil("slim","baba");
         //sm.ajouterCategorie(M1);
         M1.setNom("mohsen");
         M1.setId(15);
         //sm.modifierCategorie(M1);
         sm.supprimerCategorie(17);
         sm.afficherCategorie();
        Outil o = new Outil("wassim",2,6,84,"tunis",4444,"ppp","mmmm",7);
        OutilService om = new OutilService();
        o.setPrix(999);
        o.setId(28);
        //om.ajouterOutil(o);
        om.modifierOutil(o);
        //om.afficherOutil();
         
    }*/
public class FileOpener extends Application {

    public void start(final Stage stage) {
        stage.setTitle("File Chooser Sample");

        final FileChooser fileChooser = new FileChooser();
       // FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(.jpg)",".JPG");
      //  FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(.png)",".PNG");
      //  fileChooser.getExtensionFilters().addAll(ext1,ext2);

        final Button openButton = new Button("Choose Background Image");
        openButton.setOnAction((final ActionEvent e) -> {
            
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                // openFile(file);

                // where my problem is 
                Image image = new Image(file.toURI().toString());
                // what I tried to do
                // Image image1 = new Image(file);
                ImageView ip = new ImageView(image);
              BufferedOutputStream stream = null;
        String globalPath="E:\\Nouveau_dossier";
        String localPath="localhost:80/";
        String fileName = file.getName();
        fileName=fileName.replace(" ", "_");
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            //return localPath+"/"+fileName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOpener.class.getName()).log(Level.SEVERE, null, ex);
           // return "error1";
        } catch (IOException ex) {
            Logger.getLogger(FileOpener.class.getName()).log(Level.SEVERE, null, ex);
            //return "error2";
        }
                 VBox root = new VBox(10, ip,openButton);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("");
                stage.show();
            }
        });
        final StackPane stac = new StackPane();       
        stac.getChildren().add(openButton);
        stage.setScene(new Scene(stac, 500, 500));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
