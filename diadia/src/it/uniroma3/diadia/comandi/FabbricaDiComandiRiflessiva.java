package it.uniroma3.diadia.comandi;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	public AbstractComando costruisciComando(String istruzione){
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		String nomeComando = null; // es. ‘vai’
		String parametro = null;   // es. ‘sud’
		AbstractComando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		if(nomeComando == null) {
			comando = new ComandoNonValido();
			return comando;
		}
			
		try {
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) ); 
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
			nomeClasse.append( nomeComando.substring(1) ) ;
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
			comando = (AbstractComando) Class.forName(nomeClasse.toString())
	                .getDeclaredConstructor() 	// restituisce il costruttore no-args
	                .newInstance();
			comando.setParametro(parametro);
		} catch(ClassNotFoundException e) {
			comando = new ComandoNonValido();
		} catch(NoSuchMethodException e) {
			comando = new ComandoNonValido();
		} catch(InvocationTargetException e) {
			comando = new ComandoNonValido();
		} catch(IllegalAccessException e) {
			comando = new ComandoNonValido();
		} catch(InstantiationException e) {
			comando = new ComandoNonValido();
		} 
		return comando;
	}  
}

