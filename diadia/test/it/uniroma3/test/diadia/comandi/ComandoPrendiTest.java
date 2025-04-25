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

	@Test
	void testEsegui_AttrezzoNonSpecificatoSetParametroNull() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		prendi.setParametro(null);
		prendi.esegui(partita);
		assertNull(borsa.getAttrezzo(prendi.getParametro()));
		assertNull(stanza.getAttrezzo(prendi.getParametro()));
	}
	
	@Test
	void testEsegui_AttrezzoNullStanzaConOggetti() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		prendi.esegui(partita);
		Attrezzo a = new Attrezzo("pera", 1);
		
		stanza.addAttrezzo(a);		// cosi ho la certezza che ci sia almeno un attrezzo
		
		assertNull(borsa.getAttrezzo(prendi.getParametro()));
		assertNull(stanza.getAttrezzo(prendi.getParametro()));
	}
	
	@Test
	void testEsegui_AttrezzoNullStanzaSenzaOggetti() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();

		Attrezzo[] array = stanza.getAttrezzi();
		while(stanza.getNumeroAttrezzi() != 0) {
			stanza.removeAttrezzo(array[0]);
		}
		prendi.esegui(partita);
		
		assertNull(borsa.getAttrezzo(prendi.getParametro()));
		assertNull(stanza.getAttrezzo(prendi.getParametro()));
	}
	
	@Test
	void testEsegui_BorsaPiena() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		
		
		Attrezzo a = new Attrezzo("a", 10);
		Attrezzo prova = new Attrezzo("prova", 1);
		borsa.addAttrezzo(a);
		stanza.addAttrezzo(prova);
		prendi.esegui(partita);
		
		assertNotNull(stanza.getAttrezzo(prendi.getParametro()));
		assertNull(borsa.getAttrezzo(prendi.getParametro()));
	}
	
	@Test
	void testEsegui_Funzionante() {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Stanza stanza = partita.getStanzaCorrente();
		Attrezzo prova = new Attrezzo("prova", 1);
		
		stanza.addAttrezzo(prova);
		prendi.esegui(partita);
		
		assertNull(stanza.getAttrezzo(prendi.getParametro()));
		assertNotNull(borsa.getAttrezzo(prendi.getParametro()));
	}

}
