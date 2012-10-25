package app.main;

import java.util.List;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import app.spider.Spider;
import app.table.WebsiteTableViewer;

public class MainApplication extends ApplicationWindow
{
	public static MainApplication	mainWindow;
	// AddEntryAction addEntryAction;
	List													entries;
	private TableViewer						viewer;

	public MainApplication()
	{
		super( null );
		mainWindow = this;
		addToolBar( SWT.NONE );
	}

	public void run()
	{
		setBlockOnOpen( true );
		open();
		Display.getCurrent().dispose();
	}

	/**
	 * Helper method for jUnit Test
	 */
	protected void runTest()
	{
		setBlockOnOpen( false );
		open();
		Display.getCurrent().dispose();
	}

	protected void configureShell( Shell shell )
	{
		super.configureShell( shell );
		shell.setSize( 600, 400 );
	}

	protected Control createContents( Composite parent )
	{
		viewer = new WebsiteTableViewer( parent );

		refresh();

		Spider spid = new Spider();
		spid.run();

		return viewer.getTable();
	}

	protected ToolBarManager createToolBarManager( int style )
	{
		ToolBarManager tbm = new ToolBarManager( style );
		// tbm.add( addEntryAction );
		return tbm;
	}

	private void refresh()
	{
		viewer.refresh();
		Table table = viewer.getTable();
		for ( int i = 0, n = table.getColumnCount(); i < n; i++ )
		{
			table.getColumn( i ).pack();
		}
	}

	public static void main( String[] args )
	{
		new MainApplication().run();
	}
}
