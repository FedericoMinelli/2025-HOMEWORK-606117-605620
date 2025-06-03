package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{
	
	private static final String NOME = "saluta";
	private static final String MESSAGGIO_CHI = "Chi dovrei salutare?...";
	
	public ComandoSaluta() {
		super(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			super.getIO().mostraMessaggio(personaggio.saluta());
		} else 
			super.getIO().mostraMessaggio(MESSAGGIO_CHI);
	}	

}
