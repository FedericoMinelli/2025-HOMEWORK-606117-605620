package it.uniroma3.test.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;

public class MagoTest {
	private Partita partita;
	private Mago mago;
	private Attrezzo regalo;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("iniziale")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.mago = new Mago("Merlino", "Presentazione!");
		regalo = mago.getRegalo();
	}

	@Test
	public void testAgisci() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.mago.agisci(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
	}
	
	@Test
	public void testAgisci_DueVolte() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.mago.agisci(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
		this.partita.getStanzaCorrente().removeAttrezzo(this.regalo);
		this.mago.agisci(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(this.regalo.getNome()));
	}

	@Test
	public void testRiceviRegalo() {
		Attrezzo daDimezzare = new Attrezzo("daDimezzare", 5);
		this.mago.riceviRegalo(daDimezzare, this.partita);
		assertEquals(2, this.partita.getStanzaCorrente().getAttrezzo(daDimezzare.getNome()).getPeso());
	}

}
