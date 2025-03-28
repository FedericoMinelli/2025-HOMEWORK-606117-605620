package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
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
	
	private Labirinto labirinto;	// nuova variabile di istanza labirinto
	private Giocatore giocatore;	// nuova variabile di istanza giocatore
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	private boolean finita;
	
	public Partita(){
		this.labirinto = new Labirinto();	// creazione riferimento di un'istanza Labirinto
		stanzaCorrente = labirinto.getStanzaIniziale();		// prendo da labirinto l'informazione della stanza dove il gioco inizia
		stanzaVincente = labirinto.getStanzaFinale();		// prendo da labirinto l'informazione della stanza dove il gioco finisce
		
		this.giocatore = new Giocatore();	// creazione riferimento di un'istanza Giocatore
		
		this.finita = false;
		
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return getStanzaCorrente() == getStanzaVincente();	
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
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
	
	/**
	 * getter della stanza vincente
	 * @return stanzaVincente inizializzata tramite labirinto
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	/**
	 * Aggiorna la stanza corrente
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * getter della stanza corrente
	 * @return stanzaCorrente, ad inizio gioco ha un riferimento ottenuto tramite labirinto.getStanzaIniziale, 
	 * <p>poi cambia a seconda delle scelte del giocatore
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * aggiunto getter di giocatore
	 * @return giocatore
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}
	
}
/* public int getCfu() {
return this.cfu;
}
public void setCfu() {
this.cfu=cfu;
}
public String toString() {
return this.getStringa 
} */
