package twitter.util;

import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterUtils
{

	public static Trends getCurrentTrends()
	{
		Twitter t = new TwitterFactory().getInstance();
		try
		{

			return t.getLocationTrends( 23424829 );
		}
		catch ( TwitterException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List <Tweet> getTweetsFromTrend( Trend trend )
	{

		Twitter t = new TwitterFactory().getInstance();
		try
		{
			Query q = new Query( trend.getQuery() );
			q.setRpp( 100 );
			q.setResultType( Query.RECENT );

			QueryResult r = t.search( q );

			return r.getTweets();
		}
		catch ( TwitterException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
