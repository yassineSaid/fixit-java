package Services;

import Entities.CategorieProduit;
import Entities.produit;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Programme extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
		}
		catch (IOException ex)
		{
			System.out.println(ex);
		}
	}
	public static void main(String[] args) throws ParseException {
            
		launch(args);
                /*
Produit p;
int myYear = 2014;
int myMonth = 3;
int myDay = 24;
Timestamp ts = Timestamp.valueOf(String.format("%04d-%02d-%02d 00:00:00", 
                                                myYear, myMonth, myDay));
CategorieProduit c= new CategorieProduit("cosmitique","fdgf","zdzd",41);
produit p1=new produit("ffm",2,c,52,ts ,4,"fjeifj");
p.ajouterProduit(p1);
*/ 
	}

}
