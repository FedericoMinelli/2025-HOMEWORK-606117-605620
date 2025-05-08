package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

// stampa la stanza corrente e lo stato della partita (cfu rimanenti)
public class ComandoGuarda implements Comando {

	private IO io;
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().toString());
	}

	@Override
	public void setParametro(String parametro) {	

	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNome() {
		return "guarda";
	}

}
