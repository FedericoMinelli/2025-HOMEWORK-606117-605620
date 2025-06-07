package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
	
	private static final String NOME = "vai";
	private Direzioni direzione;	// parametro	
	
	public ComandoVai(Direzioni direzione) {
		super(NOME, direzione);
		this.direzione = direzione;
	}
	public ComandoVai() {
		super("vai");
	}
	
	

	@Override
	public void esegui(Partita partita) {
		
		
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
	@Override
	public void setParametro(String nuovoParametro) {
		super.setParametro(nuovoParametro);
		try {
			this.direzione = Direzioni.valueOf(nuovoParametro.toUpperCase());
		} catch (Exception e) {
			this.direzione = null;
		}
	}

}