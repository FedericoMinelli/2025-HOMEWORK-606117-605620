package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoGuarda;

class ComandoGuardaTest {

	private Partita partita;
	private ComandoGuarda guarda;
	
	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita();
		guarda = new ComandoGuarda();
	}

	@Test
	void testFunzionamento() {
		guarda.esegui(partita);
	}

}
