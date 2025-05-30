package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

// stampa la stanza corrente e lo stato della partita (cfu rimanenti)
public class ComandoGuarda extends AbstractComando {

	private static final String NOME = "guarda";

	public ComandoGuarda() {
		super(NOME);
	}
	
	@Override
	public void esegui(Partita partita) {
		super.getIO().mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().getDescrizione());
		super.getIO().mostraMessaggio(partita.getGiocatore().toString());
	}

}

