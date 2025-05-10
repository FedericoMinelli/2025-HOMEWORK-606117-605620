package it.uniroma3.test.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

class IOSimulatorTest {

	private IOSimulator io;
	private String[] comandi;

	@Test
	void test_SimulazioneVinta() {
		this.comandi = new String[] {"vai sud", "vai nord", "vai nord"};
		this.io = new IOSimulator(comandi);
		
		DiaDia gioco = new DiaDia(io);		// avvio una partita simulata 
		gioco.gioca();
		
		// vado a cercare l'ultimo messaggio che è quello
		// che mi fa capire come si è concluso il gioco
		String messFinale = new String();		
		while(io.hasMessaggio()) {
			messFinale = io.nextMessaggio();
		}
		
		assertEquals("Hai vinto!", messFinale);
	}
	
	@Test
	void test_SimulazionePersaConComandoFine() {
		this.comandi = new String[] {"vai sud", "vai nord", "prendi", "guarda", "prendi osso", "fine"};
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
		this.comandi = new String[] {"vai sud", "vai nord", "vai sud", "vai nord", "vai sud", 
									"vai nord", "vai sud", "vai nord", "vai sud", "vai nord",
									"vai sud", "vai nord", "vai sud", "vai nord", "vai sud",
									"vai nord", "vai sud", "vai nord", "vai sud", "vai nord"};
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
