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
	 * @return	borsa con nuovo attrezzo aggiunto
	 */
	public boolean giocatoreAddAttrezzo(Attrezzo attrezzo) {
		 return borsa.addAttrezzo(attrezzo);
	}

	public Borsa getBorsa() {
		return borsa;
	}
	
	/**
	 * Getter del peso dela borsa
	 * @return peso attuale della borsa
	 */
	public int getPesoBorsa() {
		return borsa.getPeso();
	}
	
	
	
}
