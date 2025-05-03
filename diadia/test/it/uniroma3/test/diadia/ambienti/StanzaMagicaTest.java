package it.uniroma3.test.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	private StanzaMagica stanza;
	private Attrezzo spada;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new StanzaMagica("n12");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
