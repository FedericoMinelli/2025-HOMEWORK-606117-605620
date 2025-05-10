package it.uniroma3.test.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class LabirintoTest {
	
	private Labirinto labirinto;
	@BeforeEach
	void setUp() throws Exception {
		this.labirinto = new Labirinto();
	}

	// verifico che la stanza iniziale sia quella giusta, cioè Atrio
	@Test
	void testGetStanzaIniziale() {
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
	}
	
	// verifico che la stanza finale sia quella giusta, cioè Biblioteca
	@Test
	void testGetStanzaFinale() {
		assertEquals("Biblioteca", this.labirinto.getStanzaFinale().getNome());
	}

}
