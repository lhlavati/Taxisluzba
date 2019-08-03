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
				updateVozilo();
				break;
			case 2:
				updateVozi();
				break;
			case 3:
				updateVozac();
				break;
			case 4:
				updateVoznja();
				break;
			case 5:
				break izlaz;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
			}
		}
	}

	public static void brisiIzBaze() {
		int obrisi;
		izlaz: while (true) {
			System.out.println("\n1. vozilo\n2. vozi\n3. vozac\n4. voznja\n5. IZLAZ\n");
			izadi: switch (KontroleZaUnos.unosInt("Unesite tablicu iz koje biste htjeli brisati podatke")) {
			case 1:

				try {
					ispisiTablicu("SELECT * FROM vozilo");
					izraz = veza.prepareStatement("DELETE FROM vozilo WHERE sifra = ?");
					while (true) {
						izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru reda kojeg bi htjeli obrisati"));
						obrisi = JOptionPane.showConfirmDialog(null,
								"Jeste li sigurni da želite obrisati ovaj podatak?", "Brisanje podatka",
								JOptionPane.YES_NO_OPTION);
						if (obrisi == 0) {
							JOptionPane.showMessageDialog(null, "Uspješno obrisano (" + izraz.executeUpdate() + ")");
							System.out.println("\n\n");
							ispisiTablicu("SELECT * FROM vozilo");
							JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");
							break izadi;
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ne možete obrisati parent row! Negdje se koristi ovaj red");
				}
				
				break;
				
			case 2:
				
				try {
					ispisiTablicu(
							"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
									+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
									+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
									+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");
					izraz = veza.prepareStatement("DELETE FROM vozi WHERE sifra = ?");
					while (true) {
						izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru reda kojeg bi htjeli obrisati"));
						obrisi = JOptionPane.showConfirmDialog(null,
								"Jeste li sigurni da želite obrisati ovaj podatak?", "Brisanje podatka",
								JOptionPane.YES_NO_OPTION);
						if (obrisi == 0) {
							JOptionPane.showMessageDialog(null, "Uspješno obrisano (" + izraz.executeUpdate() + ")");
							System.out.println("\n\n");
							ispisiTablicu(
									"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
											+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
											+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
											+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");
							JOptionPane.showMessageDialog(null, "Tablica vozi prikazana!");
							break izadi;
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ne možete obrisati parent row! Negdje se koristi ovaj red");
				}
				
				break;
				
			case 3:

				try {
					ispisiTablicu("SELECT * FROM vozac");
					izraz = veza.prepareStatement("DELETE FROM vozac WHERE sifra = ?");
					while (true) {
						izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru reda kojeg bi htjeli obrisati"));
						obrisi = JOptionPane.showConfirmDialog(null,
								"Jeste li sigurni da želite obrisati ovaj podatak?", "Brisanje podatka",
								JOptionPane.YES_NO_OPTION);
						if (obrisi == 0) {
							JOptionPane.showMessageDialog(null, "Uspješno obrisano (" + izraz.executeUpdate() + ")");
							System.out.println("\n\n");
							ispisiTablicu("SELECT * FROM vozac");
							JOptionPane.showMessageDialog(null, "Tablica vozac prikazana!");
							break izadi;
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ne možete obrisati parent row! Negdje se koristi ovaj red");
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
					izraz = veza.prepareStatement("DELETE FROM voznja WHERE sifra = ?");
					while (true) {
						izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru reda kojeg bi htjeli obrisati"));
						obrisi = JOptionPane.showConfirmDialog(null,
								"Jeste li sigurni da želite obrisati ovaj podatak?", "Brisanje podatka",
								JOptionPane.YES_NO_OPTION);
						if (obrisi == 0) {
							JOptionPane.showMessageDialog(null, "Uspješno obrisano (" + izraz.executeUpdate() + ")");
							System.out.println("\n\n");
							ispisiTablicu(
									"SELECT a.sifra, a.cijena, a.adresaPolazista, a.adresaOdredista, a.brojMob, a.pocetakVoznje, "
											+ "a.krajVoznje, a.brojPutnika, \r\n"
											+ "concat(c.ime,' ', c.prezime) AS vozac, concat(d.marka, ', broj vozila ', d.brojVozila) as vozilo\r\n"
											+ "FROM voznja a INNER JOIN vozi b ON a.vozi = b.sifra\r\n"
											+ "INNER JOIN vozac c ON c.sifra = b.vozac\r\n"
											+ "INNER JOIN vozilo d ON d.sifra = b.vozilo");
							JOptionPane.showMessageDialog(null, "Tablica voznja prikazana!");
							break izadi;
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ne možete obrisati parent row! Negdje se koristi ovaj red");
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
					izraz.setByte(4, KontroleZaUnos.unosByte("Unesite ima li vaše vozilo ABS\n1. DA ili 0. NE"));
					izraz.setInt(5, KontroleZaUnos.provjeraGodista("Unesite godiste vozila"));
					izraz.setInt(6, KontroleZaUnos.provjeraBrojaVozila("Unesite broj vozila"));
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

					izraz = veza.prepareStatement("INSERT INTO vozi (vozilo, vozac) VALUES (?, ?)");
					ispisiTablicu("SELECT * FROM vozilo");
					izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru vozila"));
					System.out.println("\n\n");
					ispisiTablicu("SELECT * FROM vozac");
					izraz.setInt(2, KontroleZaUnos.unosInt("Unesite šifru vozaca"));
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
					izraz.setString(4, KontroleZaUnos.provjeriSpol("Unesite spol\nM ili Z"));
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
					izraz = veza.prepareStatement(
							"INSERT INTO voznja (cijena, adresaPolazista, adresaOdredista, brojMob, brojPutnika, vozi) "
									+ "VALUES (?, ?, ?, ?, ?, ?)");
					izraz.setBigDecimal(1, KontroleZaUnos.unosBigDec("Unesite cijenu"));
					izraz.setString(2, KontroleZaUnos
							.unosString("Unesite novu adresu polazista u formatu:\nAdresa i kuæni broj, Grad"));
					izraz.setString(3, KontroleZaUnos
							.unosString("Unesite novu adresu odredista u formatu:\nAdresa i kuæni broj, Grad"));
					izraz.setString(4,
							KontroleZaUnos.provjeraBrojaMob("Unesite novi broj mobitela\nHR format: +385991234567"));
					izraz.setInt(5, KontroleZaUnos.unosInt("Unesite broj putnika"));
					System.out.println("\n\n");
					ispisiTablicu(
							"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
									+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
									+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
									+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");
					System.out.println("\n\n");
					izraz.setInt(6, KontroleZaUnos.unosInt("Unesite šifru iz tablice vozi"));
					JOptionPane.showMessageDialog(null, "Uspješno uneseno (" + izraz.executeUpdate() + ")");
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

	private static void updateVozilo() {

		int i;
		try {

			ispisiTablicu("SELECT * FROM vozilo");

			i = KontroleZaUnos.unosInt("Unesite šifru vozila kojeg biste htjeli promjeniti");
			izraz = veza.prepareStatement("UPDATE vozilo SET marka = ? WHERE sifra = ?");
			izraz.setInt(2, i);
			izraz.setString(1, KontroleZaUnos.unosString("Unesite novu vrijednost marke"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz.executeUpdate() + ")");

			PreparedStatement izraz1 = veza.prepareStatement("UPDATE vozilo SET gorivo = ? WHERE sifra = ?");
			izraz1.setInt(2, i);
			izraz1.setString(1, KontroleZaUnos.unosString("Unesite novu vrijednost goriva"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz1.executeUpdate() + ")");

			PreparedStatement izraz2 = veza.prepareStatement("UPDATE vozilo SET snaga = ? WHERE sifra = ?");
			izraz2.setInt(2, i);
			izraz2.setString(1, KontroleZaUnos.unosString("Unesite novu vrijednost snage"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz2.executeUpdate() + ")");

			PreparedStatement izraz3 = veza.prepareStatement("UPDATE vozilo SET ABS_ = ? WHERE sifra = ?");
			izraz3.setInt(2, i);
			izraz3.setByte(1, KontroleZaUnos.unosByte("Unesite novu vrijednost ABS-a\n1. DA ili 0. NE"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz3.executeUpdate() + ")");

			PreparedStatement izraz4 = veza.prepareStatement("UPDATE vozilo SET godiste = ? WHERE sifra = ?");
			izraz4.setInt(2, i);
			izraz4.setInt(1, KontroleZaUnos.provjeraGodista("Unesite novu vrijednost godista"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz4.executeUpdate() + ")");

			PreparedStatement izraz5 = veza.prepareStatement("UPDATE vozilo SET brojVozila = ? WHERE sifra = ?");
			izraz5.setInt(2, i);
			izraz5.setInt(1, KontroleZaUnos.provjeraBrojaVozila("Unesite novi broj vozila"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz5.executeUpdate() + ")");

			System.out.println("");
			ispisiTablicu("SELECT * FROM vozilo");
			JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateVozi() {

		int i;
		try {

			ispisiTablicu(
					"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
							+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
							+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
							+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");

			i = KontroleZaUnos.unosInt("Unesite šifru vozi kojeg biste htjeli promjeniti");

			System.out.println("\n\n");
			ispisiTablicu("SELECT * FROM vozilo");
			JOptionPane.showMessageDialog(null, "Tablica vozilo prikazana!");
			izraz = veza.prepareStatement("UPDATE vozi SET vozilo = ? WHERE sifra = ?");
			izraz.setInt(2, i);
			izraz.setInt(1, KontroleZaUnos.unosInt("Unesite šifru novog vozila"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz.executeUpdate() + ")");

			System.out.println("\n\n");

			ispisiTablicu("SELECT * FROM vozac");
			JOptionPane.showMessageDialog(null, "Tablica vozac prikazana!");
			PreparedStatement izraz1 = veza.prepareStatement("UPDATE vozi SET vozac = ? WHERE sifra = ?");
			izraz1.setInt(2, i);
			izraz1.setInt(1, KontroleZaUnos.unosInt("Unesite šifru novog vozaca"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz1.executeUpdate() + ")");

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

	}

	private static void updateVozac() {

		int i;
		try {

			ispisiTablicu("SELECT * FROM vozac");

			i = KontroleZaUnos.unosInt("Unesite šifru vozaca kojeg biste htjeli promjeniti");

			izraz = veza.prepareStatement("UPDATE vozac SET ime = ? WHERE sifra = ?");
			izraz.setInt(2, i);
			izraz.setString(1, KontroleZaUnos.unosString("Unesite novo ime vozaca"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz.executeUpdate() + ")");

			PreparedStatement izraz1 = veza.prepareStatement("UPDATE vozac SET prezime = ? WHERE sifra = ?");
			izraz1.setInt(2, i);
			izraz1.setString(1, KontroleZaUnos.unosString("Unesite novo prezime vozaca"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz1.executeUpdate() + ")");

			PreparedStatement izraz2 = veza.prepareStatement("UPDATE vozac SET OIB = ? WHERE sifra = ?");
			izraz2.setInt(2, i);
			String oib;
			while (true) {
				oib = KontroleZaUnos.unosString("Unesite oib");
				if (KontroleZaUnos.checkOIB(oib)) {
					izraz2.setString(1, oib);
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Molimo Vas unesite važeèi OIB od 11 znamenaka!");
				}
			}
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz2.executeUpdate() + ")");

			PreparedStatement izraz3 = veza.prepareStatement("UPDATE vozac SET spol = ? WHERE sifra = ?");
			izraz3.setInt(2, i);
			izraz3.setString(1, KontroleZaUnos.provjeriSpol("Unesite spol vozaca\nM ili Z"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz3.executeUpdate() + ")");

			System.out.println("\n\n");
			ispisiTablicu("SELECT * FROM vozac");
			JOptionPane.showMessageDialog(null, "Tablica vozi prikazana!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void updateVoznja() {

		int i;
		try {

			ispisiTablicu("SELECT a.sifra, a.cijena, a.adresaPolazista, a.adresaOdredista, a.brojMob, a.pocetakVoznje, "
					+ "a.krajVoznje, a.brojPutnika, \r\n"
					+ "concat(c.ime,' ', c.prezime) AS vozac, concat(d.marka, ', broj vozila ', d.brojVozila) as vozilo\r\n"
					+ "FROM voznja a INNER JOIN vozi b ON a.vozi = b.sifra\r\n"
					+ "INNER JOIN vozac c ON c.sifra = b.vozac\r\n" + "INNER JOIN vozilo d ON d.sifra = b.vozilo");

			i = KontroleZaUnos.unosInt("Unesite šifru voznje koju biste htjeli promjeniti");
			izraz = veza.prepareStatement("UPDATE voznja SET cijena = ? WHERE sifra = ?");
			izraz.setInt(2, i);
			izraz.setBigDecimal(1, KontroleZaUnos.unosBigDec("Unesite novu cijenu voznje"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz.executeUpdate() + ")");

			PreparedStatement izraz1 = veza.prepareStatement("UPDATE voznja SET adresaPolazista = ? WHERE sifra = ?");
			izraz1.setInt(2, i);
			izraz1.setString(1,
					KontroleZaUnos.unosString("Unesite novu adresu polazista u formatu:\nAdresa i kuæni broj, Grad"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz1.executeUpdate() + ")");

			PreparedStatement izraz2 = veza.prepareStatement("UPDATE voznja SET adresaOdredista = ? WHERE sifra = ?");
			izraz2.setInt(2, i);
			izraz2.setString(1,
					KontroleZaUnos.unosString("Unesite novu adresu odredista u formatu:\nAdresa i kuæni broj, Grad"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz2.executeUpdate() + ")");

			PreparedStatement izraz3 = veza.prepareStatement("UPDATE voznja SET brojMob = ? WHERE sifra = ?");
			izraz3.setInt(2, i);
			izraz3.setString(1,
					KontroleZaUnos.provjeraBrojaMob("Unesite novi broj mobitela\nHrvatski format: +385991234567"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz3.executeUpdate() + ")");

			PreparedStatement izraz4 = veza.prepareStatement("UPDATE voznja SET brojPutnika = ? WHERE sifra = ?");
			izraz4.setInt(2, i);
			izraz4.setInt(1, KontroleZaUnos.unosInt("Unesite novi broj putnika"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz4.executeUpdate() + ")");

			PreparedStatement izraz5 = veza.prepareStatement("UPDATE voznja SET vozi = ? WHERE sifra = ?");
			izraz5.setInt(2, i);
			System.out.println("\n\n");
			ispisiTablicu(
					"SELECT a.sifra, a.vrijemePocetka, a.vrijemeKraja, concat(b.ime,' ', b.prezime) AS vozac, \r\n"
							+ "concat(c.marka, ', broj vozila ', c.brojVozila) as vozilo\r\n"
							+ "FROM vozi a INNER JOIN vozac b ON b.sifra = a.vozac\r\n"
							+ "INNER JOIN vozilo c ON c.sifra = a.vozilo");
			JOptionPane.showMessageDialog(null, "Tablica vozi prikazana!");
			izraz5.setInt(1, KontroleZaUnos.unosInt("Odaberite sifru iz vozi"));
			JOptionPane.showMessageDialog(null, "Uspješno promjenjeno (" + izraz5.executeUpdate() + ")");

			System.out.println("");
			ispisiTablicu("SELECT a.sifra, a.cijena, a.adresaPolazista, a.adresaOdredista, a.brojMob, a.pocetakVoznje, "
					+ "a.krajVoznje, a.brojPutnika, \r\n"
					+ "concat(c.ime,' ', c.prezime) AS vozac, concat(d.marka, ', broj vozila ', d.brojVozila) as vozilo\r\n"
					+ "FROM voznja a INNER JOIN vozi b ON a.vozi = b.sifra\r\n"
					+ "INNER JOIN vozac c ON c.sifra = b.vozac\r\n" + "INNER JOIN vozilo d ON d.sifra = b.vozilo");
			JOptionPane.showMessageDialog(null, "Tablica voznja prikazana!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void ispisiTablicu(String upit) {

		try {
			PreparedStatement izraz0 = veza.prepareStatement(upit);
			ResultSet rs = izraz0.executeQuery();
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
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
