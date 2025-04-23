package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {

	private Partita partita;
	private ComandoVai vai;
	
	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita();
		vai = new ComandoVai("nord");
	}

	// non essendoci il parametro il giocatore non si puo muovere e rimane nella stanza corrente
	@Test
	void testEsegui_DirezioneNonSpecificataSetParametroNull() {
		Stanza stanza = partita.getStanzaCorrente();
		vai.setParametro(null);
		vai.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente());
	}
	
	// impostando la stanza nord a null il giocatore non si potrà spostare perche non viene trovata una stanza
	@Test
	void testEsegui_DirezioneInesistente() {
		Stanza stanza = partita.getStanzaCorrente();
		partita.getStanzaCorrente().impostaStanzaAdiacente("nord", null);
		vai.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente()); 
	}
	
	@Test
	void testEsegui_DirezioneEsistente() {
		Stanza stanza = partita.getStanzaCorrente(); 
		vai.esegui(partita);
		assertEquals(stanza.getStanzaAdiacente("nord"), partita.getStanzaCorrente());
	}

}
