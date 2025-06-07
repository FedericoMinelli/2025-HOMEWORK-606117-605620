package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private Direzioni direzioneBloccata;
	private String attrezzoSbloccante;
	

	public StanzaBloccata(String nome, Direzioni dir, String attrezzo) {
		super(nome);
		this.direzioneBloccata = dir;
		this.attrezzoSbloccante = attrezzo;
		
	}

	@Override
	public Stanza getStanzaAdiacente(Direzioni direzione) {
		if(direzioneBloccata.equals(direzione)) {
			if(this.hasAttrezzo(attrezzoSbloccante)) {
				return super.getStanzaAdiacente(direzione);
			}
			return this;	// se direzione è ancora bloccata ritorna un riferimento alla stanza corrente
		}
		return super.getStanzaAdiacente(direzione);

	}

	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(attrezzoSbloccante)) {
			return "La direzione " + this.getDirezioneBloccata() + " è bloccata\n" + super.getDescrizione();
		}
		return super.getDescrizione();
	}

	// prediletto l'accesso tramite il metodo
	public Direzioni getDirezioneBloccata() {
		return direzioneBloccata;
	}

	// probabilmente inutile
	public String getAttrezzoSbloccante() {
		return attrezzoSbloccante;
	}
}
