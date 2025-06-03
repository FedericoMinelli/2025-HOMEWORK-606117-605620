package it.uniroma3.diadia.personaggi;

import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Strega extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_NEGATIVO = "Sei un maleducato! Ora andrai nella stanza piu vuota!";
	private static final String MESSAGGIO_POSITIVO = "Ma che gentile che sei! Ora andrai nella stanza piu rifornita";
	private static final String MESSAGGIO_REGALO = "ah ah ah questo me lo tengo io ahahahahahahahaha";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	/* la strega ti sposta nella stanza adiacente con meno attrezzi presente se non 
	 * è stata salutata, se no nella stanza adicente con piu attrezzi */
	@Override
	public String agisci(Partita partita) {
		
		Map<String, Stanza> mappa = partita.getStanzaCorrente().getMapStanzeAdiacenti();
		Stanza nuovaStanza = null;
		int attrezziPresenti = -1;
		
		if(this.haSalutato()) {		// la strega sposta il giocatore nella stanza con piu attrezzi
			for(String direzione : mappa.keySet()) {
				if(mappa.get(direzione).numeroAttrezzi() > attrezziPresenti) {
					attrezziPresenti = mappa.get(direzione).numeroAttrezzi();
					nuovaStanza = mappa.get(direzione);
				}	
			}
			partita.setStanzaCorrente(nuovaStanza);
			return MESSAGGIO_POSITIVO;
		}
		else {		// altrimenti in quella con meno attrezzi
			attrezziPresenti = 11;  // al massimo in una stanza possono esserci 10 attrezzi, mettendo 11 alla prima iterazione del for viene salva la prima stanza su cui si itera
			for(String direzione : mappa.keySet()) {
				if(mappa.get(direzione).numeroAttrezzi() < attrezziPresenti) {
					attrezziPresenti = mappa.get(direzione).numeroAttrezzi();
					nuovaStanza = mappa.get(direzione);
				}
			}
			partita.setStanzaCorrente(nuovaStanza);
			return MESSAGGIO_NEGATIVO;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Giocatore giocatore = partita.getGiocatore();
		partita.getStanzaCorrente().removeAttrezzo(attrezzo);
		return MESSAGGIO_REGALO;
	}
}
