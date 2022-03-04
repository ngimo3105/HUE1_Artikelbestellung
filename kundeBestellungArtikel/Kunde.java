package kundeBestellungArtikel;

import java.sql.*;

public class Kunde {
	public static void createTableKunde(Connection c) {
		try {
			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Kunde" + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name VARCHAR(30)," + "email VARCHAR(40));";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoKunde(Connection c, String name, String email) {
		try {
			Statement stmt = c.createStatement();
			String sql = "insert into Kunde (name, email) values" + "(\"" + name + "\", \"" + email + "\");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
