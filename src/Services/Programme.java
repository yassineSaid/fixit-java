package Services;

import java.io.IOException;
import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.Connexion;

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
			
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
