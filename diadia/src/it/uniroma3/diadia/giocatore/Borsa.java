package it.uniroma3.diadia.giocatore;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Borsa {
	private static final int Peso_massimo_default = 10;
	private List <Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(Peso_massimo_default);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi= new ArrayList<>();
	}

	/**
	 * Getter di pesoMax
	 * @return pesoMax
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	public int getPeso() {
		int pesototale=0;
		for(Attrezzo attrezzo : attrezzi) {
			pesototale += attrezzo.getPeso();
		}
		return pesototale;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.add(attrezzo);
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List <Attrezzo> risultato = new ArrayList<>(this.getAttrezzi());
		Collections.sort(risultato, new ComparatorePerNomeEPeso());
		return risultato;
	}
	public List<Attrezzo> getContenutoOrdinatoPerNome(){
		final List <Attrezzo> risultato = new ArrayList<>(this.getAttrezzi());
		Collections.sort(risultato);
		return risultato;
	}
	private List<Attrezzo> getAttrezzi() {
		// TODO Auto-generated method stub
		return this.attrezzi;
	}
	public SortedSet<Attrezzo> getContenutoOrdinatoNome(){
		return new TreeSet<>(this.getAttrezzi());

	}
	public int getNumeroAttrezzi() {
		
		return this.attrezzi.size();
	}
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2attrezzi = new HashMap<>();
		for(Attrezzo attrezzo : attrezzi) {
			final int peso = attrezzo.getPeso();
			Set<Attrezzo> attrezziStessoPeso = peso2attrezzi.get(peso);
			if(attrezziStessoPeso != null) {
				attrezziStessoPeso.add(attrezzo);
			}
			else {
				attrezziStessoPeso = new HashSet<>();
				attrezziStessoPeso.add(attrezzo);
				peso2attrezzi.put(peso, attrezziStessoPeso);
			}
		}
		return peso2attrezzi;
	}
	public boolean isEmpty() {
		return this.getNumeroAttrezzi() == 0;
	}
	
	/**
	 * Verifica se 
	 * @param nomeAttrezzo
	 * @return
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i<this.getNumeroAttrezzi(); i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}

	/**
	 * Metodo che somma il peso di tutti gli attrezzi presenti nella borsa
	 * @return peso attuale della borsa
	 */
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		
		if(!isEmpty()) {
			for(int i=0; i<this.getNumeroAttrezzi();i++) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a=this.attrezzi[i];    // prima era this.attrezzi[i]=a  però con il test dava problemi
					for(int j=i; j<this.getNumeroAttrezzi()-1;j++) {
						this.attrezzi[j]=this.attrezzi[j+1];
					}
					this.attrezzi[this.getNumeroAttrezzi()-1]=null;
					this.attrezzi--;
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
			for (int i= 0; i<this.attrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	



}
