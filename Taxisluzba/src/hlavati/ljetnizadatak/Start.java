package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.net.URI;

public class Start {

	public Start() {
		izlaz:
		while(true) {
			Desktop d = Desktop.getDesktop();
			izbornik();
			switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite redni broj stavke:"))) {
			case 0: // IZLAZ
				JOptionPane.showMessageDialog(null, "Doviðenja!");
				break izlaz;
			case 1: // GIT URL ERA dijagrama
				
				try {
					d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/ERA/ERA%20taxisluzba.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
				break;
				
			case 2: // URL GITHUB KODA
				
				try {
					d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/Taxisluzba/src/hlavati/ljetnizadatak/Start.java"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "URL uspješno otvoren!");
				break;
			default:
				break;
			}
			
		}
		
	}
	
	private void izbornik() {
		
	}



	public static void main(String[] args) {
		new Start();
	}
}
