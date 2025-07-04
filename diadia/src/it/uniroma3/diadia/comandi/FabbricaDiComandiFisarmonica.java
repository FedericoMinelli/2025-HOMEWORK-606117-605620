package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzioni;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	
	@Override
	public AbstractComando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();   // seconda parola: eventuale parametro
		
		if (nomeComando == null) 
			comando = new ComandoNonValido();
		
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai(Direzioni.valueOf(parametro));
		
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(parametro);
		
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(parametro);
		
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		
		else 
			comando = new ComandoNonValido();
		
		comando.setParametro(parametro);
		return comando;
	}            
}

