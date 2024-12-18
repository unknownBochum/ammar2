package gui.guiBodenbelaege;

import business.Teppich;
import business.TeppichModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;


public class BodenbelaegeView implements Observer {

	private BodenbelaegeControl teppichControl;
	private TeppichModel teppichModel;
	
	
	

    private Pane pane     					= new  Pane();
    private Label lblAnzeige   	 	    	= new Label("Anzeige Teppich");
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnAnzeige 		 		= new Button("Anzeige");
    
  
    //-------Ende Attribute der grafischen Oberflaeche-------
	
	public BodenbelaegeView(Stage primaryStage,BodenbelaegeControl teppichControl, TeppichModel teppichModel) {
		this.teppichControl = teppichControl;
		this.teppichModel = teppichModel;
		teppichModel.addObserver(this);
		
    	Scene scene = new Scene(this.pane, 560, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Anzeige von Teppich Einrichtung");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
	}
	
    private void initKomponenten(){
       	// Labels

    	Font font = new Font("Arial", 24); 

    	lblAnzeige.setLayoutX(310);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
    	
       	pane.getChildren().addAll(lblAnzeige);
    

     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(310);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(220);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	

        btnAnzeige.setLayoutX(310);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnAnzeige); 
        

   }
   
   private void initListener() {

	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	    		update();
	        } 
   	    });

    }
    

   
	@Override
	public void update() {
		if(teppichModel.getTeppichs().size() > 0){ 
	         StringBuffer text = new StringBuffer(); 
	      // Ergaenzen: for each – Schleife ueber ArrayList 
	         { 
	        	 for (Teppich teppich : teppichModel.getTeppichs()) {
	        		 text.append(teppich.gibTeppichZurueck(' ') + "\n"); 
				}
	          
	         } 
	         this.txtAnzeige.setText(text.toString()); 
	        } 
    	else{
    		zeigeInformationsfensterAn("Bisher wurde kein Teppich aufgenommen!");
    	}
    }    
		

    void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }


	
	
}
