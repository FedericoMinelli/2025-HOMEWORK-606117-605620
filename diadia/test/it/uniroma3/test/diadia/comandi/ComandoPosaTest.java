package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {

	private Partita partita;
	private ComandoPosa posa;
	
	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita();
		posa = new ComandoPosa("prova");
		posa.setIO(new IOConsole());
	}

	@Test
	void testEsegui_AttrezzoNonSpecificatoSetParametroNull() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		posa.setParametro(null);
		posa.esegui(partita);
		assertNull(borsa.getAttrezzo(posa.getParametro()));
		assertNull(stanza.getAttrezzo(posa.getParametro()));
	}
	
	
	@Test
	void testEsegui_AttrezzoNullBorsaConOggetti() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		Attrezzo a = new Attrezzo("pera", 1);
		
		borsa.addAttrezzo(a);
		posa.esegui(partita);
		
		assertNull(borsa.getAttrezzo(posa.getParametro()));
		assertNull(stanza.getAttrezzo(posa.getParametro()));
	}
	
	@Test
	void testEsegui_AttrezzoNullBorsaSenzaOggetti() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		posa.esegui(partita);
		
		assertNull(borsa.getAttrezzo(posa.getParametro()));
		assertNull(stanza.getAttrezzo(posa.getParametro()));
	}	
	
	@Test
	void testEsegui_Funzionante() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		Attrezzo prova = new Attrezzo("prova", 1);
		
		borsa.addAttrezzo(prova);
		posa.esegui(partita);
		
		assertNull(borsa.getAttrezzo(posa.getParametro()));
		assertNotNull(stanza.getAttrezzo(posa.getParametro()));
	}
	
//	@Test
//	void testEsegui_StanzaPiena() {
//		Borsa borsa = partita.getGiocatore().getBorsa();
//		Stanza stanza = partita.getStanzaCorrente();
//		
//		while(stanza.getNumeroAttrezzi() < 10) {
//			Attrezzo a = new Attrezzo("a", 1);
//			stanza.addAttrezzo(a);
//		}
//		Attrezzo prova = new Attrezzo("prova", 1);
//		borsa.addAttrezzo(prova);
//		posa.esegui(partita);
//		
//		assertNotNull(borsa.getAttrezzo(posa.getParametro()));
//		assertNull(stanza.getAttrezzo(posa.getParametro()));
//	}

}
