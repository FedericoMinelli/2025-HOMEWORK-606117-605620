package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{
	
	private static final String REGALA = "regala attrezzo";		// questo dovrebbe essere il nome del comando, perche l'hai scritto cosi?
	private static final String MEESSAGGIO_A_CHI = "a chi vorresti regalare l'attrezzo?";
	private Attrezzo attrezzo;

	
	public ComandoRegala() {		// stesso motivo scritto in ComandoVai
		this(null);
	}
	
	public ComandoRegala(Attrezzo attrezzo) {
		super(REGALA,attrezzo.getNome());
		this.attrezzo = attrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio==null) {
			super.getIO().mostraMessaggio(MEESSAGGIO_A_CHI);
			return;
		}
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		personaggio.riceviRegalo(attrezzo, partita);
		partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
		super.getIO().mostraMessaggio("attrezzo regalato");
		
			
		
		
	}
	
	
	
}
