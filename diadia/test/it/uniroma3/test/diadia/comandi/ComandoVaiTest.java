package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;
//import it.uniroma3.diadia.ambienti.StanzaBloccata;
//import it.uniroma3.diadia.attrezzi.Attrezzo;
//import it.uniroma3.diadia.comandi.ComandoGuarda;

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
	
//	PRIMA DI FARE QUESTI TEST "SCOMMENTARE" GLI IMPORT
//	@Test 
//	void testStanzaBloccata(){
//		Stanza stanza = partita.getStanzaCorrente();
//		StanzaBloccata bloccata = new StanzaBloccata("b", "ovest", "chiave");
//		stanza.impostaStanzaAdiacente("sud", bloccata);
//		bloccata.impostaStanzaAdiacente("nord", stanza);
//		Stanza stanza2 = new Stanza("Prova");
//		stanza2.impostaStanzaAdiacente("est", bloccata);
//		bloccata.impostaStanzaAdiacente("ovest", stanza2);
//		
//		ComandoVai vai2 = new ComandoVai("sud");		// comando per spostamento
//		vai2.esegui(partita);		// mi sposto a sud, quindi nella stanza che ha una direzione bloccata
//		ComandoGuarda guarda = new ComandoGuarda();
//		guarda.esegui(partita);
//		vai2.setParametro("ovest");		// provo ad andare nella direzione bloccata
//		vai2.esegui(partita);
//	}
//	
//	@Test 
//	void testStanzaSbloccata(){
//		Stanza stanza = partita.getStanzaCorrente();
//		StanzaBloccata bloccata = new StanzaBloccata("b", "ovest", "chiave");
//		stanza.impostaStanzaAdiacente("sud", bloccata);
//		bloccata.impostaStanzaAdiacente("nord", stanza);
//		Stanza stanza2 = new Stanza("Prova");
//		stanza2.impostaStanzaAdiacente("est", bloccata);
//		bloccata.impostaStanzaAdiacente("ovest", stanza2);
//		
//		ComandoVai vai2 = new ComandoVai("sud");		// comando per spostamento
//		vai2.esegui(partita);		// mi sposto a sud, quindi nella stanza che ha una direzione bloccata
//		ComandoGuarda guarda = new ComandoGuarda();
//		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("chiave", 1));
//		guarda.esegui(partita);
//		vai2.setParametro("ovest");		// provo ad andare nella direzione bloccata
//		vai2.esegui(partita);
//		
//	}

}
