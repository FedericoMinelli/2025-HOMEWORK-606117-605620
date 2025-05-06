package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{

	private String attrezzochiave;

	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.attrezzochiave=attrezzo;
	}

	@Override
	public String getDescrizione() {
		if(!(this.hasAttrezzo(this.attrezzochiave))) {
			return "qui c'è buio pesto";
		}
		return super.getDescrizione();
	}
}
