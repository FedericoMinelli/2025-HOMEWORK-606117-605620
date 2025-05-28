package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Map<String, Stanza> stanze;
	private Stanza ultimaStanzaAggiunta;
	
	public LabirintoBuilder() {
		this.stanze = new HashMap<>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String iniziale) {
		this.ultimaStanzaAggiunta = new Stanza(iniziale);
		this.labirinto.setStanzaIniziale(ultimaStanzaAggiunta);		// crea e aggiunge la stanza iniziale
		
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String vincente) {
		this.ultimaStanzaAggiunta = new Stanza(vincente);
		this.labirinto.setStanzaVincente(ultimaStanzaAggiunta);
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
		
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
