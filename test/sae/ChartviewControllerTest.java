package sae;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import gui.ChartviewController;
import javafx.fxml.FXMLLoader;

public class ChartviewControllerTest {

	 @Test
	 void testChartViewInitialization() throws IOException {
		 javafx.application.Platform.startup(()->{});
		 FXMLLoader loader = new FXMLLoader(new File("chartview.fxml").toURI().toURL());
			loader.load();
			
			//Get and Set the Controller
	        ChartviewController control = loader.getController();
	        control.initializeAll();
	        assertNotEquals(null, control);
	        javafx.application.Platform.exit();
	 }
	
}
