package it.uniroma3.test.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Strega;

public class StregaTest {
	private static final String STANZA_OVEST_1_ATTREZZO = "stanzaOvest1attrezzo";
	private static final String INIZIALE = "iniziale";
	private static final String STANZA_EST_2_ATTREZZI = "stanzaEst2Attrezzi";
	private Partita partita;
	private Strega strega;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale(INIZIALE)
				.addStanza(STANZA_EST_2_ATTREZZI)
				.addAttrezzo("attrezzo1", 1)
				.addAttrezzo("attrezzo2", 2)
				.addAdiacenza(INIZIALE, STANZA_EST_2_ATTREZZI, Direzioni.Est)
				.addStanza(STANZA_OVEST_1_ATTREZZO)
				.addAttrezzo("attrezzo3", 3)
				.addAdiacenza(INIZIALE, STANZA_OVEST_1_ATTREZZO, Direzioni.Ovest)
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.strega = new Strega("Morgana", "Presentazione!");
	}

	@Test
	public void testAgisci_SenzaSalutare() {
		this.strega.agisci(this.partita);
		assertEquals(STANZA_OVEST_1_ATTREZZO, this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testAgisci_Saluta() {
		this.strega.saluta();
		this.strega.agisci(this.partita);
		assertEquals(STANZA_EST_2_ATTREZZI, this.partita.getStanzaCorrente().getNome());
		
	}

	@Test
	public void testRiceviRegalo() {
		this.strega.riceviRegalo(new Attrezzo("test", 1), this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("test"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("test"));
	}
}
