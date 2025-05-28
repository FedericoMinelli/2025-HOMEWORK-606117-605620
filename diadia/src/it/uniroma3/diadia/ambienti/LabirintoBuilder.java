package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
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
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		// se esiste una stanza allora posso aggiungere un attrezzo...
		if(this.ultimaStanzaAggiunta != null)
			this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nome, peso));
		
		// ...altrimenti ritorna il riferimento a LabirintoBuilder senza fare nulla
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String riferimento, String adiacente, String direzione) {
		if(riferimento == null) return this;
		if(stanze.containsKey(adiacente))
			this.stanze.get(riferimento).impostaStanzaAdiacente(direzione, this.stanze.get(adiacente));
		this.stanze.get(riferimento).impostaStanzaAdiacente(direzione, new Stanza(adiacente));
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
		return null;
	}
}
