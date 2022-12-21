package ch.lugis.jraptor;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.exceptions.CsvValidationException;

public class Starter {

	public static void main(String[] args) {
		// Test GtfsReader
		GtfsSqliteReader reader = new GtfsSqliteReader("data/gtfs_fp2022_2022-08-17_04-15", "data/GTFS.sqlite");
		System.out.println(LocalTime.now());
		
//		try {
//			reader.readAgenciesToMemory();
//			reader.readCalendarsToMemory();
//			reader.readCalendarDatesToMemory();
//			reader.readRoutesToMemory();
//			reader.readStopsToMemory();
//			reader.readStopTimesToMemory();
//			reader.readTransfersToMemory();
//			reader.readTripsToMemory();
//		} catch (CsvValidationException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
//		for (GtfsStop stop : reader.getGtfsStops()) {
//			System.out.println(stop.toString());
//		}
//		for (GtfsStopTime stopTime : reader.getGtfsStopTimes()) {
//			System.out.println(stopTime.toString());
//		}
//		for (GtfsTransfer transfer : reader.getGtfsTransfers()) {
//			System.out.println(transfer.toString());
//		}
//		for (GtfsTrip trip : reader.getGtfsTrips()) {
//			System.out.println(trip.toString());
//		}
				
		try {
			reader.readAgenciesToSqlite();
			reader.readCalendarsToSqlite();
			reader.readCalendarDatesToSqlite();
			reader.readRoutesToSqlite();
		} catch (CsvValidationException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(LocalTime.now());
	}
}
