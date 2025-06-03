package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando{

	private static final String NOME = "posa";
	private String nomeAttrezzo;

	public ComandoPosa() {		// stesso motivo scritto in ComandoVai
		this(null);
	}
	
	public ComandoPosa(String nomeAttrezzo) {
		super(NOME, nomeAttrezzo);
		this.nomeAttrezzo = nomeAttrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		this.nomeAttrezzo = super.getParametro();		// stesso motivo scritto in ComandoVai
		
		// non viene specificato l'attrezzo ma viene chiamato solo il comando
		if(nomeAttrezzo == null) {
			super.getIO().mostraMessaggio("Quale attrezzo vuoi posare? (Usa comando guarda per vedere gli attrezzi)");
			return;
		}

		// cerco l'attrezzo richiesto nella borsa...
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		// ... verifico che esista, se no...
		if(a!=null) {
			if(partita.getStanzaCorrente().addAttrezzo(a)) {		 // vedo se posso aggiungerlo, se no....
				partita.getGiocatore().giocatoreRemoveAttrezzo(a);   // rimuovo dalla borsa
				super.getIO().mostraMessaggio("Fatto!");
				return;
			}
			super.getIO().mostraMessaggio("Stanza al completo");		// .... vuol dire che la stanza è al completo
			return;
		}
		// ... attrezzo non trovato
		super.getIO().mostraMessaggio("Attrezzo non trovato");
		return;
	}

}
