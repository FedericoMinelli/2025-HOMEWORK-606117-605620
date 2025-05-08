package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, String dir, String attrezzo) {
		super(nome);
		this.direzioneBloccata = dir;
		this.attrezzoSbloccante = attrezzo;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
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
		if(!(this.hasAttrezzo(attrezzoSbloccante))) {
			System.out.println("La direzione " + this.getDirezioneBloccata() + " è bloccata");
		}
		return super.getDescrizione();
	}

	// prediletto l'accesso tramite il metodo
	public String getDirezioneBloccata() {
		return direzioneBloccata;
	}

	// probabilmente inutile
	public String getAttrezzoSbloccante() {
		return attrezzoSbloccante;
	}
}
