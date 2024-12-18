package gui.guiTeppich;


import business.Teppich;
import business.TeppichModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;


public class TeppichView implements Observer {

	private TeppichControl teppichControl;
	private TeppichModel teppichModel;
	

	
	

    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblArtikelnummer 			= new Label("Artikelnummer:");
    private Label lblKategorie   			= new Label("Kategorie:");
    private Label lblBereit  	 			= new Label("Bereit:");
    private Label lblLaenge   				= new Label("Laenge:");
    private Label lblFarben  				= new Label("Farben:");
    private TextField txtArtikelnummer	 	= new TextField();
    private TextField txtKategorie			= new TextField();
    private TextField txtBereit				= new TextField();
    private TextField txtLaenge 			= new TextField();
    private TextField txtFarben				= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");    
    //-------Ende Attribute der grafischen Oberflaeche-------
	
	public TeppichView(Stage primaryStage,TeppichControl teppichControl, TeppichModel teppichModel) {
		this.teppichControl = teppichControl;
		this.teppichModel = teppichModel;
		teppichModel.addObserver(this);
		
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Teppich");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
	}
	
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblArtikelnummer.setLayoutX(20);
    	lblArtikelnummer.setLayoutY(90);
    	lblKategorie.setLayoutX(20);
    	lblKategorie.setLayoutY(130);
    	lblBereit.setLayoutX(20);
    	lblBereit.setLayoutY(170);
    	lblLaenge.setLayoutX(20);
    	lblLaenge.setLayoutY(210);
    	lblFarben.setLayoutX(20);
    	lblFarben.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblArtikelnummer, lblKategorie, lblBereit,
       		lblLaenge, lblFarben);
    
    	// Textfelder
       	txtArtikelnummer.setLayoutX(170);
       	txtArtikelnummer.setLayoutY(90);
       	txtArtikelnummer.setPrefWidth(200);
       	txtKategorie.setLayoutX(170);
       	txtKategorie.setLayoutY(130);
       	txtKategorie.setPrefWidth(200);
       	txtBereit.setLayoutX(170);
       	txtBereit.setLayoutY(170);
       	txtBereit.setPrefWidth(200);
       	txtLaenge.setLayoutX(170);
       	txtLaenge.setLayoutY(210);
       	txtLaenge.setPrefWidth(200);
       	txtFarben.setLayoutX(170);
       	txtFarben.setLayoutY(250);
       	txtFarben.setPrefWidth(200);
      	pane.getChildren().addAll( 
      			txtArtikelnummer, txtKategorie, txtBereit,
      			txtLaenge, txtFarben);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	   btnEingabe.setOnAction(e->nehmeTeppichAuf());

	    btnAnzeige.setOnAction(e->update());

	    mnItmCsvImport.setOnAction(e->leseAusDatei("csv"));

	    mnItmTxtImport.setOnAction(e->leseAusDatei("txt"));
	    
	    mnItmCsvExport.setOnAction(e->schreibeTeppichInCsvDatei());

    }
    
    public void nehmeTeppichAuf(){
    	try{
    		//new
    		teppichModel.addTeppich(new Teppich(
        			Integer.parseInt(txtArtikelnummer.getText()),
       	            txtKategorie.getText(),
       	            Float.parseFloat(txtBereit.getText()),
       	            Float.parseFloat(txtLaenge.getText()),
       	            txtFarben.getText().split(";")));
    		teppichModel.notifyObserver();
    			
    		
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
    
    //new
 
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
		  
    private void leseAusDatei(String typ){
    	teppichControl.leseAusDatei(typ);
    	teppichModel.notifyObserver();
	}
		
	private void schreibeTeppichInCsvDatei() {
		teppichControl.schreibeTeppichInCsvDatei();
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
