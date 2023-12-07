package ch.weinetz.jraptor.filter;

import java.util.Set;

import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;

public class GtfsFeedFilter {
	GtfsFeed feed;
	
	// Constructor
	public GtfsFeedFilter(GtfsFeed feed) {
		this.feed = feed;
	}
	
	// Feed filters
	public GtfsFeed filterFeedByDates(Set<GtfsDate> dates) {
		// Calendars and CalendarDates
		this.feed.setGtfsCalendars(
				GtfsTableFilter.getCalendarsAllAtDates(
						this.feed.getAllGtfsCalendars(), 
						dates)
				);
		this.feed.setGtfsCalendarDates(
				GtfsTableFilter.getCalendarDatesAtDates(
						this.feed.getAllGtfsCalendarDates(), 
						dates)
				);
		// Trips
		this.feed.setGtfsTrips(
				GtfsTableFilter.getTripsAtDates(
						this.feed.getAllGtfsTrips(), 
						this.feed.getAllGtfsCalendars(), 
						this.feed.getAllGtfsCalendarDates(), 
						dates)
				);
		// Routes
		this.feed.setGtfsRoutes(
				GtfsTableFilter.getRoutesByTrips(
						this.feed.getAllGtfsRoutes(),
						this.feed.getAllGtfsTrips())
				);
		// StopTimes
		this.feed.setGtfsStopTimes(
				GtfsTableFilter.getStopTimesByTrips(
						this.feed.getAllGtfsStopTimes(), 
						this.feed.getAllGtfsTrips())
				);
		// Stops
		this.feed.setGtfsStops(
				GtfsTableFilter.getStopsByStopTimes(
						this.feed.getAllGtfsStops(),
						this.feed.getAllGtfsStopTimes())
				);
		// Transfers
		this.feed.setGtfsTransfers(
				GtfsTableFilter.getTransfersByStops(
						this.feed.getAllGtfsTransfers(), 
						this.feed.getAllGtfsStops())
				);
		
		return this.feed;
	}
	
	
	
	// Getter
	public GtfsFeed getFeed() {
		return this.feed;
	}
}
