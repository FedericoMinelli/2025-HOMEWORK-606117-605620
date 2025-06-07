package it.uniroma3.test.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza;
	private Attrezzo spada;
	private Attrezzo osso;
	private Direzioni nord = Direzioni.Nord;

	@BeforeEach
	void setUp() throws Exception {
		this.stanza=new Stanza("n11");
		this.spada = new Attrezzo("spada", 10);
		this.osso = new Attrezzo("osso", 5);
	}

	@Test
	void testHasAttrezzo_StanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	void testHasAttrezzo_StanzaAttrezzoGiusto() {
		this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.hasAttrezzo("spada"));
	}
	
	@Test
    void testHasAttrezzo_StanzaAttrezzoSbagliato() {
		this.stanza.addAttrezzo(this.osso);
        assertFalse(this.stanza.hasAttrezzo("spada"));
    }
	
	// provo a rimuovere da una stanza che non ha quel attrezzo ed è vuota
	@Test
	void testRemoveAttrezzo_StanzaVuota() {
		assertFalse(this.stanza.removeAttrezzo(spada));
	}
	
	// provo a rimuovere un attrezzo presente nella stanza
	@Test
	void testRemoveAttrezzo_StanzaOggettoPresente() {
		this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.removeAttrezzo(spada));
	}
	
	// provo a rimuovere da una stanza che non ha quell'attrezzo ma ne ha un altro
	@Test
	void testRemoveAttrezzo_StanzaOggettoNonPresente() {
		this.stanza.addAttrezzo(this.osso);
		assertFalse(this.stanza.removeAttrezzo(spada));
	}
	
	// provo ad aggiungere un attrezzo e vedo se va a buon fine
	@Test
	void testAddAttrezzo_StanzaVuota() {
		assertTrue(this.stanza.addAttrezzo(this.osso));
	}
	
	@Test
	void testImpostaStanzaAdicente_Esistente() {
		Stanza aula = new Stanza("aula");
		this.stanza.impostaStanzaAdiacente(nord, aula);
		assertSame(stanza.getStanzaAdiacente(nord), aula);
	}
	
	@Test
	void testImpostaStanzaAdicente_ChiaveNullVedoDirezioneNull() {
		Stanza aula = new Stanza("aula");
		this.stanza.impostaStanzaAdiacente(null, aula);
		assertSame(stanza.getStanzaAdiacente(null), aula);
	}
	
	@Test
	void testImpostaStanzaAdicente_ChiaveNullVedoAltraDirezione() {
		this.stanza.impostaStanzaAdiacente(null, new Stanza("aula"));
		assertNull(stanza.getStanzaAdiacente(nord));
	}
	
	
	
	
}
