package dad.javafx.swapi.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private Controller controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		this.controller = new Controller();
		
		primaryStage.setTitle("StarWars");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
