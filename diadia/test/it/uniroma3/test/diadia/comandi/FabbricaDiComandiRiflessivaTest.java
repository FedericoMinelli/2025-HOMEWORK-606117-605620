package it.uniroma3.test.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

class FabbricaDiComandiRiflessivaTest {
	
	private FabbricaDiComandiRiflessiva fabbrica;
	private AbstractComando comando;
	
	
	
	@BeforeEach
	public void setUp() {
		fabbrica = new FabbricaDiComandiRiflessiva();
		comando = null;
	}
	
	
	// ComandoNonValido
	@Test
	void testComandoNonValidoNull()throws Exception{
		comando = fabbrica.costruisciComando("");
        assertEquals("non valido", comando.getNome());
        assertNull(comando.getParametro());
	}
	
	@Test
	void testComandoNonValidoSconosciuito()throws Exception{
		comando = fabbrica.costruisciComando("fdsgsdgfsdgfs");
        assertEquals("non valido", comando.getNome());
        assertNull(comando.getParametro());
	}
	
	@Test
	void testComandoNonValidoSconosciuitoConParametro() throws Exception{
		comando = fabbrica.costruisciComando("fdsgsdgfsdgfs nord");
        assertEquals("non valido", comando.getNome());
        assertNull(comando.getParametro());
	}
	
	// ComandoVai
	@Test
	public void testComandoVaiSenzaParametro() throws Exception {
		comando = fabbrica.costruisciComando("vai");
		assertEquals("vai", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoVaiConParametro() throws Exception {
		comando = fabbrica.costruisciComando("vai nord");
		assertEquals("vai", comando.getNome());
		assertEquals("nord" , comando.getParametro()); // ho lasciato nord come stringa perchè getParametro si aspetta una stringa
	}
	
	// ComandoPosa
	@Test
	public void testComandoPosaSenzaParametro() throws Exception {
		comando = fabbrica.costruisciComando("posa");
		assertEquals("posa", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoPosaConParametro() throws Exception {
		comando = fabbrica.costruisciComando("posa orso");
		assertEquals("posa", comando.getNome());
		assertEquals("orso", comando.getParametro());
	}
	
	// ComandoPrendi 
	@Test
	public void testComandoPrendiSenzaParametro() throws Exception {
		comando = fabbrica.costruisciComando("prendi");
		assertEquals("prendi", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoPrendiConParametro() throws Exception {
		comando = fabbrica.costruisciComando("prendi spada");
		assertEquals("prendi", comando.getNome());
		assertEquals("spada", comando.getParametro());
	}
	
	//ComandoAiuto
	@Test
	public void testComandoAiuto() throws Exception {
		comando = fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoAiutoConParametro() throws Exception {
		comando = fabbrica.costruisciComando("aiuto gianni");
		assertEquals("aiuto", comando.getNome());
	}

	//ComandoFine
	@Test 
	public void testComandoFine() throws Exception{
		comando = fabbrica.costruisciComando("fine");
		assertEquals("fine", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test 
	public void testComandoFineConParametro() throws Exception{
		comando = fabbrica.costruisciComando("fine basta");
		assertEquals("fine", comando.getNome());
		assertEquals("basta",comando.getParametro());
	}
	
	//ComandoGuarda
	@Test
	public void testComandoGuarda() throws Exception{
		comando = fabbrica.costruisciComando("guarda");
		assertEquals("guarda", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoGuardaConParametro() throws Exception{
		comando = fabbrica.costruisciComando("guarda gianni");
		assertEquals("guarda", comando.getNome());
		assertEquals("gianni",comando.getParametro());
	}

}
