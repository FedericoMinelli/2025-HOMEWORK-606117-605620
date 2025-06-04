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

	// costruttore per partite nel labirinto di default
	public Partita(){
		this.labirinto = Labirinto.newBuilder()  // non sono sicuro sia il modo migliore
				 .addStanzaIniziale("LabCampusOne")
				 .addStanzaVincente("Biblioteca")
				 .addStanza("N11")
				 .addAdiacenza("LabCampusOne","Biblioteca","nord")	// prima era ovest
				 .addAdiacenza("LabCampusOne","N11","sud")
				 .addAdiacenza("N11","LabCampusOne","nord")
				 .getLabirinto();// creazione riferimento di un'istanza Labirinto
		stanzaCorrente = labirinto.getStanzaIniziale();		// prendo da labirinto l'informazione della stanza dove il gioco inizia
		stanzaVincente = labirinto.getStanzaVincente();		// prendo da labirinto l'informazione della stanza vincente

		this.giocatore = new Giocatore();	// creazione riferimento di un'istanza Giocatore

		this.finita = false;
	}
	
	// costruttore a supporto di partite in labirinti diversi
	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		stanzaCorrente = labirinto.getStanzaIniziale();
		stanzaVincente = labirinto.getStanzaVincente();
		
		this.giocatore = new Giocatore();
		
		this.finita = false;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
//		System.out.println("Stanza corrente: " + getStanzaCorrente());
//	    System.out.println("Stanza vincente: " + getStanzaVincente());
//	    System.out.println("Stesso oggetto? " + (getStanzaCorrente() == getStanzaVincente()));
		return getStanzaCorrente().equals(getStanzaVincente());	
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
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
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
