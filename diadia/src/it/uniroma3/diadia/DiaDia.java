package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole IO;
	
	
	

	public DiaDia(IOConsole IO) {
		this.IO = IO;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		

		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do		
			istruzione = this.IO.leggiRiga();
		while (!processaIstruzione(istruzione));  // se processaIstruzione returna false allora può essere letto un altro comando

		
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita); 
		if (this.partita.vinta())
			System.out.println("Hai vinto!");
		if (this.partita.getGiocatore().getCfu() == 0)
			System.out.println("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}
	/* vecchia processaIstruzione */
//	private boolean processaIstruzione(String istruzione) {
//		Comando comandoDaEseguire = new Comando(istruzione);
//
//		if(comandoDaEseguire.getNome()==null)
//			return false;
//		if (comandoDaEseguire.getNome().equals("fine")) {
//			this.fine(); 
//			return true;
//		}
//		else if (comandoDaEseguire.getNome().equals("vai"))
//			this.vai(comandoDaEseguire.getParametro());
//
//		else if (comandoDaEseguire.getNome().equals("aiuto"))
//			this.aiuto();
//
//		else if (comandoDaEseguire.getNome().equals("prendi"))
//			this.prendi(comandoDaEseguire.getParametro());
//
//		else if (comandoDaEseguire.getNome().equals("posa"))
//			this.posa(comandoDaEseguire.getParametro());
//
//		else
//			this.IO.mostraMessaggio("Comando sconosciuto");
//		
//		if(this.partita.isFinita()) {
//			if (this.partita.vinta()) {
//				this.IO.mostraMessaggio("Hai vinto!");
//			}
//			else
//				this.IO.mostraMessaggio("Hai perso!");	// ora c'è una stampa anche quando si perde
//			return true;
//		}
//		return false;
//	}   

	
	/* implementazioni dei comandi dell'utente: */

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String messaggio = "";
		for(int i=0; i< elencoComandi.length; i++) {
			messaggio += elencoComandi[i] + " ";
		}
			this.IO.mostraMessaggio(messaggio);  
		
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();		// aggiunta chiamata al metodo getGiocatore() per poter accedere ai cfu
			this.partita.getGiocatore().setCfu(--cfu);		// ora i cfu vengono scalati correttamente
		}
		this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
		this.IO.mostraMessaggio(this.partita.getGiocatore().toString());	// printa il contentuto della borsa e i cfu attuali
	}

	/**
	 * comando Prendi
	 */
	private void prendi(String nomeAttrezzo) {
		// se peso della borsa è uguale al suo massimo allora non posso aggiungere un nuovo attrezzo
		if(this.partita.getGiocatore().getBorsa().getPeso() == this.partita.getGiocatore().getBorsa().getPesoMax()) {
			this.IO.mostraMessaggio("La borsa è al completo!");
		}
		else if(nomeAttrezzo==null) {
			String messaggio = "";
			this.IO.mostraMessaggio("Quale attrezzo vuoi prendere ? ");
			// stampa tutti gli attrezzi nella stanza
			for(Attrezzo a : this.partita.getStanzaCorrente().getAttrezzi()) {
				if(a!=null)
					messaggio += a + " ";
				
			}
			this.IO.mostraMessaggio(messaggio);
		}
		else{
			/* si potrebbe fare un altro if che sfrutta hasAttrezzo per vedere se
			 * l'attrezzo da prendere esiste ma lo stesso compito viene svolto da
			 * getAttrezzo(nomeAttrezzo) perché restituisce null quando non lo trova*/
			Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(a!=null) {
				// controllo se l'aggiunta si può fare e in caso aggiungo
				if(this.partita.getGiocatore().giocatoreAddAttrezzo(a)) {
					this.partita.getStanzaCorrente().removeAttrezzo(a);		// rimuovo da stanza
					this.IO.mostraMessaggio("Operazione avvenuta correttamente!!");
				}
				else 
					this.IO.mostraMessaggio("Questo attrezzo è troppo pesante e non può essere aggiunto, prova con un altro!");				
			}
			else 
				this.IO.mostraMessaggio("Attrezzo non trovato");	
			
			this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
			this.IO.mostraMessaggio(this.partita.getGiocatore().toString()); // printa il contentuto della borsa e i cfu attuali
		}
	}

	/**
	 * comando Posa
	 */
	private void posa(String nomeAttrezzo) {
		// controllo se la borsa è vuota
		if(this.partita.getGiocatore().getBorsa().isEmpty()) {
			
			this.IO.mostraMessaggio("Nessun attrezzo da posare");
			this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());;
			this.IO.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());		// printa il contenuto della borsa		
		}
		else if(nomeAttrezzo == null) {
			this.IO.mostraMessaggio("Quale attrezzo vuoi posare ? ");
			this.IO.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());		// stampa tutti gli attrezzi in borsa
		}
		else {
			// salvo dentro un riferimento 'borsa' la borsa della partita
			Borsa borsa = this.partita.getGiocatore().getBorsa();	
			if(borsa.hasAttrezzo(nomeAttrezzo)) {
				// se la stanza ha posto posso posare l'attrezzo, altrimenti no
				if(this.partita.getStanzaCorrente().addAttrezzo(borsa.getAttrezzo(nomeAttrezzo))) {
					this.partita.getGiocatore().giocatoreRemoveAttrezzo(borsa.getAttrezzo(nomeAttrezzo));			// rimuovo da borsa
					this.IO.mostraMessaggio("Operazione avvenuta correttamente!!");
				}
				else
					this.IO.mostraMessaggio("La stanza è al completo, non puoi posare l'attrezzo qui!");
			}
			else
				this.IO.mostraMessaggio("Attrezzo non trovato!");
			
			this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
			this.IO.mostraMessaggio(this.partita.getGiocatore().toString());		// printa il contentuto della borsa e i cfu attuali
		}		
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole IO = new IOConsole();
		DiaDia gioco = new DiaDia(IO);
		gioco.gioca();
		
		
		
		

	}
}