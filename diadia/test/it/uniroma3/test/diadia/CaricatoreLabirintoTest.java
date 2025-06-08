package it.uniroma3.test.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

class CaricatoreLabirintoTest {

	private CaricatoreLabirinto cl;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto("labirinto1.txt");
		cl.carica();
		assertEquals("atrio", this.cl.getStanzaIniziale().getNome());
		assertEquals("atrio", this.cl.getStanzaVincente().getNome());
	}

	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto("labirinto2.txt");
		cl.carica();
		assertEquals("atrio", this.cl.getStanzaIniziale().getNome());
		assertEquals("uscita", this.cl.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMultilocaleConStanzaBuia_PrimaSenzaAttrezzoPoiCon() throws FileNotFoundException, FormatoFileNonValidoException {
		cl = new CaricatoreLabirinto("labirinto3.txt");
		cl.carica();
		Stanza buia = this.cl.getStanzaIniziale().getStanzaAdiacente(Direzioni.Nord);
		assertEquals("qui c'è buio pesto", buia.getDescrizione());
		buia.addAttrezzo(new Attrezzo("torcia", 3));
		assertFalse(buia.getDescrizione().equals("qui c'è buio pesto"));
	}
	
	@Test
	public void testMultilocaleConStanzaBloccata_PrimaSenzaAttrezzoPoiCon() throws FileNotFoundException, FormatoFileNonValidoException {
		cl = new CaricatoreLabirinto("labirinto3.txt");
		cl.carica();
		Stanza bloccata = this.cl.getStanzaIniziale().getStanzaAdiacente(Direzioni.Sud);
		assertSame(bloccata, bloccata.getStanzaAdiacente(Direzioni.Nord));
		bloccata.addAttrezzo(new Attrezzo("chiave", 3));
		assertSame(this.cl.getStanzaIniziale(), bloccata.getStanzaAdiacente(Direzioni.Nord));
	}
	
	@Test
	public void testMultilocaleConStanzaMagica_IsMagica() throws FileNotFoundException, FormatoFileNonValidoException{
		cl = new CaricatoreLabirinto("labirinto3.txt");
		cl.carica();
		assertTrue(this.cl.getStanzaIniziale().getStanzaAdiacente(Direzioni.Est).isMagica());
	}
	
	@Test
	public void testMultilocalePersonaggi_Cane() throws FileNotFoundException, FormatoFileNonValidoException{
		cl = new CaricatoreLabirinto("labirinto4.txt");
		cl.carica();
		AbstractPersonaggio personaggio = cl.getStanzaIniziale().getPersonaggio();
		assertEquals(personaggio.saluta(), "Ciao, io sono "+personaggio.getNome()+"."+personaggio.getPresentazione());
	}
	
	@Test
	public void testMultilocalePersonaggi_Mago() throws FileNotFoundException, FormatoFileNonValidoException{
		cl = new CaricatoreLabirinto("labirinto4.txt");
		cl.carica();
		AbstractPersonaggio personaggio = cl.getStanzaIniziale().getStanzaAdiacente(Direzioni.Sud).getPersonaggio();
		assertEquals(personaggio.saluta(), "Ciao, io sono "+personaggio.getNome()+"."+personaggio.getPresentazione());
	}
	
	@Test
	public void testMultilocalePersonaggi_Strega() throws FileNotFoundException, FormatoFileNonValidoException{
		cl = new CaricatoreLabirinto("labirinto4.txt");
		cl.carica();
		AbstractPersonaggio personaggio = cl.getStanzaIniziale().getStanzaAdiacente(Direzioni.Nord).getPersonaggio();
		assertEquals(personaggio.saluta(), "Ciao, io sono "+personaggio.getNome()+"."+personaggio.getPresentazione());
	}


}
