package business;

public class Teppich {
	

    private int artikelnummer;
    private String kategorie;
    private float bereit;
    private float laenge;
    private String[] farben;


    
	public Teppich(int artikelnummer, String kategorie, float bereit, float laenge, String[] farben) {
		this.artikelnummer = artikelnummer;
		this.kategorie = kategorie;
		this.bereit = bereit;
		this.laenge = laenge;
		this.farben = farben;
	}


	
	public int getArtikelnummer() {
		return artikelnummer;
	}



	public void setArtikelnummer(int artikelnummer) {
		this.artikelnummer = artikelnummer;
	}



	public String getKategorie() {
		return kategorie;
	}



	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}



	public float getBereit() {
		return bereit;
	}



	public void setBereit(float bereit) {
		this.bereit = bereit;
	}



	public float getLaenge() {
		return laenge;
	}



	public void setLaenge(float laenge) {
		this.laenge = laenge;
	}



	public String[] getFarben() {
		return farben;
	}



	public void setFarben(String[] farben) {
		this.farben = farben;
	}



	public String getFarbenAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getFarben().length - 1; i++) {
			ergebnis = ergebnis + this.getFarben()[i] + trenner; 
		}
		return ergebnis	+ this.getFarben()[i];
	}
	
	public String gibTeppichZurueck(char trenner){
  		return ""+this.getArtikelnummer() + trenner 
  			+ this.getKategorie() + trenner
  		    + this.getBereit() + trenner
  		    + this.getLaenge() + trenner + "\n"
  		    + this.getFarbenAlsString(trenner) + "\n";
  	}
}

