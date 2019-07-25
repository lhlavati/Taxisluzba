package hlavati.ljetnizadatak;

import java.sql.Connection;
import java.sql.DriverManager;

public class Baza {

	public static Connection getConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mariadb://localhost/taxisluzba","edunova","edunova");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
