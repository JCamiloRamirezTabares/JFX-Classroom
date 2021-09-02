package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) throws IOException {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		ClassroomGUI controller = new ClassroomGUI();
		loader.setController(controller);
		Parent root = loader.load();
		Scene sc = new Scene(root);
	
		primaryStage.setScene(sc);
		primaryStage.setTitle("Main Pane");
		primaryStage.show();
		
		controller.startLoginMenu();
	}
	

}
