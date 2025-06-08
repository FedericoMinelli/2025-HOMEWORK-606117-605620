package it.uniroma3.test.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.Cane;

public class CaneTest {
	private Cane cane;
	private Partita partita;
	private Attrezzo regalo = Cane.getAttrezzoCane();
	private Attrezzo ciboPreferito = new Attrezzo(Cane.getCibo(), 1);

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("iniziale")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.cane = new Cane("Cane", "Presentazione!");
	}

	@Test
	public void testAgisci() {
		assertEquals(20, this.partita.getGiocatore().getCfu());
		this.cane.agisci(this.partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}

	@Test
	public void testRiceviRegalo_CiboCorretto() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		borsa.addAttrezzo(ciboPreferito);
		this.cane.riceviRegalo(this.ciboPreferito, this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		assertFalse(borsa.hasAttrezzo(ciboPreferito.getNome()));
	}
	
	@Test
	public void testRiceviRegalo_Sbagliato() {
		assertEquals(20, this.partita.getGiocatore().getCfu());
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		borsa.addAttrezzo(ciboPreferito);
		this.cane.riceviRegalo(new Attrezzo("CiboSbagliato", 2), this.partita);
		assertEquals(19, this.partita.getGiocatore().getCfu());
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		assertTrue(borsa.hasAttrezzo(ciboPreferito.getNome()));
	}
	
	@Test
	public void testRiceviRegalo_DueVolte() {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		
		assertFalse(stanzaCorrente.hasAttrezzo(this.regalo.getNome()));
		this.cane.riceviRegalo(this.ciboPreferito, this.partita);
		assertTrue(stanzaCorrente.hasAttrezzo(this.regalo.getNome()));
		stanzaCorrente.removeAttrezzo(this.regalo);
		this.cane.riceviRegalo(this.ciboPreferito, this.partita);
		assertFalse(stanzaCorrente.hasAttrezzo(this.regalo.getNome()));
	}
}
