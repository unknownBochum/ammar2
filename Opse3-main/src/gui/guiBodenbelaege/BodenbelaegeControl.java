package gui.guiBodenbelaege;

import business.TeppichModel;
import javafx.stage.Stage;


public class BodenbelaegeControl {
	
	private TeppichModel teppichModel;
	private BodenbelaegeView teppishView;
	
	public BodenbelaegeControl(Stage primaryStage) {
		
		this.teppichModel = TeppichModel.getInstance();
		this.teppishView = new BodenbelaegeView(primaryStage, this, teppichModel);
	}
	


}
