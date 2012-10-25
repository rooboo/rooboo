package app.table;

import java.util.List;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import app.db.MySQLConnection;
import app.db.Website;

public class WebsiteTableViewer extends TableViewer
{

	public WebsiteTableViewer( Composite parent )
	{
		super( parent );
		init();

	}

	private void init()
	{
		setContentProvider( new WebsiteContentProvider() );
		setLabelProvider( new WebsiteLabelProvider() );

		Table table = getTable();
		new TableColumn( table, SWT.LEFT ).setText( "Name" );
		new TableColumn( table, SWT.LEFT ).setText( "URL" );
		// new TableColumn(table, SWT.LEFT).setText("E-mail Address");
		table.setHeaderVisible( true );
		table.setLinesVisible( true );

		// Get iput from DB
		List <Website> websites = MySQLConnection.getInstance().getWebsites();

		setInput( websites );
	}

	private class WebsiteContentProvider implements IStructuredContentProvider
	{
		@Override
		public Object[] getElements( Object inputElement )
		{
			if ( inputElement instanceof List )
			{
				@SuppressWarnings( "unchecked" )
				List <Website> websites = ( List <Website> ) inputElement;
				return websites.toArray( new Website[ websites.size() ] );
			}
			return null;
		}

		@Override
		public void dispose()
		{
		}

		@Override
		public void inputChanged( Viewer viewer, Object oldInput, Object newInput )
		{

		}

	}

	private class WebsiteLabelProvider implements ITableLabelProvider
	{

		@Override
		public void addListener( ILabelProviderListener listener )
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void dispose()
		{
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isLabelProperty( Object element, String property )
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener( ILabelProviderListener listener )
		{
			// TODO Auto-generated method stub

		}

		@Override
		public Image getColumnImage( Object element, int columnIndex )
		{
			return null;
		}

		@Override
		public String getColumnText( Object element, int columnIndex )
		{
			if ( element instanceof Website )
			{
				Website website = ( Website ) element;
				if ( columnIndex == 0 )
				{
					return website.getName();
				}
				else if ( columnIndex == 1 )
				{
					return website.getUrl().toString();
				}
			}
			return null;
		}
	}
}
