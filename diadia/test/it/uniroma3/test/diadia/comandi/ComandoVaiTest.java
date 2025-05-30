package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoGuarda;

class ComandoVaiTest {

	private Partita partita;
	private ComandoVai vai;
	private ComandoVai vai2;
	private ComandoGuarda guarda;
	private IOConsole io;
	
	@BeforeEach
	void setUp() throws Exception {
		/**
		 * IMPORTANTE:
		 * prima di chiamare un qualsiasi metodo su un oggetto Comando mi devo
		 * assicurare che sia inizializzato il suo paramentro "IO io" senno
		 * verrà sollevata una NullPointerException.
		 */
		io = new IOConsole();
		partita = new Partita();
		vai = new ComandoVai("nord");
		vai.setIO(io);
		vai2 = new ComandoVai("sud");
		vai2.setIO(io);
		guarda = new ComandoGuarda();
		guarda.setIO(io);
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
	@Test 
	void testStanzaBloccata(){
		Stanza stanza = partita.getStanzaCorrente();		
		StanzaBloccata bloccata = new StanzaBloccata("b", "ovest", "chiave");
		stanza.impostaStanzaAdiacente("sud", bloccata);
		bloccata.impostaStanzaAdiacente("nord", stanza);
		Stanza stanza2 = new Stanza("Prova");
		stanza2.impostaStanzaAdiacente("est", bloccata);
		bloccata.impostaStanzaAdiacente("ovest", stanza2);
		vai2.esegui(partita);		// mi sposto a sud, quindi nella stanza che ha una direzione bloccata
		guarda.esegui(partita);
		vai2.setParametro("ovest");		// provo ad andare nella direzione bloccata
		vai2.esegui(partita);
	}
	
	@Test 
	void testStanzaSbloccata(){
		Stanza stanza = partita.getStanzaCorrente();
		StanzaBloccata bloccata = new StanzaBloccata("b", "ovest", "chiave");
		stanza.impostaStanzaAdiacente("sud", bloccata);
		bloccata.impostaStanzaAdiacente("nord", stanza);
		Stanza stanza2 = new Stanza("Prova");
		stanza2.impostaStanzaAdiacente("est", bloccata);
		bloccata.impostaStanzaAdiacente("ovest", stanza2);
		vai2.esegui(partita);		// mi sposto a sud, quindi nella stanza che ha una direzione bloccata
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("chiave", 1));
		guarda.esegui(partita);
		vai2.setParametro("ovest");		// provo ad andare nella direzione bloccata
		vai2.esegui(partita);
		
	}

}
