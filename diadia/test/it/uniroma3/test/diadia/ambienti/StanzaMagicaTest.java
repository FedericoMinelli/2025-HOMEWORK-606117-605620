package it.uniroma3.test.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	private StanzaMagica stanza;
	private Attrezzo spada;
	private Attrezzo bastone;
	private Attrezzo ascia;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new StanzaMagica("n12",2);
		this.spada=new Attrezzo("spada",3);
		this.bastone=new Attrezzo("bastone", 1);
		this.ascia=new Attrezzo("ascia",2);
	}

	@Test
	void testSTanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	void testSTanzaNormale() {
		this.stanza.addAttrezzo(ascia);
		Attrezzo attrezzo=stanza.getAttrezzo("ascia");
		assertTrue(this.stanza.hasAttrezzo("ascia"));
		assertSame(2, attrezzo.getPeso());
	}
	@Test
	void testSTanzaMAgica() {
		this.stanza.addAttrezzo(ascia);
		this.stanza.addAttrezzo(bastone);
		this.stanza.addAttrezzo(spada);
		Attrezzo attrezzoMod=this.stanza.getAttrezzo("adaps");
		assertTrue(this.stanza.hasAttrezzo("adaps"));
		assertSame(6, attrezzoMod.getPeso());
		
	}
   @Test
	void testModificaAttrezzo() {
	   Attrezzo attrezzomodificato=this.stanza.modificaAttrezzo(ascia);
		this.stanza.addAttrezzo(attrezzomodificato);
		
		assertTrue(this.stanza.hasAttrezzo("aicsa"));
		assertSame(4, attrezzomodificato.getPeso());
	}

}
