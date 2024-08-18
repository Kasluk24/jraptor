package ch.weinetz.jraptor.timetable;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.weinetz.jraptor.filter.GtfsFeedFilter;
import ch.weinetz.jraptor.filter.GtfsTableFilter;
import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class Extractor {
	private Logger logger;
	
	private final GtfsFeed feed;
	private GtfsTime analyseFrom = new GtfsTime("10:00:00");
	private GtfsTime analyseTo = new GtfsTime("15:00:00");
	
	public Extractor(GtfsFeed feed, GtfsDate date) {
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);
		
		// Filter feed to single date
		GtfsFeedFilter filter = new GtfsFeedFilter(feed);
		Set<GtfsDate> dateSet = new HashSet<>();
		dateSet.add(date);
		this.feed = filter.filterFeedByDates(dateSet);
		
		defaultAnalyseTime();
	}
	
	public void getByStops(GtfsStop stopA, GtfsStop stopB) {
		// Calculates the cycle between two stops
		Set<String> stopIds = new HashSet<>();
		stopIds.add(stopA.getStopId());
		stopIds.add(stopB.getStopId());
		Set<GtfsStopTime> stopTimes = feed.getStopTimesByStopIds(stopIds);
		
		
	}
	
	public void setAnalyseTime(GtfsTime fromTime, GtfsTime toTime) {
		this.analyseFrom = fromTime;
		this.analyseTo = toTime;
		logger.info(String.format("Reset analyse time to: %s - %s", analyseFrom, analyseTo));
		
	}
	
	private void defaultAnalyseTime() {
		// Set default analyse time
		analyseFrom = new GtfsTime("10:00:00");
		analyseTo = new GtfsTime("15:00:00");
		logger.info(String.format("Set analyse time to default: %s - %s", analyseFrom, analyseTo));
	}
}
