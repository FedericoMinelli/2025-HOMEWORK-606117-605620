package it.uniroma3.diadia.giocatore;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Borsa {
	private static final int Peso_massimo_default = 10;
	private List <Attrezzo> attrezzi;	// da trasformare in una mappa del tipo Map<String, Attrezzo>
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
	
	// da modifica usando una mappa di attrezzi
	public int getPeso() {
		int pesototale=0;
		for(Attrezzo attrezzo : attrezzi) {
			pesototale += attrezzo.getPeso();
		}
		return pesototale;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo.getPeso()+this.getPeso()<=this.pesoMax) {
			return this.attrezzi.add(attrezzo);
		}
		return false;
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List <Attrezzo> risultato = new ArrayList<>(this.getAttrezzi());
		Collections.sort(risultato, new ComparatorePerNomeEPeso());
		return risultato;
	}
	
//	public List<Attrezzo> getContenutoOrdinatoPerNome(){
//		final List <Attrezzo> risultato = new ArrayList<>(this.getAttrezzi());
//		Collections.sort(risultato);
//		return risultato;
//	}
	
	private List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
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
	// da modifica usando una mappa di attrezzi
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (Attrezzo b:attrezzi)
			if (b.getNome().equals(nomeAttrezzo))
				a = b;

		return a;
	}

	/**
	 * Metodo che somma il peso di tutti gli attrezzi presenti nella borsa
	 * @return peso attuale della borsa
	 */
	
	// da modifica usando una mappa di attrezzi
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		while(it.hasNext()) {
			a=it.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				it.remove();
				return a;
			}
		}

		return null;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a:attrezzi) {
				s.append(a.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	



}
