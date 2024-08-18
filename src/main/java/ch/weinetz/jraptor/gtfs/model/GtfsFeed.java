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
	
	// Get objects as set by attributes (simple)
	// Agency
	public Set<GtfsAgency> getAgenciesByIds(Set<String> agencyIds) {
		return this.gtfsAgencies.stream()
				.filter(a -> agencyIds.contains(a.getAgencyId()))
				.collect(Collectors.toSet());
	}
	// Calendar
	public Set<GtfsCalendar> getCalendarsByEndDates(Set<GtfsDate> endDates) {
		return this.gtfsCalendars.stream()
				.filter(c -> endDates.contains(c.getEndDate()))
				.collect(Collectors.toSet());
	}
	public Set<GtfsCalendar> getCalendarsByStartDates(Set<GtfsDate> startDates) {
		return this.gtfsCalendars.stream()
				.filter(c -> startDates.contains(c.getStartDate()))
				.collect(Collectors.toSet());
	}
	// CalendarDate
	public Set<GtfsCalendarDate> getCalendarDatesByDates(Set<GtfsDate> dates) {
		return this.gtfsCalendarDates.stream()
				.filter(c -> dates.contains(c.getDate()))
				.collect(Collectors.toSet());
	}
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
	public Set<GtfsStopTime> getStopTimesByStopId(String stopId) {
		return this.gtfsStopTimes.stream()
				.filter(s -> stopId.equals(s.getStopId()))
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
	
	// Set Gtfs Tables	
	public void setGtfsAgencies(Set<GtfsAgency> gtfsAgencies) {
		this.gtfsAgencies = gtfsAgencies;
	}
	public void setGtfsCalendars(Set<GtfsCalendar> gtfsCalendars) {
		this.gtfsCalendars = gtfsCalendars;
	}
	public void setGtfsCalendarDates(Set<GtfsCalendarDate> gtfsCalendarDates) {
		this.gtfsCalendarDates = gtfsCalendarDates;
	}
	public void setGtfsFrequencies(Set<GtfsFrequency> gtfsFrequencies) {
		this.gtfsFrequencies = gtfsFrequencies;
	}
	public void setGtfsRoutes(Set<GtfsRoute> gtfsRoutes) {
		this.gtfsRoutes = gtfsRoutes;
	}
	public void setGtfsStops(Set<GtfsStop> gtfsStops) {
		this.gtfsStops = gtfsStops;
	}
	public void setGtfsStopTimes(Set<GtfsStopTime> gtfsStopTimes) {
		this.gtfsStopTimes = gtfsStopTimes;
	}
	public void setGtfsTransfers(Set<GtfsTransfer> gtfsTransfers) {
		this.gtfsTransfers = gtfsTransfers;
	}
	public void setGtfsTrips(Set<GtfsTrip> gtfsTrips) {
		this.gtfsTrips = gtfsTrips;
	}
	
	// Set automatic by class type
	@SuppressWarnings("unchecked")
	public <T extends GtfsTableData> void setGtfsData(Set<T> table) {
		if (table != null && !table.isEmpty()) {
			GtfsTableData element = table.stream().findAny().get();
						
			if (element instanceof GtfsAgency) {
				this.gtfsAgencies = (Set<GtfsAgency>) table;
			}
			if (element instanceof GtfsCalendar) {
				this.gtfsCalendars = (Set<GtfsCalendar>) table;
			}
			if (element instanceof GtfsCalendarDate) {
				this.gtfsCalendarDates = (Set<GtfsCalendarDate>) table;
			}
			if (element instanceof GtfsFrequency) {
				this.gtfsFrequencies = (Set<GtfsFrequency>) table;
			}
			if (element instanceof GtfsRoute) {
				this.gtfsRoutes = (Set<GtfsRoute>) table;
			}
			if (element instanceof GtfsStop) {
				this.gtfsStops = (Set<GtfsStop>) table;
			}
			if (element instanceof GtfsStopTime) {
				this.gtfsStopTimes = (Set<GtfsStopTime>) table;
			}
			if (element instanceof GtfsTransfer) {
				this.gtfsTransfers = (Set<GtfsTransfer>) table;
			}
			if (element instanceof GtfsTrip) {
				this.gtfsTrips = (Set<GtfsTrip>) table;
			}			
		}
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
	
	public boolean isEmpty() {
		if ((gtfsAgencies.size() +
				gtfsCalendars.size() +
				gtfsCalendarDates.size() +
				gtfsFrequencies.size() +
				gtfsRoutes.size() +
				gtfsStops.size() +
				gtfsStopTimes.size() + 
				gtfsTransfers.size() + 
				gtfsTrips.size()
		) == 0) {
			return true;
		} else {
			return false;
		}
	}
}
