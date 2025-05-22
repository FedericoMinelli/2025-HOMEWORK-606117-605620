package it.uniroma3.test.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo osso;
	private Attrezzo spada;
	private Borsa vuota;
	private Attrezzo martello;
	private Attrezzo martelletto;
	private Attrezzo piuma;
	private Borsa borsa2;

	@BeforeEach
	void setUp() throws Exception {
		this.borsa= new Borsa(10);
		this.osso= new Attrezzo("osso", 2);
		this.spada= new Attrezzo("spada", 10);
		this.vuota= new Borsa(15);
		this.piuma=new Attrezzo("piuma", 1);
		this.martello=new Attrezzo("martello",10);
		this.martelletto=new Attrezzo("martello",2);
		this.borsa2= new Borsa(50);
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
	void testBorsaAddAttrezzo_BorsaVuota() {
		assertTrue(this.borsa.addAttrezzo(osso));
	}
	
	@Test
	void testAddAttrezzo_BorsaTroppoPesante() {
		this.borsa.addAttrezzo(this.osso);
		assertFalse(this.borsa.addAttrezzo(this.spada));		// provo ad aggiungerne un altro
	}
	
	@Test
	void testAddAttrezzo_OltreLimiteDiPeso() {
		this.borsa.addAttrezzo(this.osso);
		assertFalse(this.borsa.addAttrezzo(this.spada));		// provo ad aggiungerne un altro
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
	@Test
	void testBorsaVuota2() {
		assertEquals(0,  this.vuota.getPeso());
	}
	@Test
	void testBorsa2attrezzi() {
		this.vuota.addAttrezzo(piuma);
		this.vuota.addAttrezzo(martello);
		assertEquals(11, this.vuota.getPeso());
	}
	@Test
	void testBorsaGetConetenutoNome() {
		this.borsa2.addAttrezzo(this.martello);
		assertEquals(1, borsa2.getNumeroAttrezzi());
		assertEquals(1, borsa2.getContenutoOrdinatoNome().size());
		this.borsa2.addAttrezzo(this.martelletto);
		assertEquals(2, borsa2.getNumeroAttrezzi());
		assertEquals(2, borsa2.getContenutoOrdinatoNome().size());
	}

}
