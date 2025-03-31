package it.uniroma3.test.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class PartitaTest {
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		this.partita=new Partita();
	}

	@Test
	void testPartitaNonFinita() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	void testPartitaFinita_ZeroCFU() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testPartitaFinita_SettataAFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testPartitaVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	void testPartitaNonAncoraVinta() {
		assertFalse(this.partita.vinta());
	}
}
