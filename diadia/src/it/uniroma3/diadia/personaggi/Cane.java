package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio{

	private static final String MESSAGGIO_MORSO = "Aia, questo cane morde!";
	private static final String MESSAGGIO_REGALO = "Bau Bau, ecco a te il tuo nuovo attrezzo";
	private static final String CIBO = "osso";
	private static final Attrezzo ATTREZZO_CANE = new Attrezzo("pallina", 1);
	private boolean attrezzoRegalato = false;

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
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
		if(!attrezzoRegalato && attrezzo.getNome().equals(CIBO)) {
			partita.getStanzaCorrente().addAttrezzo(ATTREZZO_CANE);
			giocatore.giocatoreRemoveAttrezzo(attrezzo);
			attrezzoRegalato = true;
			return MESSAGGIO_REGALO;
		}
		else {
			giocatore.setCfu(giocatore.getCfu()-1);
			return MESSAGGIO_MORSO;
		}
	}
	
	public static String getCibo() {
		return CIBO;
	}

	public static Attrezzo getAttrezzoCane() {
		return ATTREZZO_CANE;
	}
}
