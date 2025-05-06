package it.uniroma3.test.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia stanza;
	private Attrezzo spada;
	private Attrezzo bastone;
	private Stanza stanza2;
	@BeforeEach
	void setUp() throws Exception {
		this.stanza=new StanzaBuia("n11", "spada");
		this.spada=new Attrezzo("spada",2);
		this.bastone=new Attrezzo("bastone",3);
		this.stanza2=new Stanza("n11");
	}

	@Test
	void testStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	void testStanzaBuia() {
		this.stanza.addAttrezzo(this.bastone);
		assertEquals("qui c'è buio pesto", this.stanza.getDescrizione());

	}
	@Test
	void testStanzaNormale() {
		this.stanza.addAttrezzo(this.spada);
		this.stanza2.addAttrezzo(this.spada);
		assertEquals(this.stanza2.getDescrizione(), this.stanza.getDescrizione());

	}


}
