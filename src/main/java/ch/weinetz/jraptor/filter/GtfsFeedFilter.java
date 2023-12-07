package ch.weinetz.jraptor.filter;

import java.util.Set;
import java.util.stream.Collectors;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
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
		// Calendars
		this.feed.setGtfsCalendars(
				GtfsTableFilter.getCalendarsAllAtDates(
						this.feed.getAllGtfsCalendars(), 
						dates)
				);
		// CalendarDates
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
		// Agencies
		this.feed.setGtfsAgencies(
				GtfsTableFilter.getAgenciesByRoutes(
						this.feed.getAllGtfsAgencies(), 
						this.feed.getAllGtfsRoutes())
				);
		
		return this.feed;
	}
	
	public GtfsFeed filterFeedByAgencies(Set<GtfsAgency> agencies) {
		// Agencies (are not only replaced, so the filter must not be a complete agency)
		this.feed.setGtfsAgencies(
				this.feed.getAllGtfsAgencies().stream()
					.filter(a -> agencies.contains(a))
					.collect(Collectors.toSet())
			);
		// Routes
		this.feed.setGtfsRoutes(
				GtfsTableFilter.getRoutesByAgencies(
						this.feed.getAllGtfsRoutes(),
						this.feed.getAllGtfsAgencies())
				);
		// Trips
		this.feed.setGtfsTrips(
				GtfsTableFilter.getTripsByRoutes(
						this.feed.getAllGtfsTrips(), 
						this.feed.getAllGtfsRoutes())
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
		// Calendars
		this.feed.setGtfsCalendars(
				GtfsTableFilter.getCalendarsByTrips(
						this.feed.getAllGtfsCalendars(), 
						this.feed.getAllGtfsTrips())
				);
		// CalendarDates
		this.feed.setGtfsCalendarDates(
				GtfsTableFilter.getCalendarDatesByCalendars(
						this.feed.getAllGtfsCalendarDates(), 
						this.feed.getAllGtfsCalendars())
				);
		
		return this.feed;
	}
	
	
	// Getter
	public GtfsFeed getFeed() {
		return this.feed;
	}
}
