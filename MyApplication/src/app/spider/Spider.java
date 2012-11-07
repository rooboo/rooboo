package app.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import app.db.MySQLConnection;
import app.db.Website;

public class Spider
{

	public Spider()
	{
		// TODO Auto-generated constructor stub
	}

	public void run()
	{
		/*
		 * webseiten holen
		 * über jede webseite laufen
		 * html inhalt holen
		 * urls aus html extrahieren
		 * urls abspeichern
		 */
		List <Website> websites = MySQLConnection.getInstance().getWebsites();
		for ( Website website : websites )
		{
			String html = "";
			try
			{
				// Create a URL for the desired page
				URL url = new URL( website.getUrl() );

				// Read all the text returned by the server
				BufferedReader in = new BufferedReader( new InputStreamReader( url.openStream() ) );
				String str;
				while ( ( str = in.readLine() ) != null )
				{
					html += str;
				}

				in.close();

				Document doc = Jsoup.parse( html );
				Elements LinkElems = doc.getElementsByTag( "a" );
				Set <String> links = new HashSet <String>( LinkElems.size() );

				for ( Element linkElem : LinkElems )
				{
					String link = linkElem.attr( "href" );
					if ( UrlValidator.getInstance().isValid( link ) )
					{
						System.out.println( link );
						links.add( link );
					}
				}

				if ( StringUtils.isNotBlank( html ) )
				{
					// HTMLDocument.Iterator iter = htmlDoc.getIterator(HTML.Tag.P);
				}

			}
			catch ( MalformedURLException e )
			{
				throw new RuntimeException( "URL malformed", e );
			}
			catch ( IOException e )
			{
				throw new RuntimeException( "I/O Error" );
			}
		}
	}
}
