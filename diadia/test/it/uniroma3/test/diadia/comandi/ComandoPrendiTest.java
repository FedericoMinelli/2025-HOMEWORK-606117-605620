package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPrendiTest {

	private Partita partita;
	private ComandoPrendi prendi;
	
	@BeforeEach
	void setUp() throws Exception {
		partita = new Partita();
		prendi = new ComandoPrendi("prova");
	}

//	@Test
//	void testEsegui_AttrezzoNonSpecificatoSetParametroNull() {
//		Borsa borsa = partita.getGiocatore().getBorsa();
//		Stanza stanza = partita.getStanzaCorrente();
//		prendi.setParametro(null);
//		prendi.esegui(partita);
//		assertNull(borsa.getAttrezzo(prendi.getParametro()));
//		assertNull(stanza.getAttrezzo(prendi.getParametro()));
//	}
//	
//	@Test
//	void testEsegui_AttrezzoNullStanzaConOggetti() {
//		Borsa borsa = partita.getGiocatore().getBorsa();
//		Stanza stanza = partita.getStanzaCorrente();
//		prendi.esegui(partita);
//		Attrezzo a = new Attrezzo("pera", 1);
//		
//		stanza.addAttrezzo(a);		// cosi ho la certezza che ci sia almeno un attrezzo
//		
//		assertNull(borsa.getAttrezzo(prendi.getParametro()));
//		assertNull(stanza.getAttrezzo(prendi.getParametro()));
//	}
//	
	@Test
	void testEsegui_AttrezzoNullStanzaSenzaOggetti() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		//prendi.esegui(partita);
		
		Attrezzo[] array = stanza.getAttrezzi();
		for(int i=0; stanza.getNumeroAttrezzi() != 0; i++) {
			
			stanza.removeAttrezzo(array[i]);
		}
		System.out.println(stanza.getNumeroAttrezzi());
		
		assertNull(borsa.getAttrezzo(prendi.getParametro()));
		assertNull(stanza.getAttrezzo(prendi.getParametro()));
	}
//	
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
//		prendi.esegui(partita);
//		
//		assertNotNull(borsa.getAttrezzo(prendi.getParametro()));
//		assertNull(stanza.getAttrezzo(prendi.getParametro()));
//	}
//	
//	@Test
//	void testEsegui_Funzionante() {
//		Borsa borsa = partita.getGiocatore().getBorsa();
//		Stanza stanza = partita.getStanzaCorrente();
//		Attrezzo prova = new Attrezzo("prova", 1);
//		
//		borsa.addAttrezzo(prova);
//		prendi.esegui(partita);
//		
//		assertNull(borsa.getAttrezzo(prendi.getParametro()));
//		assertNotNull(stanza.getAttrezzo(prendi.getParametro()));
//	}

}
