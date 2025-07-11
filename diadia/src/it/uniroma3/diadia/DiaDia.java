package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.*;


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


	private static Direzioni ovest = Direzioni.Ovest;

	
	private Partita partita;
	private IO io;
	
	
	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita();
	}
	
	public DiaDia(Labirinto labirinto, IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);		
	}

	public void gioca() {
		String istruzione; 
		
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));// se processaIstruzione returna false allora può essere letto un altro comando	
		this.io.mostraMessaggio("Grazie di aver giocato!");
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.setIO(this.io);
		comandoDaEseguire.esegui(this.partita); 
		if (this.partita.vinta())
			this.io.mostraMessaggio("Hai vinto!");
		if (this.partita.getGiocatore().getCfu() == 0)
			this.io.mostraMessaggio("CFU esauriti, hai perso...");
		return this.partita.isFinita();
	}
	
	public static void main(String[] argc) {
		Scanner scanner = new Scanner(System.in);
		IO io = new IOConsole(scanner);
		Labirinto labirinto = Labirinto.newBuilder()
				 .addStanzaIniziale("LabCampusOne")
				 .addStanzaVincente("Biblioteca")
				 .addAdiacenza("LabCampusOne","Biblioteca",ovest)
				 .getLabirinto();
				 
		DiaDia gioco = new DiaDia(labirinto, io);			
		try {
			gioco.gioca();
		} finally {
			scanner.close();
		}
		
	}
}