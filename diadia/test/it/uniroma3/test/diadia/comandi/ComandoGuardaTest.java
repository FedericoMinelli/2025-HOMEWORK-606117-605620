package it.uniroma3.test.diadia.comandi;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoGuarda;

class ComandoGuardaTest {

	private Partita partita;
	private ComandoGuarda guarda;
	
	@BeforeEach
	void setUp() throws Exception {
		Scanner scanner = new Scanner(System.in);
		partita = new Partita();
		guarda = new ComandoGuarda();
		guarda.setIO(new IOConsole(scanner));
	}

	@Test
	void testFunzionamento() {
		guarda.esegui(partita);
	}

}
