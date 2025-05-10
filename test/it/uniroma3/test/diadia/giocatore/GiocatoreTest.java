package it.uniroma3.test.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {

	private Giocatore giocatore;
	private Attrezzo osso;
	private Attrezzo arco;
	private Attrezzo spada;

	@BeforeEach
	void setUp() throws Exception {
		this.giocatore = new Giocatore();
		this.osso = new Attrezzo("osso", 1);
		this.arco = new Attrezzo("arco", 2);
		this.spada = new Attrezzo("spada", 10);
	}

	@Test
	void testGiocatoreRemoveAttrezzo_BorsaVuota() {
		assertNull(this.giocatore.giocatoreRemoveAttrezzo(osso));
	}
	
	@Test
	void testGiocatoreRemoveAttrezzo_OggettoPresente() {
		this.giocatore.giocatoreAddAttrezzo(osso);
		assertEquals(this.osso, this.giocatore.giocatoreRemoveAttrezzo(osso));
	}
	
	@Test
	void testGiocatoreRemoveAttrezzo_OggettoNonPresente() {
		this.giocatore.giocatoreAddAttrezzo(arco);
		assertNull(this.giocatore.giocatoreRemoveAttrezzo(osso));
	}
	
	@Test
	void testGiocatoreAddAttrezzo_BorsaVuota() {
		assertTrue(this.giocatore.giocatoreAddAttrezzo(osso));
	}
	
	@Test
	void testGiocatoreAddAttrezzo_BorsaTroppoPesante() {
		this.giocatore.giocatoreAddAttrezzo(spada);
		assertFalse(this.giocatore.giocatoreAddAttrezzo(osso));
	}
	
	@Test
	void testGiocatoreAddAttrezzo_OltreLimiteDiPeso() {
		this.giocatore.giocatoreAddAttrezzo(osso);
		assertFalse(this.giocatore.giocatoreAddAttrezzo(spada));
	}
	
	@Test
	void testGiocatoreGetPesoBorsa_BorsaVuota() {
		assertEquals(0, this.giocatore.getPesoBorsa());
	}
	
	@Test
	void testGiocatoreGetPesoBorsa() {
		this.giocatore.giocatoreAddAttrezzo(osso);
		assertEquals(1, this.giocatore.getPesoBorsa());
	}
	
	@Test
	void testGiocatoreGetPesoBorsa_BorsaPiena() {
		this.giocatore.giocatoreAddAttrezzo(spada);
		assertEquals(10, this.giocatore.getPesoBorsa());
	}

}
