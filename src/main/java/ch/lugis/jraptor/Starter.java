package ch.lugis.jraptor;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

import ch.lugis.jraptor.gtfs.model.GtfsAgency;
import ch.lugis.jraptor.gtfs.model.GtfsCalendar;
import ch.lugis.jraptor.gtfs.model.GtfsCalendarDate;
import ch.lugis.jraptor.gtfs.model.GtfsRoute;
import ch.lugis.jraptor.gtfs.model.GtfsStop;

public class Starter {

	public static void main(String[] args) {
		// Test GtfsReader
		GtfsReader reader = new GtfsReader("data/gtfs_fp2022_2022-08-17_04-15");
		
		try {
			reader.readAgenciesToMemory();
			reader.readCalendarsToMemory();
			reader.readCalendarDatesToMemory();
			reader.readRoutesToMemory();
			reader.readStopsToMemory();
		} catch (CsvValidationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (GtfsAgency agency : reader.getGtfsAgencies()) {
//			System.out.println(agency.toString());
//		}
//		for (GtfsCalendar calendar : reader.getGtfsCalendars()) {
//			System.out.println(calendar.toString());
//		}
//		for (GtfsCalendarDate calendarDate : reader.getGtfsCalendarDates()) {
//			System.out.println(calendarDate.toString());
//		}
//		for (GtfsRoute route : reader.getGtfsRoutes()) {
//			System.out.println(route.toString());
//		}
		for (GtfsStop stop : reader.getGtfsStops()) {
			System.out.println(stop.toString());
		}
	}
}
