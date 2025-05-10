package it.uniroma3.diadia;

public class IOSimulator implements IO{
	
	private String[] righeDaLeggere;
	private int indiceRighe;
	private String[] messaggi;
	private int indiceMessaggiM;		// M = mostrati
	private int indiceMessaggiP;		// P = prodotti

	public IOSimulator(String[] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRighe = 0;
		this.messaggi = new String[500];
		this.indiceMessaggiM = 0;
		this.indiceMessaggiP = 0;
	}
	
	@Override
	/*
	 * istanzia una casella dell'array di messaggi 
	 * */
	public void mostraMessaggio(String messaggio) {
		this.messaggi[this.indiceMessaggiP] = messaggio;
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
		String rigaLetta = this.righeDaLeggere[this.indiceRighe];
		this.indiceRighe++;
		return rigaLetta;
	}
	
	/*
	 * returna una casella dell'array di messaggi 
	 * */
	public String nextMessaggio() {
		String next = this.messaggi[this.indiceMessaggiM];
		this.indiceMessaggiM++;
		return next;
	}
	
	/*  */
	public boolean hasMessaggio() {
		return this.indiceMessaggiP > this.indiceMessaggiM;
	}
}
