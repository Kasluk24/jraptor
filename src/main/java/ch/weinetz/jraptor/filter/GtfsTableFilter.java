package ch.weinetz.jraptor.filter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarDate;
import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTransfer;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsTableFilter {
	// Agency
	// Calendar
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
	
}
