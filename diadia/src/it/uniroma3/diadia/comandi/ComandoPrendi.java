package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{

	private String nomeAttrezzo;
	
	public ComandoPrendi(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;				
	}
	
	@Override
	public void esegui(Partita partita) {
		
		// non viene specificato l'attrezzo ma viene chiamato solo il comando
		if(nomeAttrezzo == null) {
			System.out.println("Quale attrezzo vuoi prendere? (Usa comando guarda per vedere gli attrezzi)");
			return;
		}
		
		// cerco l'attrezzo richiesto nella stanza...
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		// ... verifico che esista, se no...
		if(a!=null) {
			if(partita.getGiocatore().giocatoreAddAttrezzo(a)) {	// vedo se posso aggiungerlo, se no....
				partita.getStanzaCorrente().removeAttrezzo(a);		// rimuovo da stanza
				System.out.println("Fatto!");
				return;
			}
			System.out.println("Attrezzo troppo pesante");		// .... vuol dire che pesa troppo
			return;
		}
		// ... attrezzo non trovato
		System.out.println("Attrezzo non trovato");
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
