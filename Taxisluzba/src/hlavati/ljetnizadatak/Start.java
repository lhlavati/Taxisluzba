package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.net.URI;

public class Start {

	Desktop d = Desktop.getDesktop();

	public Start() {

		Crud.veza = Baza.getConnection();
		JOptionPane.showMessageDialog(null,
				"Preporuka: Console-u stavite preko cijelog ekrana radi boljeg pregleda! :)");
		izlaz: while (true) {

			izbornik();
			switch (KontroleZaUnos.unosInt("Unesite redni broj stavke:")) {
			case 7: // IZLAZ
				JOptionPane.showMessageDialog(null, "Doviðenja!");
				break izlaz;
			case 1: // GIT URL ERA dijagrama
				urlEra();
				break;
			case 2: // URL GITHUB KODA
				urlGit();
				break;
			case 3: // èitanje
				Crud.citajIzBaze();
				break;
			case 4: // unos
				Crud.unesiUBazu();
				break;
			case 5: // promjena
				Crud.promjeniUBazi();
				break;
			case 6: // brisanje
				Crud.brisiIzBaze();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Nevazeci broj!");
				break;
			}

		}

	}

	private void urlGit() {

		izlaz: while (true) {
			System.out
					.println("\n1. Start.java\n OPIS: Start klasa koja sadrži metodu urlGit, urlEra, izbornik, konstruktor i main\n\n"
							+ "2. Baza.java\n OPIS: Jednostavna konekcija sa bazom\n\n3. KontroleZaUnos.java\n OPIS: Metode koje sluze"
							+ " kao kontrola prilikom unosa i promjene u bazi\n\n4. Crud.java\n OPIS: CRUD baze\n\n5. IZLAZ\n");
			try {
				switch (KontroleZaUnos.unosInt("Unesite redni broj klase koju zelite otvoriti")) {
				case 1:
					d.browse(new URI(
							"https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Start.java"));
					JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
					break;
				case 2:
					d.browse(new URI(
							"https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Baza.java"));
					JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
					break;
				case 3:
					d.browse(new URI(
							"https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/KontroleZaUnos.java"));
					JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
					break;
				case 4:
					d.browse(new URI(
							"https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Crud.java"));
					JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
					break;
				case 5:
					break izlaz;
				default:
					JOptionPane.showMessageDialog(null, "Nevazeci broj!");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		System.out.println("##  7. Izlaz				      ##");
		System.out.println("################################################");
	}

	public static void main(String[] args) {
		new Start();
	}
}
