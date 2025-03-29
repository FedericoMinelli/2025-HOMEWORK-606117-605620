package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo osso;

	@BeforeEach
	void setUp() throws Exception {
		this.borsa= new Borsa(20);
		this.osso= new Attrezzo("osso", 2);
	}

	@Test
	void testBorsaHasAttrezzo() {
		this.borsa.addAttrezzo(this.osso);
		assertTrue(this.borsa.hasAttrezzo("osso"));
	}
	@Test
	void testBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	@Test
	// non uso assertTrue/False perchè RemoveAttrezzo non restituisce un booleano
	void testBorsaRemoveAttrezzo() {
		// se la borsa non contiene l'attrezzo restituisce null;
		assertNull(this.borsa.removeAttrezzo("osso"));
		// aggiunge l'attrezzo e verifica se la rimozione avviene correttamente
		this.borsa.addAttrezzo(this.osso);
		assertEquals(this.osso, this.borsa.removeAttrezzo("osso"));

	}

}
