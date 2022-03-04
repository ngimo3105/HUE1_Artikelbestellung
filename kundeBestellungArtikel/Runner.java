package kundeBestellungArtikel;

import java.sql.*;

public class Runner {

	@SuppressWarnings("finally") // @suppressWarnings: für Eclipse; unterdrückt Warnungen - dass kein finally
									// ausgeführt wird
	public static Connection createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager
					.getConnection("jdbc:sqlite:C:\\Users\\Mosi\\Desktop\\SQLite3\\Artikel-Bestellung-Kunde.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1); // wenn 0 als parameter dann exit, kein fehler; wenn 1 bzw etwas größer 0:
							// irgend ein Fehler
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Datenbank konnte nicht gefunden werden");
			System.exit(1);
			return null;
		} finally {
			System.out.println("Sind im finally");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		try {
			Connection c = createConnection();

			dropTable(c, "Artikel");
			dropTable(c, "Kunde");
			dropTable(c, "Bestellung");

			Kunde.createTableKunde(c);
			Artikel.createTableArtikel(c);
			Bestellung.createTableBestellung(c);

			System.out.print("Kunden werden inseriert ");
			Kunde.insertIntoKunde(c, "Melanie Heis", "melanie.heis@gmail.com");
			Kunde.insertIntoKunde(c, "Johanna Müller", "j.muller@gmx.at");
			Kunde.insertIntoKunde(c, "Nicole Winkler", "Winkler.N@tsn.at");
			System.out.println();

			System.out.print("Artikel werden inseriert ");
			Artikel.insertIntoArtikel(c, "Polster Set 2-teilig", 26.90);
			Artikel.insertIntoArtikel(c, "Vase Blumendesign", 99.99);
			Artikel.insertIntoArtikel(c, "Bilderrahmen Farbe: gold Vintage - design", 12.45);
			System.out.println();

			System.out.print("Bestellungen werden inseriert ");
			Bestellung.insertIntoBestellung(c, 1, 1, 2); // Melanie kauft 2 Polster Sets
			Bestellung.insertIntoBestellung(c, 2, 2, 1); // Johanna kauft 1 Vase
			Bestellung.insertIntoBestellung(c, 3, 3, 4); // Nicole kauft 4 Bilderrahmen
			System.out.println();
			System.out.println();

			System.out.println("Bestellung wird angezeigt: ");
			Bestellung.select(c, "Bestellung", 1, 1, 3, "Kappe", 20);
			Bestellung.select(c, "Bestellung", 2, 2, 5, "Schuhe", 30);
			Bestellung.select(c, "Bestellung", 1, 1, 7, "Kappe", 20);
			Bestellung.select(c, "Bestellung", 1, 1, 41, "Kappe", 20);

			Bestellung.deleteBestellung(c, "Bestellung", 1);
			Bestellung.updateBestellung(c, "Bestellung", 2, 7);

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public static void dropTable(Connection c, String tablename) {
		Statement stmt;
		try {
			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS " + tablename + " ;";
			System.out.println("Table " + tablename + " wurde gelöscht.");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
