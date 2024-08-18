package ch.weinetz.jraptor.timetable;

import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.utils.GtfsFeedFileReader;

public class TimetableTools {
	// public static void main for testing
	public static void main(String[] args) throws InterruptedException {
		GtfsFeedFileReader reader = new GtfsFeedFileReader("data/gtfs_fp2023_2023-10-11_04-15");
		GtfsFeed feed = reader.readFeedParallel();
	
		GtfsStop zizers = feed.getStopById("8509054");
		GtfsStop igis = feed.getStopById("8509055");
			
		Extractor timeTableExtractor = new Extractor(feed, new GtfsDate("20231123"));
		timeTableExtractor.getByStops(zizers, igis);
	}
}
