package app.db;

import org.apache.commons.lang3.StringUtils;

public class Website
{
	private String	name;
	private String	url;

	public Website( String name, String url )
	{
		if ( StringUtils.isBlank( name ) || StringUtils.isBlank( url ) )
		{
			throw new IllegalArgumentException( "Name and URL must not be blank" );
		}

		this.setName( name );
		this.setUrl( url );
	}

	public String getName()
	{
		return name;
	}

	private void setName( String name )
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	private void setUrl( String url )
	{
		this.url = url;
	}

	@Override
	public String toString()
	{
		return getName() + ", " + getUrl();
	}

}
