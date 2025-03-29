package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	private Partita partita;
	private Stanza vincente;

	@BeforeEach
	void setUp() throws Exception {
		this.partita=new Partita();
		this.vincente=new Stanza("biblioteca");
	}

	@Test
	void testNuovaPartitaNonFinita() {
		assertFalse(this.partita.isFinita());
	}
	@Test
	void testPartitaVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	//@Test
	void testPartitaPersa() {
		partita.getGiocatore().setCfu(0);
		assertFalse(this.partita.vinta());
	}
	@Test
	void testPartitaNonFinitaEPoiFinita() {
		assertFalse(this.partita.isFinita());
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	

}
