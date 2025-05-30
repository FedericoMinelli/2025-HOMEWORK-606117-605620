package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	
	public static final String NOME = "fine";
	
	public ComandoFine() {
		super(NOME);
	}
	
	@Override
	public void esegui(Partita partita) {
		super.getIO().mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
}
