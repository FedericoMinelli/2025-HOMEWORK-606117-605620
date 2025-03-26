package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Labirinto {
	
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	
	public void creaLabirinto() {

		/**
	     * Crea tutte le stanze e le porte di collegamento
	     */
		
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		
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

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
		
    }
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
}
