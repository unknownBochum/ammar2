package gui.guiTeppich;
import java.io.IOException;
import business.TeppichModel;
import javafx.stage.Stage;


public class TeppichControl {
	
	private TeppichModel teppichModel;
	private TeppichView teppishView;
	
	public TeppichControl(Stage primaryStage) {
		
		this.teppichModel = TeppichModel.getInstance();
		this.teppishView = new TeppichView(primaryStage, this, teppichModel);
	}
	
	public void schreibeTeppichInCsvDatei() {
		try {
			teppichModel.schreibeTeppichInCsvDatei();
   			teppishView.zeigeInformationsfensterAn(
	   			"Die Teppich wurden gespeichert!");
		}	
		catch(IOException exc){
			teppishView.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
		}
		catch(Exception exc){
			teppishView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
		}
	}
	
    public void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			teppichModel.leseTeppichAusCsvDatei();
      	  		//teppishView.zeigeInformationsfensterAn("Die Teppich wurden gelesen!");
      		}
       		else{
      			teppichModel.leseTeppichAusTxtDatei();
      	  		//teppishView.zeigeInformationsfensterAn("Die Teppich wurden gelesen!");
	   		}
		}
		catch(IOException exc){
			teppishView.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
		}
		catch(Exception exc){
			teppishView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
		}
	}

}
