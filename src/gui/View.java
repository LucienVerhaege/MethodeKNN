package gui;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application{

	public static void main(String[] args) {
        View.launch(args);
    }
	
	@Override
	public void start(Stage stage) throws Exception {
		//Get FXML Informations
		FXMLLoader loader = new FXMLLoader(new File("chartview.fxml").toURI().toURL());
		Parent root = loader.load();
		
		//Get and Set the Controller
        ChartviewController control = loader.getController();
        control.initializeAll();
        
        //Display the scene
		Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("KNN Viewer");
        stage.show();
	}

}
