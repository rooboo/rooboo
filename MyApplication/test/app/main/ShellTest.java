package app.main;

import junit.framework.TestCase;
import org.junit.Test;

public class ShellTest extends TestCase
{

	@Test
	public void testAppOpen()
	{
		MainApplication app = new MainApplication();
		app.runTest();
		app.close();
	}

}
