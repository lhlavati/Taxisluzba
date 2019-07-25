package hlavati.ljetnizadatak;

import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.net.URI;

public class Start {

	public Start() {
		izlaz:
		while(true) {
			switch (Integer.parseInt(JOptionPane.showInputDialog("Unesite redni broj stavke:"))) {
			case 0: // IZLAZ
				break izlaz;
			case 1: // GIT URL ERA dijagrama
				Desktop d = Desktop.getDesktop();
				try {
					d.browse(new URI("https://github.com/lhlavati/Taxisluzba/blob/master/ERA/ERA%20taxisluzba.png"));
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
	
	public static void main(String[] args) {
		new Start();
	}
}
