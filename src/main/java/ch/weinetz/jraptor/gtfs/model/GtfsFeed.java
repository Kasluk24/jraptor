package ch.weinetz.jraptor.gtfs.model;

import java.util.Set;

public class GtfsFeed {
	private Set<GtfsAgency> gtfsAgencies;
	private Set<GtfsCalendar> gtfsCalendars;
	private Set<GtfsCalendarDate> gtfsCalendarDates;
	private Set<GtfsFrequency> gtfsFrequencies;
	private Set<GtfsRoute> gtfsRoutes;
	private Set<GtfsStop> gtfsStops;
	private Set<GtfsStopTime> gtfsStopTimes;
	private Set<GtfsTransfer> gtfsTransfers;
	private Set<GtfsTrip> gtfsTrips;
	
	public GtfsFeed() {
		
	}
	
}
