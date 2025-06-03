package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
	
	private static final String NOME = "vai";
	private String direzione;	// parametro	
		
	public ComandoVai() {		// ne ho bisogno se no FabbricaDiComandiRiflessiva non creava l'istanza giusta perche usava un costruttore no-args di defaul per questa classe
		this(null);
	}
	
	public ComandoVai(String direzione) {
		super(NOME, direzione);
		this.direzione = direzione;
	}
	
	@Override
	public void esegui(Partita partita) {
		this.direzione = super.getParametro();		// potrei avere la stessa istanza ComandoVai ma con il parametro cambiato, quindi controllo il valore nella superclasse e poi salva in questa
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		// quando non viene specificata la direzione
		if(direzione == null) {
			super.getIO().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		// controllo se esiste la stanza in cui si desidera andare
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			super.getIO().mostraMessaggio("Direzione inesistente");
			return;
		}
		
		// se esiste, il giocatore si sposta
		partita.setStanzaCorrente(prossimaStanza);
		super.getIO().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}

}