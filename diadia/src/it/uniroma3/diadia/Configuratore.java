package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuratore {
	private static final String CFU_INIZIALI = "cfu";
	private static final String PESO_MAX = "peso_max";
	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static Properties prop = null;
	
	public static int getCFU() {
		if(prop==null) {
			carica();
		}
		return Integer.parseInt(prop.getProperty(CFU_INIZIALI));
	}
	public static int getPesoMax() {
		if(prop==null) {
			carica();
		}
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}
	private static void carica() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(DIADIA_PROPERTIES);
			prop.load(input);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
