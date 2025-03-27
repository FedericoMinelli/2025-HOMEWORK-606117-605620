package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	
	private Stanza atrio;
	private Stanza aulaN11;
	private Stanza aulaN10;
	private Stanza laboratorio;
	private Stanza biblioteca;
	
	private Attrezzo lanterna;
	private Attrezzo osso;
	
	public Labirinto() {
		init();
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
	private void init() {
		creaAttrezzi();
		creaStanze();
		collegaStanze();
		poniAttrezzi();
		setStanzaIniziale();
		setStanzaFinale();
		
	}
	
	/**
	 * crea gli attrezzi
	 */
	public void creaAttrezzi() {

    	lanterna = new Attrezzo("lanterna",3);
		osso = new Attrezzo("osso",1);
	}
	
	/**
	 * crea stanze del labirinto
	 */
	public void creaStanze() {
    	
		atrio = new Stanza("Atrio");
		aulaN11 = new Stanza("Aula N11");
		aulaN10 = new Stanza("Aula N10");
		laboratorio = new Stanza("Laboratorio Campus");
		biblioteca = new Stanza("Biblioteca");
		
	}
	
	/**
	 * collega le stanze
	 */
	public void collegaStanze() {
		
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		
		/* vecchio collegamento, da lasciare in caso di cambi futuri */
//		atrio.impostaStanzaAdiacente("nord", biblioteca);
//		atrio.impostaStanzaAdiacente("est", aulaN11);
//		atrio.impostaStanzaAdiacente("sud", aulaN10);
//		atrio.impostaStanzaAdiacente("ovest", laboratorio);
//		aulaN11.impostaStanzaAdiacente("est", laboratorio);		// effetto pac-man
//		aulaN11.impostaStanzaAdiacente("ovest", atrio);
//		aulaN10.impostaStanzaAdiacente("nord", atrio);
//		aulaN10.impostaStanzaAdiacente("est", aulaN11);			// monodirezionale n10 -> n11
//		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);	// monodirezionale n10 -> lab
//		laboratorio.impostaStanzaAdiacente("est", atrio);
//		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);	// effetto pac-man
//		biblioteca.impostaStanzaAdiacente("sud", atrio);
		
	}
	
	/**
	 * pone gli attrezzi nelle stanze
	 */
	public void poniAttrezzi() {

		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
	}
	
	/**
	 * il gioco comincia nell'atrio
	 */
	public void setStanzaIniziale() {
		stanzaIniziale = atrio;
	}
	
	/**
	 * il gioco finisce nella biblioteca
	 */
	public void setStanzaFinale() {
		stanzaFinale = biblioteca;
	}

	/**
	 * getter di stanzaIniziale
	 * @return	stanzaIniziale
	 */
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	/**
	 * getter di stanzaFinale
	 * @return	stanzaFinale
	 */
	public Stanza getStanzaFinale() {
		return stanzaFinale;
	}
	
	
 
}
