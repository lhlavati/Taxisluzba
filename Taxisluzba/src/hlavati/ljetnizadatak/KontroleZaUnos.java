package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;

public class KontroleZaUnos {

	public static String unosString(String poruka) {
		
		String s;
		while(true) {
			
			s=JOptionPane.showInputDialog(poruka);
			if(s.trim().length()==0) {
				JOptionPane.showMessageDialog(null,"Obavezan unos");
				continue;
			}
			return s;
		}
		
	}
	
	
	public static int unosInt(String poruka) {
		
		int i;
		while(true) {
			try {
				i=Integer.parseInt(JOptionPane.showInputDialog(poruka));
				if(i<=0) {
					JOptionPane.showMessageDialog(null, "Broj mora biti veæi od nule");
					continue;
				}
				return i;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Obavezan unos broja");
			}
		}
		
	}
	
	
	
}
