package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.math.BigDecimal;
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
			switch (KontroleZaUnos.unosInt("Unesite redni broj stavke:")) {
			case 7: // IZLAZ
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
				brisiIzBaze();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
			}
			
		}
		
	}
	
	private void brisiIzBaze() {
		System.out.println("1. vozilo\n2. vozac\n3. voznja\n4. IZLAZ");
		switch(KontroleZaUnos.unosInt("Unesite tablicu iz koje biste htjeli brisati podatke")){
			case 1:
				
				try {
					ispisiTablicu("SELECT * FROM vozilo");
					izraz = veza.prepareStatement("DELETE FROM vozilo WHERE sifra = ?");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite �ifru reda kojeg bi htjeli obrisati"));
					JOptionPane.showMessageDialog(null, "Uspje�no obrisano (" + izraz.executeUpdate() + ")");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			case 2:
				
				try {
					ispisiTablicu("SELECT * FROM vozac");
					izraz = veza.prepareStatement("DELETE FROM vozac WHERE sifra = ?");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite �ifru reda kojeg biste htjeli obrisati"));
					JOptionPane.showMessageDialog(null, "Uspje�no obrisano (" + izraz.executeUpdate() + ")");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			case 3:
				
				try {
					ispisiTablicu("SELECT * FROM voznja");
					izraz = veza.prepareStatement("DELETE FROM voznja WHERE sifra = ?");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite �ifru reda kojeg bi htjeli obrisati"));
					JOptionPane.showMessageDialog(null, "Uspje�no obrisano (" + izraz.executeUpdate() + ")");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
		}
	}

	private void unesiUBazu() {
		
		System.out.println("1. vozilo\n2. vozi\n3. vozac\n4. voznja\n5. IZLAZ");
		switch (KontroleZaUnos.unosInt("Unesite tablicu u koju biste htjeli unjeti nove podatke")) {
		case 1:
			
			try {
				
				ispisiTablicu("SELECT * FROM vozilo");
				izraz = veza.prepareStatement("INSERT INTO vozilo (marka, gorivo, snaga, ABS_, godiste, brojVozila) VALUES (?, ?, ?, ?, ?, ?)");
				izraz.setString(1, KontroleZaUnos.unosString("Unesite marku vozila"));
				izraz.setString(2, KontroleZaUnos.unosString("Unesite vrstu goriva"));
				izraz.setString(3, KontroleZaUnos.unosString("Unesite snagu motora (npr: '66 kW'"));
				izraz.setBoolean(4, KontroleZaUnos.unosBoolean("Unesite ima li va�e vozilo ABS (DA ili NE)"));
				izraz.setInt(5, KontroleZaUnos.unosInt("Unesite godiste vozila"));
				izraz.setInt(6, KontroleZaUnos.unosInt("Unesite broj vozila koje �elite unjeti"));
				JOptionPane.showMessageDialog(null, "Uspje�no uneseno (" + izraz.executeUpdate() + ")");
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		case 2:
			
			try {
				
				ispisiTablicu("SELECT * FROM vozilo");
				ispisiTablicu("SELECT * FROM vozac");
				izraz = veza.prepareStatement("INSERT INTO vozi (vozilo, vozac) VALUES (?, ?)");
				izraz.setInt(1, KontroleZaUnos.unosInt("Unesite sifru vozila"));
				izraz.setInt(2, KontroleZaUnos.unosInt("Unesite sifru vozaca"));
				JOptionPane.showMessageDialog(null, "Uspje�no uneseno (" + izraz.executeUpdate() + ")");
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		case 3:
			
			try {
				
				ispisiTablicu("SELECT * FROM vozac");
				izraz = veza.prepareStatement("INSERT INTO vozac (ime, prezime, oib, spol) VALUES (?, ?, ?, ?)");
				izraz.setString(1, KontroleZaUnos.unosString("Unesite ime"));
				izraz.setString(2, KontroleZaUnos.unosString("Unesite prezime"));
				String oib;
				while(true) {
					oib = KontroleZaUnos.unosString("Unesite oib");
					if(checkOIB(oib)) {
						izraz.setString(3, oib);
						break;
					}else {
						JOptionPane.showMessageDialog(null, "Molimo Vas unesite va�e�i OIB od 11 znamenaka!");
					}
				}
				izraz.setString(4, provjeriSpol());
				JOptionPane.showMessageDialog(null, "Uspje�no uneseno (" + izraz.executeUpdate() + ")");
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		case 4:
			
			try {
				
				ispisiTablicu("SELECT * FROM voznja");
				izraz = veza.prepareStatement("INSERT INTO voznja (cijena, adresaPolazista, adresaOdredista, brojMob, brojPutnika) "
											+ "VALUES (?, ?, ?, ?, ?)");
				double cijena = Double.parseDouble(JOptionPane.showInputDialog("Unesite cijenu"));
				izraz.setBigDecimal(1, BigDecimal.valueOf(cijena));
				izraz.setString(2, KontroleZaUnos.unosString("Unesite adresu polazista"));
				izraz.setString(3, KontroleZaUnos.unosString("Unesite adresu odredista"));
				System.out.println("\nPrimjer formata broja mobitela: +385913350088");
				izraz.setString(4, KontroleZaUnos.unosString("Unesite broj mobitela"));
				izraz.setInt(5, KontroleZaUnos.unosInt("Unesite broj putnika"));
				JOptionPane.showMessageDialog(null, "Uspje�no uneseno (" + izraz.executeUpdate() + ")");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		default:
			JOptionPane.showMessageDialog(null, "Nevazeci broj!");
			break;
		}
		
	}
	

	private String provjeriSpol() {
		
		String spol;
		while(true) {
			spol = KontroleZaUnos.unosString("Unesite spol, M ili Z");
			if(spol != "M" || spol != "Z") {
				JOptionPane.showMessageDialog(null, "Niste unjeli spol!");
			}else {
				return spol;
			}
		}
		
		
	}
	

	private void citajIzBaze() {
		
		System.out.println("1. vozilo\n2. vozi\n3. vozac\n4. voznja\n5. IZLAZ");
		switch (KontroleZaUnos.unosInt("Unesite redni broj tablice koju biste htjeli �itati")) {
			case 1:
				ispisiTablicu("SELECT * FROM vozilo");
				JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");
				break;
			case 2:
				ispisiTablicu("SELECT * FROM vozi");
				JOptionPane.showMessageDialog(null, "Tablica vozi prikazana!");
				break;
			case 3:
				ispisiTablicu("SELECT * FROM vozac");
				JOptionPane.showMessageDialog(null, "Tablica vozac prikazana!");
				break;
			case 4:
				ispisiTablicu("SELECT * FROM voznja");
				JOptionPane.showMessageDialog(null, "Tablica voznja prikazana!");
				break;
			case 5:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
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
		
		System.out.println("1. Start.java (highly recommended)\n OPIS: Start klasa koja sadr�i skoro sve metode, konstruktor i main\n\n"
							+ "2. Baza.java\n OPIS: Jednostavna konekcija sa bazom\n\n3. KontroleZaUnos.java\n OPIS: Metode koje sluze"
							+ " kao kontrola prilikom unosa i promjene u bazi\n\n4. IZLAZ");
		try {
			switch (KontroleZaUnos.unosInt("Unesite redni broj klase koju zelite otvoriti")) {
			case 1:
				d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Start.java"));
				JOptionPane.showMessageDialog(null, "URL uspje�no otvoren!");
				break;
			case 2:
				d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Baza.java"));
				JOptionPane.showMessageDialog(null, "URL uspje�no otvoren!");
				break;
			case 3:
				d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/KontroleZaUnos.java"));
				JOptionPane.showMessageDialog(null, "URL uspje�no otvoren!");
				break;
			case 4:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
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
	
	
	public boolean checkOIB(String oib) {

        if (oib.length() != 11)
            return false;

        try {
            Long.parseLong(oib);
        } catch (NumberFormatException e) {
            return false;
        }

        int a = 10;
        for (int i = 0; i < 10; i++) {
            a = a + Integer.parseInt(oib.substring(i, i+1));
            a = a % 10;
            if (a == 0)
                a = 10;
            a *= 2;
            a = a % 11;
        }
        int kontrolni = 11 - a;
        if (kontrolni == 10)
            kontrolni = 0;

        return kontrolni == Integer.parseInt(oib.substring(10));
    }
	
	
	private void izbornik() {
		System.out.println("################### IZBORNIK ###################");
		System.out.println("##  1. URL ERA dijagrama		      ##");
		System.out.println("##  2. URL GitHub koda	                      ##");
		System.out.println("##  3. �itanje svih podataka odabrane tablice ##");
		System.out.println("##  4. Unos svih podataka u odabranu tablicu  ##");
		System.out.println("##  5. Promjena podataka u odabranoj tablici  ##");
		System.out.println("##  6. Brisanje podataka u odabranoj tablici  ##");
		System.out.println("##  7. Izlaz				      ##");
		System.out.println("################################################");
	}


	public static void main(String[] args) {
		new Start();
	}
}
