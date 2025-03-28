package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	private Stanza stanza;
	private Attrezzo spada;

	@BeforeEach
	void setUp() throws Exception {
		this.stanza=new Stanza("n11");
		this.spada = new Attrezzo("spada", 10);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	void testHasAttrezzo() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
		this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	void testRemoveAttrezzo() {
		assertFalse(this.stanza.removeAttrezzo(spada));
		
		this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.removeAttrezzo(spada));
		
	}
	
}
