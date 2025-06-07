package it.uniroma3.test.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Labirinto;

class IOSimulatorTest {

	
	private ArrayList<String> comandi;
	private IOSimulator io;

	@Test
	void test_SimulazioneVinta() {
		this.comandi = new ArrayList<>(List.of("vai ovest"));
		this.io = new IOSimulator(comandi);

		Labirinto labirinto = Labirinto.newBuilder()
			.addStanzaIniziale("LabCampusOne")
			.addStanzaVincente("Biblioteca")
			.addAdiacenza("LabCampusOne", "Biblioteca", Direzioni.Ovest)
			.getLabirinto();

		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();

		boolean trovatoMessaggioVittoria = false;
		String ultimo = "";

		while(io.hasMessaggio()) {
			String msg = io.nextMessaggio();
			if (msg.equals("Hai vinto!"))
				trovatoMessaggioVittoria = true;
			ultimo = msg;
		}

		assertTrue(trovatoMessaggioVittoria, "Non è stato stampato il messaggio di vittoria");
		assertEquals("Grazie di aver giocato!", ultimo);
	}
	
	
	@Test
	void test_SimulazionePersaConComandoFine() {
		this.comandi = new ArrayList<>(List.of("vai sud", "vai nord", "prendi", "guarda", "prendi osso", "fine"));
		this.io = new IOSimulator(comandi);
		
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		// vado a cercare l'ultimo messaggio che è quello
		// che mi fa capire come si è concluso il gioco
		String messFinale = new String();		
		while(io.hasMessaggio()) {
			messFinale = io.nextMessaggio();
		}
		
		assertEquals("Grazie di aver giocato!", messFinale);
	}
	
	@Test
	void test_SimulazionePersaPerCFU() {
		this.comandi = new ArrayList<>(List.of("vai sud", "vai nord", "vai sud", "vai nord", "vai sud", 
									"vai nord", "vai sud", "vai nord", "vai sud", "vai nord",
									"vai sud", "vai nord", "vai sud", "vai nord", "vai sud",
									"vai nord", "vai sud", "vai nord", "vai sud", "vai nord"));
		this.io = new IOSimulator(comandi);
		
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		// vado a cercare l'ultimo messaggio che è quello
		// che mi fa capire come si è concluso il gioco
		String messFinale = new String();		
		while(io.hasMessaggio()) {
			messFinale = io.nextMessaggio();
		}
		
		assertEquals("CFU esauriti, hai perso...", messFinale);
	}

}
