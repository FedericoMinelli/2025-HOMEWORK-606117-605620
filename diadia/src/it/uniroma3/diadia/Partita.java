package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
// import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;
	
	private Labirinto labirinto;	// nuova variabile di istanza labirinto

	private boolean finita;
	private int cfu;
	
	public Partita(){
		labirinto = new Labirinto();
		labirinto.creaLabirinto();		// creazione del labirinto, per farlo -> chiamata a classe Labirinto
		this.finita = false;
		this.cfu = CFU_INIZIALI;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return labirinto.getStanzaCorrente() == labirinto.getStanzaVincente();	// ho riadattato il return data la nuova variabile di istanza
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (cfu == 0);
	}
	
	// aggiunto getter di labirinto
	public Labirinto getLabirinto() {
		return labirinto;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
}
