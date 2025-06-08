package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO{
	
	
	private Scanner ScannerDiLinne;

	public IOConsole(Scanner scanner) {
		this.ScannerDiLinne=scanner;
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		 System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		//Scanner scannerDiLinee = new Scanner(System.in);
		String riga = ScannerDiLinne.nextLine();
		//ScannerDiLinne.close();		
		return riga;
	}
}
