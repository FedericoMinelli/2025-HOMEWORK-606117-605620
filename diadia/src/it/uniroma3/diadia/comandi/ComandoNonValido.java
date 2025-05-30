package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	
	public static final String NOME = "non valido";
	
	public ComandoNonValido() {
		super(NOME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void esegui(Partita partita) {
		super.getIO().mostraMessaggio("Comando non valido"); 
	}


}
