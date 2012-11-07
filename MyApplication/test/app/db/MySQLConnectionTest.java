package app.db;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class MySQLConnectionTest extends TestCase
{

	@Test
	public void test()
	{
		try
		{
			MySQLConnection.getInstance();
		}
		catch ( Exception e )
		{
			fail( "MYSQL Connection test failed: " + e.getMessage() );
			System.out.println( e );
			Assert.assertArrayEquals( expecteds, actuals )
		}
	}
}
