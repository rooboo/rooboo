package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection
{
	private static Connection	conn				= null;

	// Hostname
	private static String			dbHost			= "localhost";

	// Port -- Standard: 3306
	private static String			dbPort			= "3306";

	// Datenbankname
	private static String			database		= "test";

	// Datenbankuser
	private static String			dbUser			= "root";

	// Datenbankpasswort
	private static String			dbPassword	= "mysql";

	private MySQLConnection()
	{
		try
		{

			// Datenbanktreiber für ODBC Schnittstellen laden.
			// F�r verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName( "com.mysql.jdbc.Driver" );

			// Verbindung zur ODBC-Datenbank 'sakila' herstellen.
			// Es wird die JDBC-ODBC-Brücke verwendet.
			conn = DriverManager.getConnection( "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + database + "?" + "user="
					+ dbUser + "&" + "password=" + dbPassword );
		}
		catch ( ClassNotFoundException e )
		{
			throw new RuntimeException( "Treiber nicht gefunden", e );
		}
		catch ( SQLException e )
		{
			throw new RuntimeException( "Connect nicht moeglich", e );
		}
	}

	public static Connection getInstance()
	{
		if ( conn == null ) new MySQLConnection();
		return conn;
	}
}
