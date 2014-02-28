package mdc.collab.nightreader.util;

public class AudioFileInfo
{
	public String rawPath;
	public String artist;
	public String album;
	public String title;
	public String year;
	public String albumArtUri;
	public String albumArt;
	public int duration;
	
	@Override
	public String toString()
	{
		return (title == null) ? "Unknown Audio File" : title;
	}
}
