package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import fabrik.ConcreteCreatorA;
import fabrik.ConcreteCreatorB;
import fabrik.Creator;
import fabrik.Product;
import ownUtil.Observable;
import ownUtil.Observer;

public class TeppichModel implements Observable {
	
	private Vector<Observer> observers = new Vector<Observer>();
	//new 1
	private ArrayList<Teppich> teppichs = new ArrayList<Teppich>();
	
	private static TeppichModel ob;
	
	private TeppichModel() {}
	
	public static TeppichModel getInstance() {
		if (ob == null) {
			ob = new TeppichModel();
		}return ob;
	}
	
	//new 2
	public ArrayList<Teppich> getTeppichs() {
		return teppichs;
	}
	//new 3
	public void addTeppich(Teppich teppich) {
		this.teppichs.add(teppich);
	}

	//	private Teppich teppich;
	
	
	

//	public Teppich getTeppich() {
//		return teppich;
//	}
//
//
//	public void setTeppich(Teppich teppich) {
//		this.teppich = teppich;
//	}

	//new 4
	public void schreibeTeppichInCsvDatei() throws IOException  {
		BufferedWriter aus = new BufferedWriter(new FileWriter("TeppichAusgabe.csv", true));
		//new
		for (Teppich teppich : teppichs) {
			aus.write("Daten des Teppish");
			aus.newLine();
			aus.write("Artikelnummer des Teppish:	"+teppich.getArtikelnummer());
			aus.newLine();
			aus.write("Kategorie des Teppish:		"+teppich.getKategorie());
			aus.newLine();
			aus.write("Bereit des Teppish:			"+teppich.getBereit());
			aus.newLine();
			aus.write("Laenge des Teppish:			"+teppich.getLaenge());
			aus.newLine();
			aus.write("Farben des Teppish:			"+teppich.getFarbenAlsString('_')+"\n");
			aus.newLine();
		}
		
		aus.close();
	}
	
    public void leseTeppichAusCsvDatei() throws IOException{

      	
    	Creator creator = new ConcreteCreatorA();
    	Product product = creator.factoryMethod();
    	String [] zeile = product.leseAusDatei(); 
    	
    	//new5
      	addTeppich(new Teppich(Integer.parseInt(zeile[0]), 
      		zeile[1], 
      		Float.parseFloat(zeile[2]),
      		Float.parseFloat(zeile[3]),
      		zeile[4].split("_")));
      		product.schliesseDatei();
      	
      
    }
    public void leseTeppichAusTxtDatei() throws IOException{

    	Creator creator = new ConcreteCreatorB();
    	Product product = creator.factoryMethod();
    	String [] zeile = product.leseAusDatei(); 
    	
    	//new6
      	addTeppich( new Teppich(Integer.parseInt(zeile[0]), 
      		zeile[1], 
      		Float.parseFloat(zeile[2]),
      		Float.parseFloat(zeile[3]),
      		zeile[4].split("_")));
      		product.schliesseDatei();
      	
      
    }

	@Override
	public void addObserver(Observer obs) {
		observers.add(obs);
		
	}

	@Override
	public void removeObserver(Observer obs) {
		observers.remove(obs);
		
	}

	@Override
	public void notifyObserver() {
		for (Observer obs : observers) {
			obs.update();
		}
		
	}
    
 }
