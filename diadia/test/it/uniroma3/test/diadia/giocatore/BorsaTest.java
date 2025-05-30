package it.uniroma3.test.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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
		this.martelletto=new Attrezzo("martelletto",2);
		this.borsa2= new Borsa(50);
	}

	
	@Test
	void testBorsaHasAttrezzo_BorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	void testBorsaHasAttrezzo_AttrezzoPresente() {
		this.borsa.addAttrezzo("osso",this.osso);
		assertTrue(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	void testBorsaHasAttrezzo_AttrezzoNonPresente() {
		this.borsa.addAttrezzo("spada",this.spada);
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	void testBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	void testBorsaNonVuota() {
		this.borsa.addAttrezzo("osso",osso);
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
		this.borsa.addAttrezzo("osso",this.osso);
		assertEquals(this.osso, this.borsa.removeAttrezzo("osso"));
	}
	
	@Test
	// prova ad eliminare un attrezzo non presente nella borsa, return null
	void testBorsaRemoveAttrezzo_AttrezzoNonPresente() {
		this.borsa.addAttrezzo("spada",this.spada);
		assertNull(this.borsa.removeAttrezzo("osso"));
	}
	
	@Test
	void testBorsaAddAttrezzo_BorsaVuota() {
		this.borsa.addAttrezzo("osso",osso);
		assertTrue(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	void testAddAttrezzo_BorsaTroppoPesante() {
		this.borsa.addAttrezzo("osso",this.osso);
		assertNull(this.borsa.addAttrezzo("spada",this.spada));		// provo ad aggiungerne un altro
	}
	
	@Test
	void testAddAttrezzo_OltreLimiteDiPeso() {
		this.borsa.addAttrezzo("osso",this.osso);
		assertNull(this.borsa.addAttrezzo("spada",this.spada));		// provo ad aggiungerne un altro
	}
	
	@Test
	void testBorsaGetAttrezzo_BorsaVuota() {
		assertNull(this.borsa.getAttrezzo("osso"));
	}
	
	@Test
	void testBorsaGetAttrezzo_OggettoPresente() {
		this.borsa.addAttrezzo("osso",osso);
		assertEquals(this.osso, this.borsa.getAttrezzo("osso"));
	}
	
	@Test
	void testBorsaGetAttrezzo_OggettoNonPresente() {
		this.borsa.addAttrezzo("spada",spada);
		assertNull(this.borsa.getAttrezzo("osso"));
	}
	@Test
	void testBorsaVuota2() {
		assertEquals(0,  this.vuota.getPeso());
	}
	@Test
	void testBorsa2attrezzi() {
		this.vuota.addAttrezzo("piuma",piuma);
		this.vuota.addAttrezzo("martello",martello);
		assertEquals(11, this.vuota.getPeso());
	}
	@Test
	void testBorsaGetContenutoNome() {
		this.borsa2.addAttrezzo("martello",this.martello);
		assertEquals(1, borsa2.getNumeroAttrezzi());
		assertEquals(1, borsa2.getContenutoOrdinatoPerNome().size());
		this.borsa2.addAttrezzo("martelletto",this.martelletto);
		assertEquals(2, borsa2.getNumeroAttrezzi());
		assertEquals(2, borsa2.getContenutoOrdinatoPerNome().size());
	}
	
	@Test
	void testBorsa_GetSortedSetOrdinatoPerPeso() {
		borsa2.addAttrezzo(spada);
		borsa2.addAttrezzo(martello);
		
		SortedSet<Attrezzo> ordinati = borsa2.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = ordinati.iterator();
		
		assertEquals(martello, it.next());
		assertEquals(spada, it.next());
		
		
		
	}
	@Test
	void getContenutoOrdinatoPerPeso(){
		this.borsa2.addAttrezzo(osso);
		assertEquals(1, this.borsa2.getNumeroAttrezzi());
		assertEquals(1, this.borsa2.getContenutoOrdinatoPerPeso().size());
		this.borsa2.addAttrezzo(piuma);
		assertEquals(2, this.borsa2.getNumeroAttrezzi());
		assertEquals(2, this.borsa2.getContenutoOrdinatoPerPeso().size());
	}
	@Test
	void getContenutoRaggruppatoPerPeso() {
		borsa2.addAttrezzo(spada);
		borsa2.addAttrezzo(martello);
		this.borsa2.addAttrezzo(osso);
		this.borsa2.addAttrezzo(piuma);
		this.borsa2.addAttrezzo("martelletto",this.martelletto);
		assertEquals(5, this.borsa2.getNumeroAttrezzi());
		Map<Integer,Set<Attrezzo>> raggruppati = this.borsa2.getContenutoRaggruppatoPerPeso();
		assertTrue(raggruppati.get(10).contains(spada));
		assertTrue(raggruppati.get(10).contains(martello));
		assertTrue(raggruppati.get(2).contains(osso));
		assertTrue(raggruppati.get(2).contains(martelletto));
		assertTrue(raggruppati.get(1).contains(piuma));
		assertFalse(raggruppati.get(2).contains(spada));
		
		assertEquals(2, raggruppati.get(2).size());
		assertEquals(2, raggruppati.get(10).size());
		assertEquals(1, raggruppati.get(1).size());
	}
	
}
