package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata iniziale;
	private Stanza sgabuzzino;
	private Stanza sbloccata;
	private Attrezzo chiave;
	
	@BeforeEach
	void setUp() throws Exception {
		iniziale = new StanzaBloccata("Iniziale", "nord", "chiave");
		sgabuzzino = new Stanza("Sgabuzzino");
		sbloccata = new Stanza("Sbloccata");
		chiave = new Attrezzo("chiave", 1);
		
		this.iniziale.impostaStanzaAdiacente("nord", sgabuzzino);
		this.sgabuzzino.impostaStanzaAdiacente("sud", iniziale);
		
		this.iniziale.impostaStanzaAdiacente("sud", sbloccata);
		this.sbloccata.impostaStanzaAdiacente("nord", iniziale);
		
	}

	@Test
	void testGetStanzaAdiacente_DirezioneBloccata() {
		assertSame(iniziale, iniziale.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacente_DirezioneSbloccata() {
		iniziale.addAttrezzo(chiave);
		assertSame(sgabuzzino, iniziale.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacente_StanzaNonBloccataDallInizioSenzaChiave() {
		assertSame(sbloccata, iniziale.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testGetStanzaAdiacente_StanzaNonBloccataDallInizioConChiave() {
		iniziale.addAttrezzo(chiave);
		assertSame(sbloccata, iniziale.getStanzaAdiacente("sud"));
	}

}
