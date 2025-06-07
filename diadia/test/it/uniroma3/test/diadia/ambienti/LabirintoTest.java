package it.uniroma3.test.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoTest {
	
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";
	private Labirinto labirinto;
	private Direzioni ovest = Direzioni.Ovest;
	private Direzioni nord = Direzioni.Nord;
	private Direzioni sud = Direzioni.Sud;
	private Direzioni est = Direzioni.Est;
	@BeforeEach
	void setUp() throws Exception {
		
		this.labirinto = Labirinto.newBuilder()  // non sono sicuro sia il modo migliore
				.addStanzaIniziale("LabCampusOne")
				 .addStanzaVincente("Biblioteca")
				 .addAdiacenza("LabCampusOne","Biblioteca",ovest)
				 .getLabirinto();
		
	}

	// verifico che la stanza iniziale sia quella giusta, cioè Atrio
	@Test
	void testGetStanzaIniziale() {
		assertEquals("LabCampusOne", this.labirinto.getStanzaIniziale().getNome());
	}
	
	// verifico che la stanza finale sia quella giusta, cioè Biblioteca
	@Test
	void testGetStanzaFinale() {
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
	}
	@Test
	void testBuilderCreaLabirintoConStanzaInizialeEVincente() {
		Labirinto labirinto = Labirinto
				.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanzaVincente("Tesoro")
				.getLabirinto();

		assertEquals("Ingresso", labirinto.getStanzaIniziale().getNome());
		assertEquals("Tesoro", labirinto.getStanzaVincente().getNome());
	}

	@Test
	void testBuilderConAdiacenza() {
		Labirinto labirinto = Labirinto
				.newBuilder()
				.addStanzaIniziale("Ingresso")
				.addStanza("Stanza2")
				.addAdiacenza("Ingresso", "Stanza2", nord)
				.getLabirinto();

		Stanza ingresso = labirinto.getStanzaIniziale();
		assertNotNull(ingresso.getStanzaAdiacente(nord));
		assertEquals("Stanza2", ingresso.getStanzaAdiacente(nord).getNome());
	}
	@Test
	public void testMonolocale() {
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),monolocale.getStanzaIniziale().getAttrezzi());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, nord)
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, sud)
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente(nord));
		assertEquals(Collections.singletonList(nord),bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singletonList(sud),bilocale.getStanzaVincente().getDirezioni());
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", sud)
				.addAdiacenza("biblioteca", nomeStanzaIniziale, nord)
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, est)
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , ovest)
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaIniziale().getStanzaAdiacente(sud).getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
		Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", nord)
				.getLabirinto();
		assertTrue(Labirinto.newBuilder().getListaStanze().size()<=2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				//.addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", nord)
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", ovest)
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", sud)
				.addAdiacenza(nomeStanzaIniziale, "stanza 4", est)
				//.addAdiacenza(nomeStanzaIniziale, "stanza 5", nord-est) // stesso motivo di LabirintoBuilderTest
				.getLabirinto();
				Stanza test = new Stanza("stanza 5");
		//assertNull(maze.getStanzaIniziale().getStanzaAdiacente("nord-est"));
		assertTrue(maze.getStanzaIniziale().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getStanzaIniziale().getMapStanzeAdiacenti().containsValue(test));
		Map<Direzioni,Stanza> mappa = new HashMap<>();
		mappa.put(nord, new Stanza("stanza 1"));
		mappa.put(ovest, new Stanza("stanza 2"));
		mappa.put(sud, new Stanza("stanza 3"));
		mappa.put(est, new Stanza("stanza 4"));
		assertEquals(mappa,maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = Labirinto.newBuilder()
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = Labirinto.newBuilder()
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo,maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		LabirintoBuilder labirinto=Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso);
				
		assertTrue(labirinto.getListaStanze().get(nomeStanza).getAttrezzi().contains(new Attrezzo (nomeAttrezzo,peso)));
		assertEquals(new Attrezzo(nomeAttrezzo,peso),labirinto.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		LabirintoBuilder labirinto=Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso);
				
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		List<Attrezzo> attrezzi = labirinto.getListaStanze().get(nomeStanza).getAttrezzi();
		assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		LabirintoBuilder labirinto=Labirinto.newBuilder()		
				.addStanza(nomeStanza1)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirinto.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		LabirintoBuilder labirinto=Labirinto.newBuilder()
				.addStanza(nomeStanza1)
				.addStanza(nomeStanza2)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirinto.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		LabirintoBuilder labirinto=Labirinto.newBuilder()				// questo LabirintoBuilder non viene salvato da nessuna parte, poi...
				.addStanza(nomeStanza1)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addStanza(nomeStanza2)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirinto.getListaStanze();		// ...qui viene creato un nuovo LabirintoBuilder, diverso da quello sopra e completamente vuoto, quindi quando si prova ad usare un metodo su questa istanza genera NullPointerException
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		LabirintoBuilder labirinto=Labirinto.newBuilder()
				.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)labirinto.getListaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		LabirintoBuilder labirintoBuilder = Labirinto.newBuilder()		// da aggiungere in tutti test non funzionanti
				.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		LabirintoBuilder labirinto=Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", nord, "chiave").addAttrezzo("chiave", 1)
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", nord)
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, sud)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, nord)
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", sud);
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,labirinto.getListaStanze().get("stanza bloccata").getStanzaAdiacente(nord));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		LabirintoBuilder labirinto=Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", nord, "chiave")
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", nord)
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, sud)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, nord)
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", sud);
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", nord, "chiave");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata,labirinto.getListaStanze().get("stanza bloccata").getStanzaAdiacente(nord));
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = Labirinto.newBuilder()
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato",nord,"chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", nord)
				.addAdiacenza("corridoio", nomeStanzaIniziale, sud)		
				.addAdiacenza("corridoio", "corridoio bloccato", nord)	
				.addAdiacenza("corridoio bloccato", "corridoio", sud)
				.addAdiacenza("corridoio bloccato", "Aula 1", nord)
				.addAdiacenza("Aula 1", "corridoio bloccato", sud)
				.addAdiacenza("Aula 1", nomeStanzaVincente,nord)
				.addAdiacenza(nomeStanzaVincente, "Aula 1", sud)
				.addAdiacenza("corridoio", "stanza magica", est)		
				.addAdiacenza("stanza magica", "corridoio", ovest)
				.addAdiacenza("corridoio", "stanza buia", ovest)		
				.addAdiacenza("stanza buia", "corridoio", est)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaIniziale().getStanzaAdiacente(nord);
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList(ovest,est,nord,sud)));
		Map<Direzioni,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(nord,new Stanza("corridoio bloccato"));
		mapAdiacenti.put(sud,new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(est,new Stanza("stanza magica"));
		mapAdiacenti.put(ovest,new Stanza("stanza buia"));
		assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Arrays.asList(a1,a2),corridoio.getAttrezzi());
	}
}


