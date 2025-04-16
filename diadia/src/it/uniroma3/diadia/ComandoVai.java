package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	private String direzione;
	
	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		// quando non viene specificata la direzione
		if(direzione == null) {
			System.out.println("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		// controllo se esiste la stanza in cui si desidera andare
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			System.out.println("Direzione inesistente");
			return;
		}
		
		// se esiste il giocatore si sposta
		partita.setStanzaCorrente(prossimaStanza);
		System.out.println(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	
}
