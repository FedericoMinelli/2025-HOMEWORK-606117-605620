package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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
		this.spada = new Attrezzo("spada", 12);
	}

	@Test
	void testgiocatoreRemoveAttrezzo_BorsaVuota() {
		assertNull(this.giocatore.giocatoreRemoveAttrezzo(osso));
	}
	@Test
	void testgiocatoreRemoveAttrezzo_OggettoPresente() {
		this.giocatore.giocatoreAddAttrezzo(osso);
		assertEquals(this.osso, this.giocatore.giocatoreRemoveAttrezzo(osso));
	}
	@Test
	void testgiocatoreRemoveAttrezzo_OggettoNonPresente() {
		this.giocatore.giocatoreAddAttrezzo(arco);
		assertNull(this.giocatore.giocatoreRemoveAttrezzo(osso));
	}
	@Test
	void testgiocatoreGetPesoBorsa_BorsaVuota() {
		assertEquals(0, this.giocatore.getPesoBorsa());
	}
	@Test
	void testgiocatoreGetPesoBorsa() {
		this.giocatore.giocatoreAddAttrezzo(arco);
		assertEquals(2, this.giocatore.getPesoBorsa());
	}
	@Test
	void testgiocatoreGetPesoBorsa_BorsaPiena() {
		this.giocatore.giocatoreAddAttrezzo(spada);
		assertEquals(0, this.giocatore.getPesoBorsa());
	}

}
