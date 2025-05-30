package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{

	private static final String NOME = "prendi";
	private String nomeAttrezzo;
	
	public ComandoPrendi(String nomeAttrezzo) {
		super(NOME, nomeAttrezzo);
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	@Override
	public void esegui(Partita partita) {
		this.nomeAttrezzo = super.getParametro();		// stesso motivo scritto in ComandoVai
		
		// non viene specificato l'attrezzo ma viene chiamato solo il comando
		if(nomeAttrezzo == null) {
			super.getIO().mostraMessaggio("Quale attrezzo vuoi prendere? (Usa comando guarda per vedere gli attrezzi)");
			return;
		}
		
		// cerco l'attrezzo richiesto nella stanza...
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		// ... verifico che esista, se no...
		if(a!=null) {
			if(partita.getGiocatore().giocatoreAddAttrezzo(a)) {	// vedo se posso aggiungerlo, se no....
				partita.getStanzaCorrente().removeAttrezzo(a);		// rimuovo da stanza
				super.getIO().mostraMessaggio("Fatto!");
				return;
			}
			super.getIO().mostraMessaggio("Attrezzo troppo pesante");		// .... vuol dire che pesa troppo
			return;
		}
		// ... attrezzo non trovato
		super.getIO().mostraMessaggio("Attrezzo non trovato");
		return;		
	}

}
