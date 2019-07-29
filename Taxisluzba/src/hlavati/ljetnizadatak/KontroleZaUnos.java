package hlavati.ljetnizadatak;

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
					JOptionPane.showMessageDialog(null, "Broj mora biti veæi od nule");
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
				if(b == 1 || b == 0) {
					return b;	
				}else {
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
		while(true) {
			
			try {
				spol = JOptionPane.showInputDialog(poruka);
				if(spol.length() != 1) {
					JOptionPane.showMessageDialog(null, "Obavezan unos, M ili Z!");
					continue;
				}
				s = spol.charAt(0);
				if(s == 'M' || s == 'Z') {
					return String.valueOf(s);
				}else {
					JOptionPane.showMessageDialog(null, "Obavezan unos, M ili Z!");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Obavezan unos, M ili Z!");
			}
	
		}
	}
	

}
