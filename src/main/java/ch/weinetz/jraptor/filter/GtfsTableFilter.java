package ch.weinetz.jraptor.filter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarDate;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarExceptionType;
import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTransfer;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

/**
 * The table filter allows filtering in specific tables
 * It will always return single tables and the feed will not be affected
 * For filtering an entire feed see class @GtfsFeedFilter
 * @author Lukas Gafner
 */
public class GtfsTableFilter {
	private final GtfsFeed feed;
	
	public GtfsTableFilter(GtfsFeed feed) {
		this.feed = feed;
	}
	
	// Agency
	public Set<GtfsAgency> getAgenciesByRoutes(Set<GtfsRoute> gtfsRoutes) {
		Set<String> agencyIds = gtfsRoutes.stream()
				.map(r -> r.getAgencyId())
				.distinct()
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsAgencies().stream()
				.filter(a -> agencyIds.contains(a.getAgencyId()))
				.collect(Collectors.toSet());
		
	}
	// Calendar
	// All calendars that intersect the given dates
	public Set<GtfsCalendar> getCalendarsAllAtDates(Set<GtfsDate> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(feed.getAllGtfsCalendars().stream()
					.filter(c -> date.between(c.getStartDate(), c.getEndDate()))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	// All calendars that only include the given dates
	public Set<GtfsCalendar> getCalendarsOnlyAtDates(Set<GtfsDate> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(feed.getAllGtfsCalendars().stream()
					.filter(c -> date.equals(c.getStartDate()) && date.equals(c.getEndDate()))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	// All calendars that intersect the given period
	public Set<GtfsCalendar> getCalendarsAllBetweenDates(Set<GtfsDate[]> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(feed.getAllGtfsCalendars().stream()
					.filter(c -> c.getEndDate().after(date[0]) || c.getStartDate().before(date[1]))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	// All calendars that only include the given period
	public Set<GtfsCalendar> getCalendarsOnlyBetweenDates(Set<GtfsDate[]> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(feed.getAllGtfsCalendars().stream()
					.filter(c -> c.getStartDate().after(date[0]) || c.getEndDate().before(date[1]))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	// All calendars by trips
	public Set<GtfsCalendar> getCalendarsByTrips(Set<GtfsTrip> gtfsTrips) {
		Set<String> serviceIds = gtfsTrips.stream()
				.map(t -> t.getServiceId())
				.distinct()
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsCalendars().stream()
				.filter(c -> serviceIds.contains(c.getServiceId()))
				.collect(Collectors.toSet());
	}
	
	// CalendarDate
	// All calendar dates that match the given dates
	public Set<GtfsCalendarDate> getCalendarDatesAtDates(Set<GtfsDate> dates) {
		Set<GtfsCalendarDate> calendarDates = new HashSet<>();
		dates.forEach(date -> {
			calendarDates.addAll(feed.getAllGtfsCalendarDates().stream()
					.filter(c -> c.getDate().equals(date))
					.collect(Collectors.toSet())
				);
		});
		return calendarDates;		
	}
	// All calendar dates that intersect the given period
	public Set<GtfsCalendarDate> getCalendarDatesBetweenDates(Set<GtfsDate[]> dates) {
		Set<GtfsCalendarDate> calendarDates = new HashSet<>();
		dates.forEach(date -> {
			calendarDates.addAll(feed.getAllGtfsCalendarDates().stream()
					.filter(c -> c.getDate().after(date[0]) && c.getDate().before(date[1]))
					.collect(Collectors.toSet())
				);
		});
		return calendarDates;
	}
	// All calendar dates by calendars
	public Set<GtfsCalendarDate> getCalendarDatesByCalendars(Set<GtfsCalendar> gtfsCalendars) {
		Set<String> serviceIds = gtfsCalendars.stream()
				.map(c -> c.getServiceId())
				.distinct()
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsCalendarDates().stream()
				.filter(c -> serviceIds.contains(c.getServiceId()))
				.collect(Collectors.toSet());
	}
	// Frequency
	// Route
	public Set<GtfsRoute> getRoutesByTrips(Set<GtfsTrip> gtfsTrips) {
		Set<String> routeIds = gtfsTrips.stream()
				.map(t -> t.getRouteId())
				.distinct()
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsRoutes().stream()
				.filter(r -> routeIds.contains(r.getRouteId()))
				.collect(Collectors.toSet());
	}
	public Set<GtfsRoute> getRoutesByAgencies(Set<GtfsAgency> gtfsAgencies) {
		Set<String> agencyIds = gtfsAgencies.stream()
				.map(a -> a.getAgencyId())
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsRoutes().stream()
				.filter(r -> agencyIds.contains(r.getAgencyId()))
				.collect(Collectors.toSet());
	}
	// Stop
	public Set<GtfsStop> getStopsByStopTimes(Set<GtfsStopTime> gtfsStopTimes) {
		Set<String> stopIds = gtfsStopTimes.stream()
				.map(s -> s.getStopId())
				.distinct()
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsStops().stream()
				.filter(s -> stopIds.contains(s.getStopId()))
				.collect(Collectors.toSet());
	}
	// StopTime
	public Set<GtfsStopTime> getStopTimesByTrips(Set<GtfsTrip> gtfsTrips) {
		Set<String> tripIds = gtfsTrips.stream()
				.map(t -> t.getTripId())
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsStopTimes().stream()
				.filter(s -> tripIds.contains(s.getTripId()))
				.collect(Collectors.toSet());
	}
	// Transfer
	public Set<GtfsTransfer> getTransfersByStops(Set<GtfsStop> gtfsStops) {
		Set<String> stopIds = gtfsStops.stream()
				.map(s -> s.getStopId())
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsTransfers().stream()
				.filter(t -> stopIds.contains(t.getFromStopId()) && stopIds.contains(t.getToStopId()))
				.collect(Collectors.toSet());
	}
	// Trip	
	public Set<GtfsTrip> getTripsAtDates(Set<GtfsDate> dates) {
		Set<String> serviceIds = getCalendarsAllAtDates(dates).stream()
				.map(c -> c.getServiceId())
				.collect(Collectors.toSet());
		for (GtfsCalendarDate calendarDate : getCalendarDatesAtDates(dates)) {
			if (calendarDate.getExceptionType().equals(GtfsCalendarExceptionType.REMOVED)) {
				serviceIds.remove(calendarDate.getServiceId());
			}
			if (calendarDate.getExceptionType().equals(GtfsCalendarExceptionType.ADDED)) {
				serviceIds.add(calendarDate.getServiceId());
			}
		}

		return feed.getAllGtfsTrips().stream()
				.filter(t -> serviceIds.contains(t.getServiceId()))
				.collect(Collectors.toSet());
	}
	public Set<GtfsTrip> getTripsByRoutes(Set<GtfsRoute> gtfsRoutes) {
		Set<String> routeIds = gtfsRoutes.stream()
				.map(r -> r.getRouteId())
				.collect(Collectors.toSet());
		
		return feed.getAllGtfsTrips().stream()
				.filter(t -> routeIds.contains(t.getRouteId()))
				.collect(Collectors.toSet());
	}
	public Set<GtfsTrip> getTripsByStops(GtfsStop fromStop, GtfsStop toStop) {
		Set<GtfsStopTime> fromStopTimes = feed.getStopTimesByStopId(fromStop.getStopId());
		Set<GtfsStopTime> toStopTimes = feed.getStopTimesByStopId(toStop.getStopId());
		
		Set<String> tripIds = new HashSet<>();
		for (GtfsStopTime fromStopTime : fromStopTimes) {
			for (GtfsStopTime toStopTime : toStopTimes) {
				if (fromStopTime.getTripId().equals(toStopTime.getTripId()) && 
						fromStopTime.getStopSequence() < toStopTime.getStopSequence()) {
					tripIds.add(fromStopTime.getTripId());
				}
			}
		}
		
		return feed.getTripsByIds(tripIds);
	}
}
