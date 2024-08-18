package ch.weinetz.jraptor.filter;

import java.util.Set;
import java.util.stream.Collectors;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
/**
 * The feed filter allows filtering an entire feed
 * It will always return single tables and the feed will not be affected
 * For filtering only a single table see class @GtfsTableFilter
 * @author Lukas Gafner
 */
public class GtfsFeedFilter {
	private GtfsFeed feed;
	private GtfsTableFilter gtfsTableFilter;
	
	// Constructor
	public GtfsFeedFilter(GtfsFeed feed) {
		this.feed = feed;
		this.gtfsTableFilter = new GtfsTableFilter(this.feed);
	}
	
	// Feed filters
	public GtfsFeed filterFeedByDates(Set<GtfsDate> dates) {
		// Calendars
		this.feed.setGtfsCalendars(
				gtfsTableFilter.getCalendarsAllAtDates(dates));
		// CalendarDates
		this.feed.setGtfsCalendarDates(
				gtfsTableFilter.getCalendarDatesAtDates(dates));
		// Trips
		this.feed.setGtfsTrips(
				gtfsTableFilter.getTripsAtDates(dates));
		// Routes
		this.feed.setGtfsRoutes(
				gtfsTableFilter.getRoutesByTrips(this.feed.getAllGtfsTrips()));
		// StopTimes
		this.feed.setGtfsStopTimes(
				gtfsTableFilter.getStopTimesByTrips(this.feed.getAllGtfsTrips()));
		// Stops
		this.feed.setGtfsStops(
				gtfsTableFilter.getStopsByStopTimes(this.feed.getAllGtfsStopTimes()));
		// Transfers
		this.feed.setGtfsTransfers(
				gtfsTableFilter.getTransfersByStops(this.feed.getAllGtfsStops()));
		// Agencies
		this.feed.setGtfsAgencies(
				gtfsTableFilter.getAgenciesByRoutes(this.feed.getAllGtfsRoutes()));
		
		return this.feed;
	}
	
	public GtfsFeed filterFeedByAgencies(Set<GtfsAgency> agencies) {
		// Agencies
		this.feed.setGtfsAgencies(
				this.feed.getAllGtfsAgencies().stream()
					.filter(a -> agencies.contains(a))
					.collect(Collectors.toSet())
				);
		// Routes
		this.feed.setGtfsRoutes(
				gtfsTableFilter.getRoutesByAgencies(this.feed.getAllGtfsAgencies()));
		// Trips
		this.feed.setGtfsTrips(
				gtfsTableFilter.getTripsByRoutes(this.feed.getAllGtfsRoutes()));
		// StopTimes
		this.feed.setGtfsStopTimes(
				gtfsTableFilter.getStopTimesByTrips(this.feed.getAllGtfsTrips()));
		// Stops
		this.feed.setGtfsStops(
				gtfsTableFilter.getStopsByStopTimes(this.feed.getAllGtfsStopTimes()));
		// Transfers
		this.feed.setGtfsTransfers(
				gtfsTableFilter.getTransfersByStops(this.feed.getAllGtfsStops()));
		// Calendars
		this.feed.setGtfsCalendars(
				gtfsTableFilter.getCalendarsByTrips(this.feed.getAllGtfsTrips()));
		// CalendarDates
		this.feed.setGtfsCalendarDates(
				gtfsTableFilter.getCalendarDatesByCalendars(this.feed.getAllGtfsCalendars()));
		
		return this.feed;
	}
	
	
	// Getter
	public GtfsFeed getFeed() {
		return this.feed;
	}
}
