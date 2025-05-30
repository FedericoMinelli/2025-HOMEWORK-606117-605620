package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.*;

class FdCFisarmonicaTest {		// FdC è l'abbreviazione di FabbricaDiComandi

	private AbstractComando comando;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetNome_Vai() {
		comando = new ComandoVai("nord");
		assertEquals("vai", comando.getNome());
	}
	@Test
	void testGetParametro_Vai() {
		comando = new ComandoVai("nord");
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	void testGetNome_Aiuto() {
		comando = new ComandoAiuto();
		assertEquals("aiuto", comando.getNome());
	}
	
	@Test
	void testGetNome_Fine() {
		comando = new ComandoFine();
		assertEquals("fine", comando.getNome());
	}
	
	@Test
	void testGetNome_Guarda() {
		comando = new ComandoGuarda();
		assertEquals("guarda", comando.getNome());
	}
	
	@Test
	void testGetNome_Prendi() {
		comando = new ComandoPrendi("osso");
		assertEquals("prendi", comando.getNome());
	}
	
	@Test
	void testGetParametro_Prendi() {
		comando = new ComandoPrendi("osso");
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	void testGetNome_Posa() {
		comando = new ComandoPosa("osso");
		assertEquals("posa", comando.getNome());
	}
	
	@Test
	void testGetParametro_Posa() {
		comando = new ComandoPosa("osso");
		assertEquals("osso", comando.getParametro());
	}

}
