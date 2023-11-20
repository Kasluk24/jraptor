package ch.weinetz.jraptor.utils;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsFrequency;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTransfer;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsFeedSqliteReader {
	// Fields
	private String gtfsDatabasePath;
	
	// Constructor
	public GtfsFeedSqliteReader(String gtfsDatabasePath) {
		this.gtfsDatabasePath = gtfsDatabasePath;		
	}
	

	// Public methods
	public GtfsFeed readFeed() {
		GtfsFeed gtfsFeed = new GtfsFeed();
		
		gtfsFeed.setGtfsAgencies(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsAgency.class).getGtfsTable());
		gtfsFeed.setGtfsCalendars(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsCalendar.class).getGtfsTable());
		gtfsFeed.setGtfsCalendarDates(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsCalendarDate.class).getGtfsTable());
		gtfsFeed.setGtfsFrequencies(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsFrequency.class).getGtfsTable());
		gtfsFeed.setGtfsRoutes(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsRoute.class).getGtfsTable());
		gtfsFeed.setGtfsStops(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsStop.class).getGtfsTable());
		gtfsFeed.setGtfsStopTimes(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsStopTime.class).getGtfsTable());
		gtfsFeed.setGtfsTransfers(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsTransfer.class).getGtfsTable());
		gtfsFeed.setGtfsTrips(new GtfsTableSqliteReader<>(gtfsDatabasePath, GtfsTrip.class).getGtfsTable());
		
		return gtfsFeed;
	}
}
