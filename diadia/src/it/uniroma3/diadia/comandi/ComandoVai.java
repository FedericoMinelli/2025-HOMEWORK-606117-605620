package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
	
	private static final String NOME = "vai";
	private Direzioni direzione;	// parametro	
	private String parametro;
		
	
	
	public ComandoVai(Direzioni direzione) {
		super(NOME, direzione);
		this.direzione = direzione;
	}
	
	public ComandoVai(String parametro) {
		super(parametro);
	}

	@Override
	public void esegui(Partita partita) {
		this.direzione = super.getDirezione();		// ho cambiato questa riga in questo modo perchè ho cambiato il costruttore
		
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