package ch.weinetz.jraptor.filter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarDate;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarExceptionType;
import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTransfer;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsTableFilter {
	// Agency
	// Calendar
	// All calendars that intersect the given dates
	public static Set<GtfsCalendar> getCalendarsAllAtDates(Set<GtfsCalendar> gtfsCalendars, Set<GtfsDate> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(gtfsCalendars.stream()
					.filter(c -> date.between(c.getStartDate(), c.getEndDate()))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	// All calendars that only include the given dates
	public static Set<GtfsCalendar> getCalendarsOnlyAtDates(Set<GtfsCalendar> gtfsCalendars, Set<GtfsDate> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(gtfsCalendars.stream()
					.filter(c -> date.equals(c.getStartDate()) && date.equals(c.getEndDate()))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	// All calendars that intersect the given period
	public static Set<GtfsCalendar> getCalendarsAllBetweenDates(Set<GtfsCalendar> gtfsCalendars, Set<GtfsDate[]> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(gtfsCalendars.stream()
					.filter(c -> c.getEndDate().after(date[0]) || c.getStartDate().before(date[1]))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	// All calendars that only include the given period
	public static Set<GtfsCalendar> getCalendarsOnlyBetweenDates(Set<GtfsCalendar> gtfsCalendars, Set<GtfsDate[]> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(gtfsCalendars.stream()
					.filter(c -> c.getStartDate().after(date[0]) || c.getEndDate().before(date[1]))
					.collect(Collectors.toSet())
				);
		});
		return calendars;
	}
	
	// CalendarDate
	// All calendar dates that match the given dates
	public static Set<GtfsCalendarDate> getCalendarDatesAtDates(Set<GtfsCalendarDate> gtfsCalendarDates, Set<GtfsDate> dates) {
		Set<GtfsCalendarDate> calendarDates = new HashSet<>();
		dates.forEach(date -> {
			calendarDates.addAll(gtfsCalendarDates.stream()
					.filter(c -> c.getDate().equals(date))
					.collect(Collectors.toSet())
				);
		});
		return calendarDates;		
	}
	// All calendar dates that intersect the given period
	public static Set<GtfsCalendarDate> getCalendarDatesBetweenDates(Set<GtfsCalendarDate> gtfsCalendarDates, Set<GtfsDate[]> dates) {
		Set<GtfsCalendarDate> calendarDates = new HashSet<>();
		dates.forEach(date -> {
			calendarDates.addAll(gtfsCalendarDates.stream()
					.filter(c -> c.getDate().after(date[0]) && c.getDate().before(date[1]))
					.collect(Collectors.toSet())
				);
		});
		return calendarDates;
	}
	// Frequency
	// Route
	// Stop
	// StopTime
	// Transfer
	// Trip
	public static Set<GtfsTrip> getTripsAtDates(Set<GtfsTrip> gtfsTrips, 
			Set<GtfsCalendar> gtfsCalendars, 
			Set<GtfsCalendarDate> gtfsCalendarDates, 
			Set<GtfsDate> dates) {
		
		Set<String> serviceIds = getCalendarsAllAtDates(gtfsCalendars, dates).stream()
				.map(c -> c.getServiceId())
				.collect(Collectors.toSet());
		for (GtfsCalendarDate calendarDate : getCalendarDatesAtDates(gtfsCalendarDates, dates)) {
			if (calendarDate.getExceptionType() == GtfsCalendarExceptionType.REMOVED) {
				serviceIds.remove(calendarDate.getServiceId());
			}
			if (calendarDate.getExceptionType() == GtfsCalendarExceptionType.ADDED) {
				serviceIds.add(calendarDate.getServiceId());
			}
		}

		return gtfsTrips.stream()
				.filter(t -> serviceIds.contains(t.getServiceId()))
				.collect(Collectors.toSet());
	}
	
}
