package ch.weinetz.jraptor.gtfs.model;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	// Default constructor
	public GtfsFeed() {}

	// Constructor set all fields
	public GtfsFeed(Set<GtfsAgency> gtfsAgencies, Set<GtfsCalendar> gtfsCalendars,
			Set<GtfsCalendarDate> gtfsCalendarDates, Set<GtfsFrequency> gtfsFrequencies, Set<GtfsRoute> gtfsRoutes,
			Set<GtfsStop> gtfsStops, Set<GtfsStopTime> gtfsStopTimes, Set<GtfsTransfer> gtfsTransfers,
			Set<GtfsTrip> gtfsTrips) {
		this.gtfsAgencies = gtfsAgencies;
		this.gtfsCalendars = gtfsCalendars;
		this.gtfsCalendarDates = gtfsCalendarDates;
		this.gtfsFrequencies = gtfsFrequencies;
		this.gtfsRoutes = gtfsRoutes;
		this.gtfsStops = gtfsStops;
		this.gtfsStopTimes = gtfsStopTimes;
		this.gtfsTransfers = gtfsTransfers;
		this.gtfsTrips = gtfsTrips;
	}
	
	
	// Get full sets unmodifiable
	public Set<GtfsAgency> getAllGtfsAgencies() {
		return Collections.unmodifiableSet(this.gtfsAgencies);
	}
	public Set<GtfsCalendar> getAllGtfsCalendars() {
		return Collections.unmodifiableSet(this.gtfsCalendars);
	}
	public Set<GtfsCalendarDate> getAllGtfsCalendarDates() {
		return Collections.unmodifiableSet(this.gtfsCalendarDates);
	}
	public Set<GtfsFrequency> getAllGtfsFrequencies() {
		return Collections.unmodifiableSet(this.gtfsFrequencies);
	}
	public Set<GtfsRoute> getAllGtfsRoutes() {
		return Collections.unmodifiableSet(this.gtfsRoutes);
	}
	public Set<GtfsStop> getAllGtfsStops() {
		return Collections.unmodifiableSet(this.gtfsStops);
	}
	public Set<GtfsStopTime> getAllGtfsStopTimes() {
		return Collections.unmodifiableSet(this.gtfsStopTimes);
	}
	public Set<GtfsTransfer> getAllGtfsTransfers() {
		return Collections.unmodifiableSet(this.gtfsTransfers);
	}
	public Set<GtfsTrip> getAllGtfsTrips() {
		return Collections.unmodifiableSet(this.gtfsTrips);
	}
	
	// Get objects as set by attributes
	// Agency
	public Set<GtfsAgency> getAgenciesByIds(Set<String> agencyIds) {
		return this.gtfsAgencies.stream()
				.filter(a -> agencyIds.contains(a.getAgencyId()))
				.collect(Collectors.toSet());
	}
	// Calendar
	// CalendarDate
	// Frequency
	// Route
	public Set<GtfsRoute> getRoutesByIds(Set<String> routeIds) {
		return this.gtfsRoutes.stream()
				.filter(r -> routeIds.contains(r.getRouteId()))
				.collect(Collectors.toSet());
	}
	// Stop
	public Set<GtfsStop> getStopsByIds(Set<String> stopIds) {
		return this.gtfsStops.stream()
				.filter(s -> stopIds.contains(s.getStopId()))
				.collect(Collectors.toSet());
	}
	// StopTime
	public Set<GtfsStopTime> getStopTimesByTripIds(Set<String> tripIds) {
			return this.gtfsStopTimes.stream()
					.filter(s -> tripIds.contains(s.getTripId()))
					.collect(Collectors.toSet());
	}
	public Set<GtfsStopTime> getStopTimesByStopIds(Set<String> stopIds) {
		return this.gtfsStopTimes.stream()
				.filter(s -> stopIds.contains(s.getStopId()))
				.collect(Collectors.toSet());
	}
	// Transfer
	public Set<GtfsTransfer> getTransfersByFromStopIds(Set<String> fromStopIds) {
		return this.gtfsTransfers.stream()
				.filter(t -> fromStopIds.contains(t.getFromStopId()))
				.collect(Collectors.toSet());
	}
	public Set<GtfsTransfer> getTransfersByToStopIds(Set<String> toStopIds) {
		return this.gtfsTransfers.stream()
				.filter(t -> toStopIds.contains(t.getToStopId()))
				.collect(Collectors.toSet());
	}	
	// Trip
	public Set<GtfsTrip> getTripsByIds(Set<String> tripIds) {
		return this.gtfsTrips.stream()
				.filter(t -> tripIds.contains(t.getTripId()))
				.collect(Collectors.toSet());
	}
	
	// Add Gtfs objects
	public void addGtfsAgency(GtfsAgency gtfsAgency) {
		this.gtfsAgencies.add(gtfsAgency);
	}
	public void addGtfsCalendar(GtfsCalendar gtfsCalendar) {
		this.gtfsCalendars.add(gtfsCalendar);
	}
	public void addGtfsCalendarDate(GtfsCalendarDate gtfsCalendarDate) {
		this.gtfsCalendarDates.add(gtfsCalendarDate);
	}
	public void addGtfsFrequency(GtfsFrequency gtfsFrequency) {
		this.gtfsFrequencies.add(gtfsFrequency);
	}
	public void addGtfsRoute(GtfsRoute gtfsRoute) {
		this.gtfsRoutes.add(gtfsRoute);
	}
	public void addGtfsStop(GtfsStop gtfsStop) {
		this.gtfsStops.add(gtfsStop);
	}
	public void addGtfsStopTime(GtfsStopTime gtfsStopTime) {
		this.gtfsStopTimes.add(gtfsStopTime);
	}
	public void addGtfsTransfer(GtfsTransfer gtfsTransfer) {
		this.gtfsTransfers.add(gtfsTransfer);
	}
	public void addGtfsTrip(GtfsTrip gtfsTrip) {
		this.gtfsTrips.add(gtfsTrip);
	}
	
}
