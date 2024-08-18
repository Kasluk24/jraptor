package ch.weinetz.jraptor.timetable;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
		// Get the stop times at the stop A within the analyse time span
		Set<GtfsStopTime> fromStopTimes = feed.getStopTimesByStopId(stopA.getStopId());
		fromStopTimes = fromStopTimes.stream()
				.filter(st -> st.getDepartureTime().afterEquals(analyseFrom) && 
						st.getDepartureTime().before(analyseTo))
				.collect(Collectors.toSet());
		
		// Get the stop times at the stop B within the analyse time span
		Set<GtfsStopTime> toStopTimes = feed.getStopTimesByStopId(stopB.getStopId());
		toStopTimes = toStopTimes.stream()
				.filter(st -> st.getDepartureTime().afterEquals(analyseFrom) && 
						st.getDepartureTime().before(analyseTo))
				.collect(Collectors.toSet());
		
		// Get the trips by the filtered tripIds
		Set<String> tripIds = new HashSet<>();
		for (GtfsStopTime fromStopTime : fromStopTimes) {
			for (GtfsStopTime toStopTime : toStopTimes) {
				if (fromStopTime.getTripId().equals(toStopTime.getTripId()) && 
						fromStopTime.getStopSequence() < toStopTime.getStopSequence()) {
					tripIds.add(fromStopTime.getTripId());
				}
			}
		}
		
		Set<GtfsTrip> trips = feed.getTripsByIds(tripIds);
		
		trips.forEach(t -> System.out.println(t));
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
