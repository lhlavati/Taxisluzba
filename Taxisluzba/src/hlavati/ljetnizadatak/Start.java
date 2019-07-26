package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

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
				JOptionPane.showMessageDialog(null, "Dovi�enja!");
				break izlaz;
			case 1: // GIT URL ERA dijagrama
				urlEra();
				break;
			case 2: // URL GITHUB KODA
				urlGit();
				break;
			case 3: // �itanje
				citajIzBaze();
				break;
			case 4: // unos
				unesiUBazu();
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
	
	private void unesiUBazu() {
		
		System.out.println("1. vozac\n2. vozi\n3. vozilo\n4. voznja\n5. IZLAZ");
		switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite tablicu u koju biste htjeli unjeti nove podatke"))) {
		case 1:
			
			try {
				izraz = veza.prepareStatement("INSERT INTO vozilo (marka, gorivo, snaga, ABS_, godiste, brojVozila) VALUES (?, ?, ?, ?, ?, ?)");
				izraz.setString(1, JOptionPane.showInputDialog("Unesite marku vozila"));
				izraz.setString(2, JOptionPane.showInputDialog("Unesite vrstu goriva"));
				izraz.setString(3, JOptionPane.showInputDialog("Unesite snagu motora (npr: '66 kW'"));
				izraz.setBoolean(4, Boolean.parseBoolean(JOptionPane.showInputDialog("Unesite ima li va�e vozilo ABS (true ili false)")));
				izraz.setInt(5, Integer.parseInt(JOptionPane.showInputDialog("Unesite godiste vozila")));
				izraz.setInt(6, Integer.parseInt(JOptionPane.showInputDialog("Unesite broj vozila koje �elite unjeti")));
				System.out.println("Uneseno je " + izraz.executeUpdate());
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		case 2:
			
			try {
				
				ispisiTablicu("SELECT * FROM vozilo");
				ispisiTablicu("SELECT * FROM vozac");
				izraz = veza.prepareStatement("INSERT INTO vozi (vozilo, vozac) VALUES (?, ?)");
				izraz.setInt(1, Integer.parseInt(JOptionPane.showInputDialog("Unesite sifru vozila")));
				izraz.setInt(2, Integer.parseInt(JOptionPane.showInputDialog("Unesite sifru vozaca")));
				JOptionPane.showMessageDialog(null, ("Uspje�no uneseno (" + izraz.executeUpdate() + ")"));
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		default:
			break;
		}
		
	}

	private void citajIzBaze() {
		
		System.out.println("1. vozac\n2. vozilo\n3. vozi\n4. voznja\n5. IZLAZ");
		switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite redni broj tablice koju biste htjeli �itati"))) {
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
		
		System.out.println("1. Start.java (highly recommended)\n2. Baza.java\n3. IZLAZ");
		try {
			switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite redni broj klase koju zelite otvoriti"))) {
			case 1:
				d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Start.java"));
				JOptionPane.showMessageDialog(null, "URL uspje�no otvoren!");
				break;
			case 2:
				d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Baza.java"));
				JOptionPane.showMessageDialog(null, "URL uspje�no otvoren!");
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
		JOptionPane.showMessageDialog(null, "URL uspje�no otvoren!");
		
	}
	
	private void izbornik() {
		System.out.println("################### IZBORNIK ###################");
		System.out.println("##  1. URL ERA dijagrama		      ##");
		System.out.println("##  2. URL GitHub koda	                      ##");
		System.out.println("##  3. �itanje svih podataka odabrane tablice ##");
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
