package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Start {

	private Connection veza;
	private PreparedStatement izraz;
	Desktop d = Desktop.getDesktop();
	
	public Start() {
		
		veza = Baza.getConnection();
		
		izlaz:
		while(true) {
			
			izbornik();
			switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite redni broj stavke:"))) {
			case 0: // IZLAZ
				JOptionPane.showMessageDialog(null, "Doviðenja!");
				break izlaz;
			case 1: // GIT URL ERA dijagrama
				urlEra();
				break;
			case 2: // URL GITHUB KODA
				urlGit();
				break;
			case 3: // èitanje
				
				break;
			case 4: // unos
				
				break;
			case 5: // promjena
				
				break;
			case 6: // brisanje
				
				break;
			default:
				break;
			}
			
		}
		
	}
	
	private void urlGit() {
		
		try {
			d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Start.java"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
		
	}

	private void urlEra() {
		
		try {
			d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/ERA/ERA%20taxisluzba.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
		
	}
	
	private void izbornik() {
		System.out.println("################### IZBORNIK ###################");
		System.out.println("##  1. URL ERA dijagrama		      ##");
		System.out.println("##  2. URL GitHub koda	                      ##");
		System.out.println("##  3. Èitanje svih podataka odabrane tablice ##");
		System.out.println("##  4. Unos svih podataka u odabranu tablicu  ##");
		System.out.println("##  5. Promjena podataka u odabranoj tablici  ##");
		System.out.println("##  6. Brisanje podataka u odabranoj tablici  ##");
		System.out.println("##  0. Izlaz				      ##");
		System.out.println("################################################");
	}



	public static void main(String[] args) {
		new Start();
	}
}
