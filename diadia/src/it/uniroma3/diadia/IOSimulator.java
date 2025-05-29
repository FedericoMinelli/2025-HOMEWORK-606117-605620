package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;


public class IOSimulator implements IO{
	
	private List<String> righeDaLeggere;
	private int indiceRighe;
	private List<String> messaggi;
	private int indiceMessaggiM;		// M = mostrati
	private int indiceMessaggiP;		// P = prodotti

	public IOSimulator(List<String> righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRighe = 0;
		this.messaggi = new ArrayList<>();
		this.indiceMessaggiM = 0;
		this.indiceMessaggiP = 0;
	}
	
	@Override
	/*
	 * istanzia una casella dell'array di messaggi 
	 * */
	public void mostraMessaggio(String messaggio) {
		this.messaggi.add(messaggio);
		this.indiceMessaggiP++;
	}
	
	public int getIndiceMessaggiM() {
		return indiceMessaggiM;
	}

	public int getIndiceMessaggiP() {
		return indiceMessaggiP;
	}

	@Override
	/*
	 * returna una casella dell'array di righe da leggere 
	 * */
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere.get(this.indiceRighe);
		this.indiceRighe++;
		return rigaLetta;
	}
	
	/*
	 * returna una casella dell'array di messaggi 
	 * */
	public String nextMessaggio() {
		String next = this.messaggi.get(indiceMessaggiM);
		this.indiceMessaggiM++;
		return next;
	}
	
	/*  */
	public boolean hasMessaggio() {
		return this.indiceMessaggiP > this.indiceMessaggiM;
	}
}
