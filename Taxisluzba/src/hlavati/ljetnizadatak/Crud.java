package hlavati.ljetnizadatak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Crud {

	public static Connection veza;
	public static PreparedStatement izraz;
	
	public static void promjeniUBazi() {

		
		izlaz: while (true) {
			System.out.println("\n1. vozilo\n2. vozi\n3. vozac\n4. voznja\n5. IZLAZ\n");
			switch (KontroleZaUnos.unosInt("Unesite tablicu koju biste htjeli promjeniti")) {
			case 1:

				try {
					izraz = veza.prepareStatement("SELECT * FROM vozilo");
					ResultSet rs = izraz.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					izraz = veza.prepareStatement("UPDATE vozilo SET ? = ? WHERE sifra = ?");
					izraz.setString(1, rsmd.getColumnName(stupciVozilo()));
					izraz.setString(2, KontroleZaUnos.unosString("Unesite novu vrijednost"));
					izraz.setInt(3, KontroleZaUnos.unosInt("Unesite šifru vozila kojeg biste htjeli promjeniti"));
					JOptionPane.showMessageDialog(null, "Uspješno uneseno (" + izraz.executeUpdate() + ")");
					System.out.println("");
					ispisiTablicu("SELECT * FROM vozilo");
					JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:
				break izlaz;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
			}
		}
	}

	private static int stupciVozilo() {
		
		izlaz: while(true) {
			System.out.println("\n1. marka\n2. gorivo\n3. snaga\n4. ABS\n5. godiste\n6. brojVozila\n7. IZLAZ");
			switch (KontroleZaUnos.unosInt("Unesite redni broj stupca")) {
			case 1:
				return 2;
			case 2:
				return 3;
			case 3:
				return 4;
			case 4:
				return 5;
			case 5:
				return 6;
			case 6:
				return 7;
			case 7:
				break izlaz;
			default:
				break;
			}
		}
		return '0';
	}

	public static void brisiIzBaze() {

		izlaz: while (true) {
			System.out.println("\n1. vozilo\n2. vozac\n3. voznja\n4. IZLAZ\n");
			switch (KontroleZaUnos.unosInt("Unesite tablicu iz koje biste htjeli brisati podatke")) {
			case 1:

				try {
					ispisiTablicu("SELECT * FROM vozilo");
					izraz = veza.prepareStatement("DELETE FROM vozilo WHERE sifra = ?");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru reda kojeg bi htjeli obrisati"));
					JOptionPane.showMessageDialog(null, "Uspješno obrisano (" + izraz.executeUpdate() + ")");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ne možete obrisati parent row! Negdje se koristi ovaj red");
				}

				break;
			case 2:

				try {
					ispisiTablicu("SELECT * FROM vozac");
					izraz = veza.prepareStatement("DELETE FROM vozac WHERE sifra = ?");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru reda kojeg biste htjeli obrisati"));
					JOptionPane.showMessageDialog(null, "Uspješno obrisano (" + izraz.executeUpdate() + ")");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ne možete obrisati parent row! Negdje se koristi ovaj red");
				}

				break;
			case 3:

				try {
					ispisiTablicu(
							"SELECT a.sifra, a.cijena, a.adresaPolazista, a.adresaOdredista, a.brojMob, a.pocetakVoznje, "
									+ "a.krajVoznje, a.brojPutnika, \r\n"
									+ "concat(c.ime,' ', c.prezime) AS vozac, concat(d.marka, ', broj vozila ', d.brojVozila) as vozilo\r\n"
									+ "FROM voznja a INNER JOIN vozi b ON a.vozi = b.sifra\r\n"
									+ "INNER JOIN vozac c ON c.sifra = b.vozac\r\n"
									+ "INNER JOIN vozilo d ON d.sifra = b.vozilo");
					izraz = veza.prepareStatement("DELETE FROM voznja WHERE sifra = ?");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru reda kojeg bi htjeli obrisati"));
					JOptionPane.showMessageDialog(null, "Uspješno obrisano (" + izraz.executeUpdate() + ")");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ne možete obrisati parent row! Negdje se koristi ovaj red");
				}
				break;
			case 4:
				break izlaz;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
			}
		}
	}

	public static void unesiUBazu() {
		izlaz: while (true) {
			System.out.println("\n1. vozilo\n2. vozi\n3. vozac\n4. voznja\n5. IZLAZ\n");
			switch (KontroleZaUnos.unosInt("Unesite tablicu u koju biste htjeli unjeti nove podatke")) {
			case 1:

				try {

					ispisiTablicu("SELECT * FROM vozilo");
					izraz = veza.prepareStatement(
							"INSERT INTO vozilo (marka, gorivo, snaga, ABS_, godiste, brojVozila) VALUES (?, ?, ?, ?, ?, ?)");
					izraz.setString(1, KontroleZaUnos.unosString("Unesite marku vozila"));
					izraz.setString(2, KontroleZaUnos.unosString("Unesite vrstu goriva"));
					izraz.setString(3, KontroleZaUnos.unosString("Unesite snagu motora (npr: '66 kW'"));
					izraz.setByte(4, KontroleZaUnos.unosByte("Unesite ima li vaše vozilo ABS (1. DA ili 0. NE"));
					izraz.setInt(5, KontroleZaUnos.unosInt("Unesite godiste vozila"));
					izraz.setInt(6, KontroleZaUnos.unosInt("Unesite broj vozila"));
					JOptionPane.showMessageDialog(null, "Uspješno uneseno (" + izraz.executeUpdate() + ")");
					System.out.println("");
					ispisiTablicu("SELECT * FROM vozilo");
					JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case 2:

				try {

					ispisiTablicu("SELECT * FROM vozilo");
					System.out.println("");
					ispisiTablicu("SELECT * FROM vozac");
					izraz = veza.prepareStatement("INSERT INTO vozi (vozilo, vozac) VALUES (?, ?)");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite sifru vozila"));
					izraz.setInt(2, KontroleZaUnos.unosInt("Unesite sifru vozaca"));
					JOptionPane.showMessageDialog(null, "Uspješno uneseno (" + izraz.executeUpdate() + ")");
					System.out.println("");
					ispisiTablicu(
							"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
									+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
									+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
									+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");
					JOptionPane.showMessageDialog(null, "Tablica vozi prikazana!");

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
					while (true) {
						oib = KontroleZaUnos.unosString("Unesite oib");
						if (KontroleZaUnos.checkOIB(oib)) {
							izraz.setString(3, oib);
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Molimo Vas unesite važeèi OIB od 11 znamenaka!");
						}
					}
					izraz.setString(4, KontroleZaUnos.provjeriSpol("Unesite spol, M ili Z"));
					JOptionPane.showMessageDialog(null, "Uspješno uneseno (" + izraz.executeUpdate() + ")");
					System.out.println("");
					ispisiTablicu("SELECT * FROM vozac");
					JOptionPane.showMessageDialog(null, "Tablica vozac prikazana!");

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case 4:

				try {

					ispisiTablicu(
							"SELECT a.sifra, a.cijena, a.adresaPolazista, a.adresaOdredista, a.brojMob, a.pocetakVoznje, "
									+ "a.krajVoznje, a.brojPutnika, \r\n"
									+ "concat(c.ime,' ', c.prezime) AS vozac, concat(d.marka, ', broj vozila ', d.brojVozila) as vozilo\r\n"
									+ "FROM voznja a INNER JOIN vozi b ON a.vozi = b.sifra\r\n"
									+ "INNER JOIN vozac c ON c.sifra = b.vozac\r\n"
									+ "INNER JOIN vozilo d ON d.sifra = b.vozilo");
					PreparedStatement izraz1 = veza.prepareStatement(
							"INSERT INTO voznja (cijena, adresaPolazista, adresaOdredista, brojMob, brojPutnika, vozi) "
									+ "VALUES (?, ?, ?, ?, ?, ?)");
					izraz1.setBigDecimal(1, KontroleZaUnos.unosBigDec("Unesite cijenu"));
					izraz1.setString(2, KontroleZaUnos.unosString("Unesite adresu polazista"));
					izraz1.setString(3, KontroleZaUnos.unosString("Unesite adresu odredista"));
					System.out.println("\nPrimjer formata broja mobitela: +385913350088");
					izraz1.setString(4, KontroleZaUnos.unosString("Unesite broj mobitela"));
					izraz1.setInt(5, KontroleZaUnos.unosInt("Unesite broj putnika"));
					ispisiTablicu(
							"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
									+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
									+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
									+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");
					izraz1.setInt(6, KontroleZaUnos.unosInt("Unesite sifru iz tablice vozi"));
					JOptionPane.showMessageDialog(null, "Uspješno uneseno (" + izraz1.executeUpdate() + ")");
					System.out.println("");
					ispisiTablicu(
							"SELECT a.sifra, a.cijena, a.adresaPolazista, a.adresaOdredista, a.brojMob, a.pocetakVoznje, "
									+ "a.krajVoznje, a.brojPutnika, \r\n"
									+ "concat(c.ime,' ', c.prezime) AS vozac, concat(d.marka, ', broj vozila ', d.brojVozila) as vozilo\r\n"
									+ "FROM voznja a INNER JOIN vozi b ON a.vozi = b.sifra\r\n"
									+ "INNER JOIN vozac c ON c.sifra = b.vozac\r\n"
									+ "INNER JOIN vozilo d ON d.sifra = b.vozilo");
					JOptionPane.showMessageDialog(null, "Tablica voznja prikazana!");

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case 5:
				break izlaz;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
			}
		}
	}

	public static void citajIzBaze() {

		izlaz: while (true) {
			System.out.println("\n1. vozilo\n2. vozi\n3. vozac\n4. voznja\n5. IZLAZ\n");
			switch (KontroleZaUnos.unosInt("Unesite redni broj tablice koju biste htjeli èitati")) {
			case 1:
				ispisiTablicu("SELECT * FROM vozilo");
				JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");
				break;
			case 2:
				ispisiTablicu(
						"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
								+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
								+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
								+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");
				JOptionPane.showMessageDialog(null, "Tablica vozi prikazana!");
				break;
			case 3:
				ispisiTablicu("SELECT * FROM vozac");
				JOptionPane.showMessageDialog(null, "Tablica vozac prikazana!");
				break;
			case 4:
				ispisiTablicu(
						"SELECT a.sifra, a.cijena, a.adresaPolazista, a.adresaOdredista, a.brojMob, a.pocetakVoznje, "
								+ "a.krajVoznje, a.brojPutnika, \r\n"
								+ "concat(c.ime,' ', c.prezime) AS vozac, concat(d.marka, ', broj vozila ', d.brojVozila) as vozilo\r\n"
								+ "FROM voznja a INNER JOIN vozi b ON a.vozi = b.sifra\r\n"
								+ "INNER JOIN vozac c ON c.sifra = b.vozac\r\n"
								+ "INNER JOIN vozilo d ON d.sifra = b.vozilo");
				JOptionPane.showMessageDialog(null, "Tablica voznja prikazana!");
				break;
			case 5:
				break izlaz;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
			}
		}
	}

	public static void ispisiTablicu(String upit) {

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
					if (i > 1)
						System.out.print(" | ");
					System.out.print(rs.getString(i));
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
