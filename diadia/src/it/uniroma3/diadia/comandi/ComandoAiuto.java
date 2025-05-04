package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	
	@Override
	public void esegui(Partita partita) {
		String messaggio = "";
		for(int i=0; i< elencoComandi.length; i++) {
			messaggio += elencoComandi[i] + " ";
		}
		System.out.println(messaggio); 
	}

	@Override
	public void setParametro(String parametro) {
		/* 	questo metodo è vuoto ma deve essere 
		 *  comunque presente perche questa 
		 *  classe implementa Comando
		 */
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

}
