package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio{

	private static final String MESSAGGIO_MORSO = "Aia, questo cane morde!";
	private String cibo;
	private Attrezzo attrezzoDelCane;
	
	public Cane(String nome, String present, String cibo, Attrezzo regalo) {
		super(nome, present);
		this.cibo = cibo;
		this.attrezzoDelCane = regalo;
	}

	@Override
	public String agisci(Partita partita) {
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu()-1);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Giocatore giocatore = partita.getGiocatore();
		if(attrezzo.getNome().equals(this.cibo)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoDelCane);
		}
		return null;
	}
}
