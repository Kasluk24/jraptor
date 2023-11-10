package ch.weinetz.jraptor.gtfs.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GtfsFeed {
	private Set<GtfsAgency> gtfsAgencies = new HashSet<>();
	private Set<GtfsCalendar> gtfsCalendars = new HashSet<>();
	private Set<GtfsCalendarDate> gtfsCalendarDates = new HashSet<>();
	private Set<GtfsFrequency> gtfsFrequencies = new HashSet<>();
	private Set<GtfsRoute> gtfsRoutes = new HashSet<>();
	private Set<GtfsStop> gtfsStops = new HashSet<>();
	private Set<GtfsStopTime> gtfsStopTimes = new HashSet<>();
	private Set<GtfsTransfer> gtfsTransfers = new HashSet<>();
	private Set<GtfsTrip> gtfsTrips = new HashSet<>();
	
	// Default constructor
	public GtfsFeed() {}

	// Constructor set all fields
	public GtfsFeed(Set<GtfsAgency> gtfsAgencies, Set<GtfsCalendar> gtfsCalendars,
			Set<GtfsCalendarDate> gtfsCalendarDates, Set<GtfsFrequency> gtfsFrequencies, Set<GtfsRoute> gtfsRoutes,
			Set<GtfsStop> gtfsStops, Set<GtfsStopTime> gtfsStopTimes, Set<GtfsTransfer> gtfsTransfers,
			Set<GtfsTrip> gtfsTrips) {
		if (gtfsAgencies != null) this.gtfsAgencies = gtfsAgencies;
		if (gtfsCalendars != null) this.gtfsCalendars = gtfsCalendars;
		if (gtfsCalendarDates != null) this.gtfsCalendarDates = gtfsCalendarDates;
		if (gtfsFrequencies != null) this.gtfsFrequencies = gtfsFrequencies;
		if (gtfsRoutes != null) this.gtfsRoutes = gtfsRoutes;
		if (gtfsStops != null) this.gtfsStops = gtfsStops;
		if (gtfsStopTimes != null) this.gtfsStopTimes = gtfsStopTimes;
		if (gtfsTransfers != null) this.gtfsTransfers = gtfsTransfers;
		if (gtfsTrips != null) this.gtfsTrips = gtfsTrips;
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
	
	@Override
	public String toString() {
		return "GtfsFeed with:\n"
				+ gtfsAgencies.size() + " Agencies\n"
				+ gtfsCalendars.size() + " Calendars\n"
				+ gtfsCalendarDates.size() + " CalendarDates\n"
				+ gtfsFrequencies.size() + " Frequencies\n"
				+ gtfsRoutes.size() + " Routes\n"
				+ gtfsStops.size() + " Stops\n"
				+ gtfsStopTimes.size() + " StopTimes\n"
				+ gtfsTransfers.size() + " Transfers\n"
				+ gtfsTrips.size() + " Trips";
	}
	
}
