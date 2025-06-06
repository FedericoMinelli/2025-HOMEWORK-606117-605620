package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;

public abstract class AbstractComando {

	private final String nomeComando;
	private String parametro;
	private IO io;		// da impostare tramite il setIO manualmente nei test o dove e quando richiesto
	private Direzioni direzione;
	
	public AbstractComando(String nomeComando, String parametro) {
		this.nomeComando = nomeComando;
		this.parametro = parametro;
	}
	// costruttore senza parametro per comandi che non hanno bisogno di un parametro
	public AbstractComando(String nomeComando) {
		this.nomeComando = nomeComando;
	}
	
	public AbstractComando(String nomeComando, Direzioni direzione) {
		this.nomeComando=nomeComando;
		this.direzione = direzione;
	}
	public String getNome() {
		return this.nomeComando;
	}
	
	public void setIO(IO io) {
		this.io = io;
	}
	public IO getIO() {
		return this.io;
	}
	
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public Direzioni getDirezione() {
		return this.direzione;
	}
	public void setDirezione(Direzioni direzione) {
		this.direzione = direzione;
	}
	
	public abstract void esegui(Partita partita);

}