package it.uniroma3.diadia.ambienti;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	private Stanza atrio;
	private Stanza aulaN11;
	private Stanza aulaN10;
	private Stanza laboratorio;
	private Stanza biblioteca;
	
	private Attrezzo lanterna;
	private Attrezzo osso;
	private Attrezzo pietra; // prova
	
	private Labirinto() {
		init();
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
	private void init() {
		creaAttrezzi();
		creaStanze();
		collegaStanze();
		poniAttrezzi();
		setStanzaIniziale(atrio);
		setStanzaVincente(biblioteca);
		
	}
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	public static class LabirintoBuilder {
		
		private Labirinto labirinto;
		private Map<String, Stanza> stanze;
		private Stanza ultimaStanzaAggiunta;
		private static final List<String> direzioniPossibili = Arrays.asList("nord","sud","ovest","est");
		
		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.stanze = new HashMap<>();
		}
		
		public LabirintoBuilder addStanzaIniziale(String iniziale) {
			this.ultimaStanzaAggiunta = new Stanza(iniziale);
			this.labirinto.setStanzaIniziale(ultimaStanzaAggiunta);		// crea e aggiunge la stanza iniziale
			this.stanze.put(iniziale, ultimaStanzaAggiunta);
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String vincente) {
			this.ultimaStanzaAggiunta = new Stanza(vincente);
			this.labirinto.setStanzaVincente(ultimaStanzaAggiunta);
			this.stanze.put(vincente, ultimaStanzaAggiunta);
			return this;
		}
		
		// aggiunge un attrezzo all'ultima stanza aggiunta
		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			// se esiste una stanza allora posso aggiungere un attrezzo...
			if(this.ultimaStanzaAggiunta != null)
				this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nome, peso));
			
			// ...altrimenti ritorna il riferimento a LabirintoBuilder senza fare nulla
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String riferimento, String adiacente, String direzione) {
			if(riferimento == null || adiacente == null) { 
				System.out.println("Problema");
				return this;	// non faccio nulla se ho argomenti null per le stanze
			}
			if(!direzioniPossibili.contains(direzione)) return this;	// non faccio nulla ste sto l'argomento direzione contiene una direzione non conosciuta
			
			// se la stanza con nome del parametro "adiacente" collego quella...
			if(stanze.containsKey(adiacente)) {
				this.stanze.get(riferimento).impostaStanzaAdiacente(direzione, this.stanze.get(adiacente));
				return this;
			}
			// ...altrimenti ne creo una nuova e l'aggiungo alla mappa di stanze esistenti
			Stanza nuovaStanza = new Stanza(adiacente);
			this.stanze.put(adiacente, nuovaStanza);
			this.stanze.get(riferimento).impostaStanzaAdiacente(direzione, nuovaStanza);
			return this;
		}
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanza(String nome) {
			this.ultimaStanzaAggiunta = new Stanza(nome);
			this.stanze.put(nome, ultimaStanzaAggiunta);
			return this;
		}

		public Map<String, Stanza> getListaStanze() {
			return this.stanze;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
			this.ultimaStanzaAggiunta = new StanzaMagica(nome, sogliaMagica);
			this.stanze.put(nome, ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String dir, String attrezzo) {
			this.ultimaStanzaAggiunta = new StanzaBloccata(nome, dir, attrezzo);
			this.stanze.put(nome, ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
			this.ultimaStanzaAggiunta = new StanzaBuia(nome, attrezzo);
			this.stanze.put(nome, ultimaStanzaAggiunta);
			return this;
		}
	}

	
	/**
	 * crea gli attrezzi
	 */
	public void creaAttrezzi() {

    	lanterna = new Attrezzo("lanterna",3);
		osso = new Attrezzo("osso",1);
		pietra = new  Attrezzo("pietra", 7);	// prova
	}
	
	/**
	 * crea stanze del labirinto
	 */
	public void creaStanze() {
    	
		atrio = new Stanza("Atrio");
		aulaN11 = new Stanza("Aula N11");
		aulaN10 = new Stanza("Aula N10");
		laboratorio = new Stanza("Laboratorio Campus");
		biblioteca = new Stanza("Biblioteca");
		
	}
	
	/**
	 * collega le stanze
	 */
	public void collegaStanze() {
		
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		
		/* vecchio collegamento, da lasciare in caso di cambi futuri */
//		atrio.impostaStanzaAdiacente("nord", biblioteca);
//		atrio.impostaStanzaAdiacente("est", aulaN11);
//		atrio.impostaStanzaAdiacente("sud", aulaN10);
//		atrio.impostaStanzaAdiacente("ovest", laboratorio);
//		aulaN11.impostaStanzaAdiacente("est", laboratorio);		// effetto pac-man
//		aulaN11.impostaStanzaAdiacente("ovest", atrio);
//		aulaN10.impostaStanzaAdiacente("nord", atrio);
//		aulaN10.impostaStanzaAdiacente("est", aulaN11);			// monodirezionale n10 -> n11
//		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);	// monodirezionale n10 -> lab
//		laboratorio.impostaStanzaAdiacente("est", atrio);
//		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);	// effetto pac-man
//		biblioteca.impostaStanzaAdiacente("sud", atrio);
		
	}
	
	/**
	 * pone gli attrezzi nelle stanze
	 */
	public void poniAttrezzi() {

		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(pietra);	// prova
	}
	
	public void setStanzaIniziale(Stanza iniziale) {
		stanzaIniziale = iniziale;
	}
	
	public void setStanzaVincente(Stanza vincente) {
		stanzaVincente = vincente;
	}

	/**
	 * getter di stanzaIniziale
	 * @return	stanzaIniziale
	 */
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	/**
	 * getter di stanzaFinale
	 * @return	stanzaFinale
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	
 
}
