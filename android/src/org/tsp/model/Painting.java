package org.tsp.model;

public class Painting {

	private String title = null;
	private String artist = null;
	private String pid = null;
	
	public Painting(String pid, String title, String artist)
	{
		this.title = title;
		this.pid = pid;
		this.artist = artist;
	}
	
	public String artist()
	{
		return artist;
	}
	
	public Painting artist(String artist)
	{
		this.artist = artist;
		return this;
	}
	
	public String title()
	{
		return title;
	}
	
	public Painting title(String title)
	{
		this.title = title;
		return this;
	}
	
	public String pid()
	{
		return pid;
	}
	
	public Painting pid(String pid)
	{
		this.pid = pid;
		return this;
	}
	
}
