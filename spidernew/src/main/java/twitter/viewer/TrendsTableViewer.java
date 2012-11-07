package twitter.viewer;

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
import twitter.util.TwitterUtils;
import twitter4j.Trend;
import twitter4j.Trends;

public class TrendsTableViewer extends TableViewer
{

	public TrendsTableViewer( Composite parent )
	{
		super( parent );
		init();

	}

	private void init()
	{
		setContentProvider( new TwitterContentProvider() );
		setLabelProvider( new TwitterLabelProvider() );

		Table table = getTable();
		new TableColumn( table, SWT.LEFT ).setText( "Name" );
		new TableColumn( table, SWT.LEFT ).setText( "URL" );
		// new TableColumn(table, SWT.LEFT).setText("E-mail Address");
		table.setHeaderVisible( true );
		table.setLinesVisible( true );

		Trends trends = TwitterUtils.getCurrentTrends();

		setInput( trends );
	}

	private class TwitterContentProvider implements IStructuredContentProvider
	{
		public Object[] getElements( Object inputElement )
		{
			if ( inputElement instanceof Trends )
			{
				Trends trends = ( Trends ) inputElement;
				return trends.getTrends();
			}
			return null;
		}

		public void dispose()
		{
		}

		public void inputChanged( Viewer viewer, Object oldInput, Object newInput )
		{

		}

	}

	private class TwitterLabelProvider implements ITableLabelProvider
	{

		public void addListener( ILabelProviderListener listener )
		{
			// TODO Auto-generated method stub

		}

		public void dispose()
		{
			// TODO Auto-generated method stub

		}

		public boolean isLabelProperty( Object element, String property )
		{
			// TODO Auto-generated method stub
			return false;
		}

		public void removeListener( ILabelProviderListener listener )
		{
			// TODO Auto-generated method stub

		}

		public Image getColumnImage( Object element, int columnIndex )
		{
			return null;
		}

		public String getColumnText( Object element, int columnIndex )
		{
			if ( element instanceof Trend )
			{
				Trend trend = ( Trend ) element;
				if ( columnIndex == 0 )
				{
					return trend.getName();
				}
				else if ( columnIndex == 1 )
				{
					return trend.getUrl().toString();
				}
			}
			return null;
		}
	}
}
