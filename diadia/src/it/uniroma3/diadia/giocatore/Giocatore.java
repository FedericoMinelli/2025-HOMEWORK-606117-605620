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
	
	/* implementato metodo per salvare gli attrezzi nella variabile di istanza borsa 
	 * ...da migliorare probabilmente in seguito... */
	public boolean giocatoreAddAttrezzo(Attrezzo attrezzo) {
		 return borsa.addAttrezzo(attrezzo);
	}

	public Borsa getBorsa() {
		return borsa;
	}
	
	
}
