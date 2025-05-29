package it.uniroma3.diadia.giocatore;


import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {

	static final private int CFU_INIZIALI = 20;
	
	private Borsa borsa;
	private int cfu;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();		// creazione riferimento di un'istanza Borsa
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	/**
	 * Aggiunge un'attrezzo alla borsa di giocatore
	 * @param attrezzo
	 * @return	true se attrezzo viene aggiunto, false altrimenti
	 */
	public boolean giocatoreAddAttrezzo(Attrezzo attrezzo) {
		 return borsa.addAttrezzo(attrezzo.getNome(), attrezzo) != null;
	}
	
	/**
	 * Rimuove un attrezzo dalla borsa di giocatore
	 * @param attrezzo
	 * @return l'attrezzo rimosso se va a buon fine, null altrimenti
	 */
	public Attrezzo giocatoreRemoveAttrezzo(Attrezzo attrezzo) {
		 return borsa.removeAttrezzo(attrezzo.getNome());
	}

	public Borsa getBorsa() {
		return borsa;
	}
	
	/**
	 * Getter del peso della borsa
	 * @return peso attuale della borsa
	 */
	public int getPesoBorsa() {
		return borsa.getPeso();
	}
	
	// Stampa la borsa del giocatore e i cfu attuali
	public String toString() {
		return this.borsa.toString() + "\nCFU attuali: " + this.getCfu();
	}
}
