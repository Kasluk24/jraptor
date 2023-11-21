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
	public static Set<GtfsCalendar> getCalendarsByDates(Set<GtfsCalendar> gtfsCalendars, Set<GtfsDate> dates) {
		Set<GtfsCalendar> calendars = new HashSet<>();
		dates.forEach(date -> {
			calendars.addAll(gtfsCalendars.stream()
					.filter(c -> date.after(c.getStartDate()))
					.collect(Collectors.toSet())
				);
		});
		
		return calendars;
	}
	// CalendarDate
	// Frequency
	// Route
	// Stop
	// StopTime
	// Transfer
	// Trip
	
}
