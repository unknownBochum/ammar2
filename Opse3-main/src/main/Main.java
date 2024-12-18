package main;


import gui.guiBodenbelaege.BodenbelaegeControl;
import gui.guiTeppich.TeppichControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
	
		TeppichControl teppichControl = new TeppichControl(primaryStage);
		Stage stage = new Stage();
		BodenbelaegeControl bodenbelaegeControl = new BodenbelaegeControl(stage);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
