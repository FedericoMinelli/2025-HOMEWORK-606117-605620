package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{

	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;				
	}

	@Override
	public void esegui(Partita partita) {

		// non viene specificato l'attrezzo ma viene chiamato solo il comando
		if(nomeAttrezzo == null) {
			io.mostraMessaggio("Quale attrezzo vuoi posare? (Usa comando guarda per vedere gli attrezzi)");
			return;
		}

		// cerco l'attrezzo richiesto nella borsa...
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		// ... verifico che esista, se no...
		if(a!=null) {
			if(partita.getStanzaCorrente().addAttrezzo(a)) {		 // vedo se posso aggiungerlo, se no....
				partita.getGiocatore().giocatoreRemoveAttrezzo(a);   // rimuovo dalla borsa
				io.mostraMessaggio("Fatto!");
				return;
			}
			io.mostraMessaggio("Stanza al completo");		// .... vuol dire che la stanza è al completo
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
		return "posa";
	}

}
