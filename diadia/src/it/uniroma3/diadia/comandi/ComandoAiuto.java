package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	static final private String NOME = "aiuto";
	private IO io;

	public ComandoAiuto() {
		super(NOME);
	}
	
	@Override
	public void esegui(Partita partita) {
		String messaggio = "";
		for(int i=0; i< elencoComandi.length; i++) {
			messaggio += elencoComandi[i] + " ";
		}
		io.mostraMessaggio(messaggio); 
	}

	

}
