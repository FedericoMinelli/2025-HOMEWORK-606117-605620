package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.*;

class AbstractComandoTest {

	private AbstractComando comando;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testComandoAiuto() {
		comando = new ComandoAiuto();
		assertEquals("aiuto", comando.getNome());
	}
	
	@Test
	void testComandoFine() {
		comando = new ComandoFine();
		assertEquals("fine", comando.getNome());
	}
	
	@Test
	void testComandoGuarda() {
		comando = new ComandoGuarda();
		assertEquals("guarda", comando.getNome());
	}
	
	@Test
	void testComandoPosa() {
		comando = new ComandoPosa("osso");
		assertEquals("posa", comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	void testComandoPosa_CambioParametro() {
		comando = new ComandoPosa("osso");
		assertEquals("osso", comando.getParametro());
		comando.setParametro("clava");
		assertEquals("clava", comando.getParametro());
	}
	
	@Test
	void testComandoPrendi() {
		comando = new ComandoPrendi("osso");
		assertEquals("prendi", comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	void testComandoPrendi_CambioParametro() {
		comando = new ComandoPrendi("osso");
		assertEquals("osso", comando.getParametro());
		comando.setParametro("clava");
		assertEquals("clava", comando.getParametro());
	}
	
	@Test
	void testComandoVai() {
		comando = new ComandoVai("nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	void testComandoVai_CambioParametro() {
		comando = new ComandoVai("nord");
		assertEquals("nord", comando.getParametro());
		comando.setParametro("sud");
		assertEquals("sud", comando.getParametro());
	}

}
