package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {

	private Partita partita;
	private ComandoPosa posa;
	
	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita();
		posa = new ComandoPosa("osso");
	}

	@Test
	void testEsegui_AttrezzoNonSpecificatoSetParametroNull() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		posa.setParametro(null);
		posa.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente());
		assertEquals(borsa, partita.getGiocatore().getBorsa());
	}
	
	
	@Test
	void testEsegui_AttrezzoNull() {
		posa.esegui(partita);
	}

}
