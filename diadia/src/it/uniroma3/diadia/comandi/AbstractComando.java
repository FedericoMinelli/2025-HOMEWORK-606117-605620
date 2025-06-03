package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {

	private final String nomeComando;
	private String parametro;
	private IO io;		// da impostare tramite il setIO manualmente nei test o dove e quando richiesto
	
	public AbstractComando(String nomeComando, String parametro) {
		this.nomeComando = nomeComando;
		this.parametro = parametro;
	}
	// costruttore senza parametro per comandi che non hanno bisogno di un parametro
	public AbstractComando(String nomeComando) {
		this.nomeComando = nomeComando;
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
	
	public abstract void esegui(Partita partita);

}