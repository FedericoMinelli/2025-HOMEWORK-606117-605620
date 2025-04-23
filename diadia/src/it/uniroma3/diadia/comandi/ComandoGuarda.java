package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

// stampa la stanza corrente e lo stato della partita (cfu rimanenti)
public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		System.out.println("Stanza corrente: " + partita.getStanzaCorrente().toString());
		System.out.println("CFU rimanenti: " + partita.getGiocatore().getCfu());
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
