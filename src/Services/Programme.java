package Services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Programme extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("/Gui/Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
		}
		catch (IOException ex)
		{
			System.out.println(ex);
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
