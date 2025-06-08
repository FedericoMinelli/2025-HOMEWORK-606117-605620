package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzioni;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche e  */
	private static final String MAGICHE_MARKER = "Magiche: ";
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie */
	private static final String BUIE_MARKER = "Buie: ";
	
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate */
	private static final String BLOCCATE_MARKER = "Bloccate: ";
	
	/* prefisso della riga contenente il nome e altre caratteristiche del mago */
	private static final String MAGO_MARKER = "Mago: ";
	
	/* prefisso della riga contenente il nome e altre caratteristiche della strega */
	private static final String STREGA_MARKER = "Strega: ";
	
	/* prefisso della riga contenente il nome e altre caratteristiche del cane */
	private static final String CANE_MARKER = "Cane: ";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;		// è un BufferedReader

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		// qui sopra un BufferedReader che "avvolge" un altro reader, in questo caso da file,
		// che da solo leggerebbere carattere per carattere, il BufferedReader velocizza 
		// la lettura e minimizza accessi in memoria
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaBloccate();
			this.leggiECreaBuie();			
			this.leggiECreaMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaCane();
			this.leggiECollocaMago();
			this.leggiECollocaStrega();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();		// legge e salva dentro riga tutta la prima riga salvata dentro il reader fino a \n, poi passa il cursore alla riga dopo, simile al next() di Scanner
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);	// controlla se inizia con la parola/carattere marker
			return riga.substring(marker.length());		// ritorna tutto cio che sta dopo il marker
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {		// nome autoesplicativo
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);		// aggiunge una ad una le stanze del labirinto nella mappa
		}
	}
	
	private void leggiECreaMagiche() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);		
		}
	}
	
	private void leggiECreaBuie() throws FormatoFileNonValidoException {
		String specificheStanzaBuia = this.leggiRigaCheCominciaPer(BUIE_MARKER);
		
		for(String specifiche : separaStringheAlleVirgole(specificheStanzaBuia)) {
			String nomeStanza = null;
			String attrezzoIlluminante = null;
			try (Scanner scannerDiLinea = new Scanner(specifiche)){
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza buia."));
				nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo che illumina."));
				attrezzoIlluminante = scannerDiLinea.next();				
			}
			this.nome2stanza.put(nomeStanza, new StanzaBuia(nomeStanza, attrezzoIlluminante));
		}
	}
	
	private void leggiECreaBloccate() throws FormatoFileNonValidoException {
		String specificheStanzaBloccate = this.leggiRigaCheCominciaPer(BLOCCATE_MARKER);
		
		for(String specifiche : separaStringheAlleVirgole(specificheStanzaBloccate)) {
			String nomeStanza = null;
			String direzione = null;
			String attrezzoSblocca = null;
			try (Scanner scannerDiLinea = new Scanner(specifiche)){
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome della stanza bloccata."));
				nomeStanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la direzione della stanza bloccata."));
				direzione = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo che sblocca."));
				attrezzoSblocca = scannerDiLinea.next();				
			}
			this.nome2stanza.put(nomeStanza, new StanzaBloccata(nomeStanza, Direzioni.valueOf(direzione), attrezzoSblocca));
		}
	}
	
	/**
	 * 
	 * @param riga letta dal metodo leggiRigaCheCominciaPer(String)
	 * @return	lista di stringhe contenente tutte le parole della stringa passata come argomento senza virgole
	 */
	private List<String> separaStringheAlleVirgole(String string) {	
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {		// serve per chiudere lo scanner, vedi try-with-resources
			while(scannerDiParole.hasNext()) {			// prima veniva salvata solo la parte precedente alla prima virgola
				result.add(scannerDiParole.next());
			}		
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {	// prova a leggere da file stanza iniziale e vincente, se va a buon fine la lettura le salva nelle rispettive variabili
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}
	
	private boolean isStanzaValida(String nomeStanza) {			// controlla se la stanza appartiene alle stanza salvate nella mappa delle stanze lette in input
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String uscite : this.separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(uscite)) {			 
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();		// prima parte di stringa letta è la stanza, ora la salvo
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();   // seconda parte di stringa è la direzione, se check va a buon fine salvo direzione
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();	// terza parte di stringa è la destinazione, se check va a buon fine salvo uscita e la corrispondente direzione
	
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);		// imposto una ad una le uscite con tutti i parametri salvati in precendenza
				}
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		
		partenzaDa.impostaStanzaAdiacente(Direzioni.valueOf(dir), arrivoA);		// valueOf è un metodo statico ereditato da java.lang.Enum che restituisce un istanza di un enum passando come parametro la stringa contente in suo nome preciso
	}
	
	private void leggiECollocaCane() throws FormatoFileNonValidoException {
		String specificheCane = this.leggiRigaCheCominciaPer(CANE_MARKER);
		for(String specCane : this.separaStringheAlleVirgole(specificheCane)) {
			String nomeCane = null;
			String stanza = null;
			String presentazione = null;
			try(Scanner scannerDiLinea = new Scanner(specCane)){
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome del cane"));
				nomeCane = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la stanza di "+nomeCane));
				stanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la presentazione di "+nomeCane));
				presentazione = scannerDiLinea.nextLine();		// per avere una presentazione di piu parole
			}
			aggiungiPersonaggio(new Cane(nomeCane, presentazione), stanza);
			
		}
	}
	
	private void leggiECollocaMago() throws FormatoFileNonValidoException {
		String specificheMago = this.leggiRigaCheCominciaPer(MAGO_MARKER);
		for(String specMago : this.separaStringheAlleVirgole(specificheMago)) {
			String nomeMago = null;
			String stanza = null;
			String presentazione = null;
			try(Scanner scannerDiLinea = new Scanner(specMago)){
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome del cane"));
				nomeMago = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la stanza di "+nomeMago));
				stanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la presentazione di "+nomeMago));
				presentazione = scannerDiLinea.nextLine();		// guarda metodo per cane
			}
			aggiungiPersonaggio(new Mago(nomeMago, presentazione), stanza);
			
		}
	}
	
	private void leggiECollocaStrega() throws FormatoFileNonValidoException {
		String specificheStrega = this.leggiRigaCheCominciaPer(STREGA_MARKER);
		for(String specStrega : this.separaStringheAlleVirgole(specificheStrega)) {
			String nomeStrega = null;
			String stanza = null;
			String presentazione = null;
			try(Scanner scannerDiLinea = new Scanner(specStrega)){
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("il nome del cane"));
				nomeStrega = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la stanza di "+nomeStrega));
				stanza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la presentazione di "+nomeStrega));
				presentazione = scannerDiLinea.nextLine();		// guarda metodo per cane
			}
			aggiungiPersonaggio(new Strega(nomeStrega, presentazione), stanza);
			
		}
	}
	
	private void aggiungiPersonaggio(AbstractPersonaggio personaggio, String stanza) {
		this.nome2stanza.get(stanza).setPersonaggio(personaggio);
	}

	// se la condizione booleana passata come primo parametro è vera non fa nulla, altrimenti lancia l'eccezione
	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
