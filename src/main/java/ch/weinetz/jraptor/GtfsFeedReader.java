package ch.weinetz.jraptor;

import java.util.LinkedList;
import java.util.List;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsTableData;

public class GtfsFeedReader  {
	// Fields
	private String gtfsFeedDirectory;
	
	// Constructor
	public GtfsFeedReader(String gtfsFeedDirectory) {
		this.gtfsFeedDirectory = gtfsFeedDirectory;		
	}
	
	// Public methods
	public GtfsFeed readFeed() {
		GtfsFeed gtfsFeed = new GtfsFeed();
		
		//TODO:
		
		return gtfsFeed;
		
	}
	
	public GtfsFeed readFeedParallel() {
		GtfsFeed gtfsFeed = new GtfsFeed();
		
		//TODO:
		
		return gtfsFeed;
		
	}
		
}
