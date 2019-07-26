package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
				citajIzBaze();
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
	
	private void citajIzBaze() {
		
		System.out.println("1. vozac\n2. vozilo\n3. vozi\n4. voznja\n5. izlaz");
		switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite redni broj tablice koju biste htjeli èitati"))) {
			case 1:
				ispisiTablicu("SELECT * FROM vozac");
				JOptionPane.showMessageDialog(null, "Tablica vozac prikazana!");
				break;
			case 2:
				ispisiTablicu("SELECT * FROM vozilo");
				JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");
				break;
			case 3:
				ispisiTablicu("SELECT * FROM vozi");
				JOptionPane.showMessageDialog(null, "Tablica vozi prikazana!");
				break;
			case 4:
				ispisiTablicu("SELECT * FROM voznja");
				JOptionPane.showMessageDialog(null, "Tablica voznja prikazana!");
				break;
			case 5:
				break;
			default:
				break;
			}
			
	}
	
	private void ispisiTablicu(String upit) {
		
		try {
			izraz = veza.prepareStatement(upit);
			ResultSet rs = izraz.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			for (int i = 1; i <= columnsNumber; i++) {
				System.out.print(rsmd.getColumnName(i) + " | ");
			}
			System.out.println("");
			while (rs.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(" | ");
		            System.out.print(rs.getString(i));
		        }
		        System.out.println("");
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void urlGit() {
		
		System.out.println("1. Start.java (highly recommended)\n2. Baza.java\n3. Izlaz");
		try {
			switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite redni broj klase koju zelite otvoriti"))) {
			case 1:
				d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Start.java"));
				JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
				break;
			case 2:
				d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Baza.java"));
				JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
				break;
			case 3:
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
