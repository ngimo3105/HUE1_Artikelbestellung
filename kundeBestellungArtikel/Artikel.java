package kundeBestellungArtikel;

import java.sql.*;

public class Artikel {
	public static void createTableArtikel(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Artikel" + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "bezeichnung VARCHAR(30)," + "preis double);";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoArtikel(Connection c, String bezeichnung, double preis) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into Artikel (bezeichnung, preis)" + "values (\"" + bezeichnung + "\", " + preis
					+ ");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateTableArtikel(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "Alter table add Lagerbestand Integer;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void insertIntoMitLagerbestand(Connection c, String tablename, String bezeichnung, int preis,
			int Lagerbestand) {
		try {
			Statement stmt = c.createStatement();
			String sql = "Update " + tablename + " set Lagerbestand = " + Lagerbestand + " where bezeichnung = \""
					+ bezeichnung + "\";";
			System.out.println();
			System.out.printf("Artikel:    Bezeichnung: " + bezeichnung + "    Preis: " + preis + "    Lagerbestand: "
					+ Lagerbestand + "\n");
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
