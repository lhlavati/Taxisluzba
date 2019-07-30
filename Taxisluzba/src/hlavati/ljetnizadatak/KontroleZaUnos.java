package hlavati.ljetnizadatak;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

public class KontroleZaUnos {

	public static String unosString(String poruka) {

		String s;
		while (true) {

			s = JOptionPane.showInputDialog(poruka);
			if (s.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Obavezan unos");
				continue;
			}
			return s.trim();
		}

	}

	public static int unosInt(String poruka) {

		int i;
		while (true) {
			try {
				i = Integer.parseInt(JOptionPane.showInputDialog(poruka));
				if (i <= 0) {
					JOptionPane.showMessageDialog(null, "Broj mora biti ve�i od nule");
					continue;
				}
				return i;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Obavezan unos broja");
			}
		}

	}

	public static Byte unosByte(String poruka) {
		byte b;
		while (true) {

			try {
				b = Byte.parseByte(JOptionPane.showInputDialog(poruka));
				if (b == 1 || b == 0) {
					return b;
				} else {
					JOptionPane.showMessageDialog(null, "Obavezan unos, 1. DA ili 0. NE!");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Obavezan unos, 1. DA ili 0. NE!");
			}

		}
	}

	public static String provjeriSpol(String poruka) {

		String spol;
		char s;
		while (true) {

			try {
				spol = JOptionPane.showInputDialog(poruka);
				if (spol.length() != 1) {
					JOptionPane.showMessageDialog(null, "Obavezan unos, M ili Z!");
					continue;
				}
				s = spol.charAt(0);
				if (s == 'M' || s == 'Z') {
					return String.valueOf(s);
				} else {
					JOptionPane.showMessageDialog(null, "Obavezan unos, M ili Z!");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Obavezan unos, M ili Z!");
			}

		}
	}

	public static BigDecimal unosBigDec(String poruka) {

		double d;
		while (true) {

			try {

				d = Double.parseDouble(JOptionPane.showInputDialog(poruka));
				if (d <= 0) {
					JOptionPane.showMessageDialog(null, "Broj mora biti ve�i od nule");
					continue;
				}
				return BigDecimal.valueOf(d);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Obavezan unos");
			}

		}

	}
	
	
	public static boolean checkOIB(String oib) {

		if (oib.length() != 11)
			return false;

		try {
			Long.parseLong(oib);
		} catch (NumberFormatException e) {
			return false;
		}

		int a = 10;
		for (int i = 0; i < 10; i++) {
			a = a + Integer.parseInt(oib.substring(i, i + 1));
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

	
}
