package fabrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConcreteProductA extends Product {
	
	private BufferedReader ein;
	

	public ConcreteProductA() {
		try {
			this.ein = new BufferedReader(new FileReader("Teppich.csv"));
	      	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public String[] leseAusDatei() throws IOException {
		String[] zeile = ein.readLine().split(";");
		return zeile;
	}

	@Override
	public void schliesseDatei() throws IOException {
		ein.close();
		
	}

}
