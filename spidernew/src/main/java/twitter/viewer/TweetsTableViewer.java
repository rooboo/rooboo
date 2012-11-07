package twitter.viewer;

import java.util.List;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import twitter.util.TwitterUtils;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Tweet;

public class TweetsTableViewer extends TreeViewer
{

	public TweetsTableViewer( Composite parent )
	{
		super( parent );
		init();

	}

	private void init()
	{
		setContentProvider( new TwitterContentProvider() );
		setLabelProvider( new TwitterLabelProvider() );

		Tree tree = getTree();
		new TreeColumn( tree, SWT.LEFT ).setText( "Name" );
		new TreeColumn( tree, SWT.LEFT ).setText( "Text" );

		tree.setHeaderVisible( true );
		tree.setLinesVisible( true );

		Trends trends = TwitterUtils.getCurrentTrends();

		setInput( trends );
	}

	private class TwitterContentProvider implements ITreeContentProvider
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

		public Object[] getChildren( Object element )
		{
			if ( element instanceof Trend )
			{
				Trend trend = ( Trend ) element;

				List <Tweet> tweets = TwitterUtils.getTweetsFromTrend( trend );
				return ( Tweet[] ) tweets.toArray( new Tweet[ tweets.size() ] );
			}
			return null;
		}

		public Object getParent( Object element )
		{
			// TODO Auto-generated method stub
			return null;
		}

		public boolean hasChildren( Object element )
		{
			if ( element instanceof Trend )
			{
				return true;
			}
			return false;
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
				switch ( columnIndex )
				{
					case 0:
						return trend.getName();
					case 1:
						return trend.getUrl().toString();
					default:
						break;
				}
			}
			if ( element instanceof Tweet )
			{
				Tweet tweet = ( Tweet ) element;
				switch ( columnIndex )
				{
					case 0:
						return tweet.getFromUserName();
					case 1:
						return tweet.getText();
					default:
						break;
				}
			}
			return null;
		}

	}
}
