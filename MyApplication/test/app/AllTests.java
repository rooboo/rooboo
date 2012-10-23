package app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import app.db.MySQLConnectionTest;
import app.main.ShellTest;

public class AllTests extends TestCase
{

	public static Test suite()
	{
		TestSuite suite = new TestSuite( AllTests.class.getName() );
		// $JUnit-BEGIN$
		suite.addTestSuite( MySQLConnectionTest.class );
		suite.addTestSuite( ShellTest.class );
		// $JUnit-END$
		return suite;
	}

}
