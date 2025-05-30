package it.uniroma3.diadia.giocatore;


import java.util.ArrayList;
import java.util.Collection;
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
	private Map<String, Attrezzo> attrezzi;	// da trasformare in una mappa del tipo Map<String, Attrezzo>
	private int pesoMax;
	
	

	public Borsa() {
		this(Peso_massimo_default);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi= new HashMap<String, Attrezzo>();
		
		
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
		for(Attrezzo attrezzo :  attrezzi.values()) {
			pesototale += attrezzo.getPeso();
		}
		return pesototale;
	}
	
	public Attrezzo addAttrezzo(String nome,Attrezzo attrezzo) {
		if(attrezzo.getPeso()+this.getPeso()<=this.pesoMax) {
			this.attrezzi.put(nome, attrezzo);		// put ritorna il valore associato precedentemente alla chiave, quindi null se l'attrezzo non era presente
			return attrezzo;			// voglio però che ritorni l'attrezzo appena aggiunto
		}
		return null;			// se non lo puo fare allora ritorna null
	}
	
	public Attrezzo addAttrezzo(Attrezzo attrezzo) {
		return addAttrezzo(attrezzo.getNome(), attrezzo);
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List <Attrezzo> risultato = new ArrayList<>(this.getAttrezzi());
		Collections.sort(risultato, new ComparatorePerPesoENome());
		return risultato;
	}
	
	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<>(this.getAttrezzi());

	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ordinati = new TreeSet<>(new ComparatorePerPesoENome());
		ordinati.addAll(this.getAttrezzi());
		return ordinati;
	}
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2attrezzi = new HashMap<>();
		for(Attrezzo attrezzo : attrezzi.values()) {
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
	public boolean hasAttrezzo(String nome) {
		return this.attrezzi.containsKey(nome);
	}
	
	public Attrezzo getAttrezzo(String nome) {
		return this.attrezzi.get(nome);
	}

	
	
	public Attrezzo removeAttrezzo(String nome) {
		return this.attrezzi.remove(nome);
	}
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a:attrezzi.values()) {
				s.append(a.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	



}
