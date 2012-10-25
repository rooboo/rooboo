package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnection
{

	private static MySQLConnection	instance		= null;
	private static Connection				conn				= null;

	// Hostname
	private static final String			dbHost			= "localhost";

	// Port -- Standard: 3306
	private static final String			dbPort			= "3306";

	// Datenbankname
	private static final String			database		= "test";

	// Datenbankuser
	private static final String			dbUser			= "root";

	// Datenbankpasswort
	private static final String			dbPassword	= "mysql";

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

	public static MySQLConnection getInstance()
	{
		if ( instance == null ) instance = new MySQLConnection();
		return instance;
	}

	public List <Website> getWebsites()
	{
		try
		{
			List <Website> websites = new ArrayList <Website>();

			Statement stmt = conn.createStatement();

			ResultSet result = stmt.executeQuery( "select name, url from website" );

			while ( result.next() )
			{
				websites.add( new Website( result.getString( 1 ), result.getString( 2 ) ) );
			}

			result.close();
			stmt.close();

			return websites;
		}
		catch ( SQLException e )
		{
			throw new RuntimeException( e );
		}
	}
}
