package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;



public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	/**
	 * Costruttori per classe Borsa
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; 
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Prova ad aggiungere 'attrezzo' alla borsa
	 * @param attrezzo
	 * @return false: quando aggiunta dell'attrezzo fa andare il peso totale sopra il valore max o la borsa è piena, true: altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		
		if (this.numeroAttrezzi==10)
			return false;
		
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		
		return true;
	}
	
	/**
	 * Getter di pesoMax
	 * @return pesoMax
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Metodo che restituisce l'attrezzo di nome 'nomeAttrezzo' se presente
	 * @param nomeAttrezzo
	 * @return attrezzo di nome passato come parametro formale, null se non viene trovato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}

	/**
	 * Metodo che somma il peso di tutti gli attrezzi presenti nella borsa
	 * @return peso attuale della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i=0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	/**
	 * Verifica se la borsa è vuota
	 * @return	true se la borsa è vuota, false altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Verifica se 
	 * @param nomeAttrezzo
	 * @return
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove un attrezzo dalla borsa (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return null se la borsa è vuota, l'attrezzo rimosso altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		
		if(!isEmpty()) {
			for(int i=0; i<this.numeroAttrezzi;i++) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					this.attrezzi[i]=a;
					for(int j=i; j<this.numeroAttrezzi-1;j++) {
						this.attrezzi[j]=this.attrezzi[j+1];
					}
					this.attrezzi[this.numeroAttrezzi-1]=null;
					this.numeroAttrezzi--;
					break;
				}
			}
		}

		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
