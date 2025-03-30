package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo osso;
	private Attrezzo spada;

	@BeforeEach
	void setUp() throws Exception {
		this.borsa= new Borsa(20);
		this.osso= new Attrezzo("osso", 2);
		this.spada= new Attrezzo("spada", 10);
	}

	
	@Test
	void testBorsaHasAttrezzo_BorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	void testBorsaHasAttrezzo_AttrezzoPresente() {
		this.borsa.addAttrezzo(this.osso);
		assertTrue(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	void testBorsaHasAttrezzo_AttrezzoNonPresente() {
		this.borsa.addAttrezzo(this.spada);
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	void testBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	void testBorsaNonVuota() {
		this.borsa.addAttrezzo(osso);
		assertFalse(this.borsa.isEmpty());
	}

	@Test
	// se la borsa non contiene l'attrezzo restituisce null
	void testBorsaRemoveAttrezzo_BorsaVuota() {
		assertNull(this.borsa.removeAttrezzo("osso"));
	}
	
	@Test
	// aggiunge l'attrezzo e verifica se la rimozione avviene correttamente
	void testBorsaRemoveAttrezzo_AttrezzoPresente() {
		this.borsa.addAttrezzo(this.osso);
		assertEquals(this.osso, this.borsa.removeAttrezzo("osso"));
	}
	
	@Test
	// prova ad eliminare un attrezzo non presente nella borsa, return null
	void testBorsaRemoveAttrezzo_AttrezzoNonPresente() {
		this.borsa.addAttrezzo(this.spada);
		assertNull(this.borsa.removeAttrezzo("osso"));
	}
	

	@Test
	void testBorsaGetAttrezzo_BorsaVuota() {
		assertNull(this.borsa.getAttrezzo("osso"));
	}
	
	@Test
	void testBorsaGetAttrezzo_OggettoPresente() {
		this.borsa.addAttrezzo(osso);
		assertEquals(this.osso, this.borsa.getAttrezzo("osso"));
	}
	
	@Test
	void testBorsaGetAttrezzo_OggettoNonPresente() {
		this.borsa.addAttrezzo(spada);
		assertNull(this.borsa.getAttrezzo("osso"));
	}

}
