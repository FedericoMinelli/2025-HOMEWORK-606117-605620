package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


public class Labirinto {
	
	private static final Direzioni Nord = null;
	private static final Direzioni Sud = null;
	private static final Direzioni Est = null;
	private static final Direzioni Ovest = null;
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
	
	@SuppressWarnings("unused")
	private Labirinto(boolean conInit) {
		if(conInit)
			init();
	}
	private Labirinto() {
		// non chiama init()
	}
	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c =
		new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
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
		public LabirintoBuilder  addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago m = new Mago(nome, presentazione, attrezzo);
			if(this.ultimaStanzaAggiunta==null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(m);
			return this;
		}

		public LabirintoBuilder  addCane(String nome, String presentazione,String cibo, Attrezzo regalo) {
			Cane c = new Cane(nome, presentazione,cibo,regalo);
			if(this.ultimaStanzaAggiunta==null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(c);
			return this;
		}

		public LabirintoBuilder  addStrega(String nome, String presentazione) {
			Strega s = new Strega(nome, presentazione);
			if(this.ultimaStanzaAggiunta==null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(s);
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
		
		public LabirintoBuilder addAdiacenza(String riferimento,String adiacente, Direzioni direzione) {
			if(riferimento == null || adiacente == null) { 
				System.out.println("Problema");
				return this;	// non faccio nulla se ho argomenti null per le stanze
			}
			Stanza c = this.stanze.get(riferimento);
			Stanza a = this.stanze.get(adiacente);
			c.impostaStanzaAdiacente(direzione, a);
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

		public LabirintoBuilder addStanzaBloccata(String nome, Direzioni dir, String attrezzo) {
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
		
		atrio.impostaStanzaAdiacente(Nord, biblioteca);
		atrio.impostaStanzaAdiacente(Est, aulaN11);
		atrio.impostaStanzaAdiacente(Sud, aulaN10);
		atrio.impostaStanzaAdiacente(Ovest, laboratorio);
		aulaN11.impostaStanzaAdiacente(Ovest, atrio);
		aulaN10.impostaStanzaAdiacente(Nord, atrio);
		laboratorio.impostaStanzaAdiacente(Est, atrio);
		biblioteca.impostaStanzaAdiacente(Sud, atrio);
		
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
