package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{

	private String nomeAttrezzo;

	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;				
	}

	@Override
	public void esegui(Partita partita) {

		// non viene specificato l'attrezzo ma viene chiamato solo il comando
		if(nomeAttrezzo == null) {
			System.out.println("Quale attrezzo vuoi posare? (Usa comando guarda per vedere gli attrezzi)");
			return;
		}

		// cerco l'attrezzo richiesto nella borsa...
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		// ... verifico che esista, se no...
		if(a!=null) {
			if(partita.getStanzaCorrente().addAttrezzo(a)) {		 // vedo se posso aggiungerlo, se no....
				partita.getGiocatore().giocatoreRemoveAttrezzo(a);   // rimuovo dalla borsa
				System.out.println("Fatto!");
				return;
			}
			System.out.println("Stanza al completo");		// .... vuol dire che la stanza è al completo
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
		return "posa";
	}

}
