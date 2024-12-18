package business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;

import gui.guiTeppich.TeppichControl;

class TeppichTest {

	@Test
	void test() {
		String [] farben={"Rot"};
		Teppich teppich = new Teppich(1212, "sdasd", 23.3f, 60.3f, farben);
		BooleanSupplier farbe = () -> teppich.getFarben().length <2;
		assertTrue(farbe," 2 Farben");
	}

}
