package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String[] righeDaLeggere;
	private int indiceRighe;
	private String[] Messaggi;
	private int indiceMessaggiP;
	private int indiceMessaggiM;

	public IOSimulator(String[] righeDaLeggere) {
		this.righeDaLeggere=righeDaLeggere;
		this.indiceRighe=0;
		this.Messaggi=new String[500];
		this.indiceMessaggiM=0;
		this.indiceMessaggiP=0;
	}
	@Override
	public void mostraMessaggio(String messaggio) {
		this.Messaggi[this.indiceMessaggiP]=messaggio;
		this.indiceMessaggiP++;
	}
	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere[this.indiceRighe];
		this.indiceRighe++;
		return rigaLetta;
	}
	public String nextMessaggio() {
		String next = this.Messaggi[this.indiceMessaggiM];
		this.indiceMessaggiM++;
		return next;

	}
	public Boolean HasMessaggio() {
		return this.indiceMessaggiP > this.indiceMessaggiM;
	}
}
