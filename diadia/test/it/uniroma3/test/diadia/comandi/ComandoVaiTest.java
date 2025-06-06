package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoGuarda;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoVaiTest {

	private Partita partita;
	private ComandoVai vai;
	private ComandoVai vai2;
	private ComandoGuarda guarda;
	private IOConsole io;
	private Direzioni nord;
	private Direzioni sud;
	private Direzioni ovest;
	private Direzioni est;
	
	@BeforeEach
	void setUp() throws Exception {
		/**
		 * IMPORTANTE:
		 * prima di chiamare un qualsiasi metodo su un oggetto Comando mi devo
		 * assicurare che sia inizializzato il suo paramentro "IO io" senno
		 * verrà sollevata una NullPointerException.
		 */
		Scanner scanner = new Scanner(System.in);
		io = new IOConsole(scanner);
		partita = new Partita();
		vai = new ComandoVai(nord);
		vai.setIO(io);
		vai2 = new ComandoVai(sud);
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
		partita.getStanzaCorrente().impostaStanzaAdiacente(nord, null);
		vai.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente()); 
	}
	
	@Test
	void testEsegui_DirezioneEsistente() {
		Stanza stanza = partita.getStanzaCorrente(); 
		vai.esegui(partita);
		assertEquals(stanza.getStanzaAdiacente(nord), partita.getStanzaCorrente());
	}
	
//	PRIMA DI FARE QUESTI TEST "SCOMMENTARE" GLI IMPORT
	@Test 
	void testStanzaBloccata(){
		Stanza stanza = partita.getStanzaCorrente();		
		StanzaBloccata bloccata = new StanzaBloccata("bloccata", ovest, "chiave");
		stanza.impostaStanzaAdiacente(sud, bloccata);
		bloccata.impostaStanzaAdiacente(nord, stanza);
		Stanza stanza2 = new Stanza("non bloccata");
		stanza2.impostaStanzaAdiacente(est, bloccata);
		bloccata.impostaStanzaAdiacente(ovest, stanza2);
		vai2.esegui(partita);		// mi sposto a sud, quindi nella stanza che ha una direzione bloccata
		guarda.esegui(partita);
		vai2.setParametro("ovest");		// provo ad andare nella direzione bloccata
		vai2.esegui(partita);
		assertEquals(bloccata, partita.getStanzaCorrente());
		assertSame(bloccata, partita.getStanzaCorrente());
	}
	
	@Test 
	void testStanzaSbloccata(){
		Stanza stanza = partita.getStanzaCorrente();
		StanzaBloccata bloccata = new StanzaBloccata("bloccata", ovest, "chiave");
		stanza.impostaStanzaAdiacente(sud, bloccata);
		bloccata.impostaStanzaAdiacente(nord, stanza);
		Stanza stanza2 = new Stanza("non bloccata");
		stanza2.impostaStanzaAdiacente(est, bloccata);
		bloccata.impostaStanzaAdiacente(ovest, stanza2);
		vai2.esegui(partita);		// mi sposto a sud, quindi nella stanza che ha una direzione bloccata
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("chiave", 1));
		guarda.esegui(partita);
		vai2.setParametro("ovest");		// provo ad andare nella direzione bloccata
		System.out.println("prova: "+vai2.getParametro());
		vai2.esegui(partita);
		assertEquals(stanza2, partita.getStanzaCorrente());
		assertSame(stanza2, partita.getStanzaCorrente());
	}
	
	@Test
	void testStanzaBuiaLabirintoBuilder_BuioPesto() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaBuia("cripta", "torcia")
				.addAdiacenza("atrio", "cripta", nord)
				.getLabirinto();
		partita = new Partita(lab);
		vai.esegui(partita);
		assertEquals("qui c'è buio pesto", partita.getStanzaCorrente().getDescrizione());
		
				
	}
	
	@Test
	void testStanzaBuiaLabirintoBuilder_CiStaVisibilita() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("torcia", 1)
				.addStanzaBuia("cripta", "torcia")
				.addAdiacenza("atrio", "cripta", nord)
				.getLabirinto();
		
		partita = new Partita(lab);
		vai.esegui(partita);
		assertEquals("qui c'è buio pesto", partita.getStanzaCorrente().getDescrizione());
		//System.out.println(partita.getStanzaCorrente().getDescrizione());
		lab.getStanzaIniziale().getStanzaAdiacente(nord).addAttrezzo(new Attrezzo("torcia", 1));
		assertEquals(lab.getStanzaIniziale().getStanzaAdiacente(nord).getDescrizione(), partita.getStanzaCorrente().getDescrizione());
		//System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	void testLabirintoBuilder_VittoriaSuperandoTutteStanze() {
		Labirinto lab = Labirinto.newBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("torcia", 1)
				.addStanzaVincente("uscita")
				.addStanzaBuia("cripta", "torcia")
				.addAttrezzo("chiave", 1)
				.addStanzaBloccata("corridoio", nord, "chiave")
				.addAdiacenza("atrio", "cripta", nord)
				.addAdiacenza("cripta", "corridoio", nord)
				.addAdiacenza("corridoio", "uscita", nord)
				.getLabirinto();
		partita = new Partita(lab);
		
		ComandoPrendi prendi = new ComandoPrendi("torcia");
		prendi.setIO(io);
		prendi.esegui(partita);		// presa la torcia per illuminare la stanza buia
		vai.esegui(partita);		// vado verso nord
		prendi.setParametro("chiave");
		prendi.esegui(partita);		// raccolgo una chiave, puo essere utile...
		vai.esegui(partita);		// vado verso nord
		vai.esegui(partita);		// vado verso nord ma dovrebbe dirmi che la direzione è bloccata
		ComandoPosa posa = new ComandoPosa("chiave");
		posa.setIO(io);
		posa.esegui(partita);
		vai.esegui(partita);
		
		assertEquals(partita.getStanzaCorrente(), partita.getStanzaVincente());
		
		
		
	}

}
