package it.uniroma3.diadia.ambienti;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
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
