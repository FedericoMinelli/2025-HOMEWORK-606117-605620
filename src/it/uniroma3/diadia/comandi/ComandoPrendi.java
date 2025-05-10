package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{

	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	public ComandoPrendi(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;				
	}
	
	@Override
	public void esegui(Partita partita) {
		
		// non viene specificato l'attrezzo ma viene chiamato solo il comando
		if(nomeAttrezzo == null) {
			io.mostraMessaggio("Quale attrezzo vuoi prendere? (Usa comando guarda per vedere gli attrezzi)");
			return;
		}
		
		// cerco l'attrezzo richiesto nella stanza...
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		// ... verifico che esista, se no...
		if(a!=null) {
			if(partita.getGiocatore().giocatoreAddAttrezzo(a)) {	// vedo se posso aggiungerlo, se no....
				partita.getStanzaCorrente().removeAttrezzo(a);		// rimuovo da stanza
				io.mostraMessaggio("Fatto!");
				return;
			}
			io.mostraMessaggio("Attrezzo troppo pesante");		// .... vuol dire che pesa troppo
			return;
		}
		// ... attrezzo non trovato
		io.mostraMessaggio("Attrezzo non trovato");
		return;		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;	
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

}
