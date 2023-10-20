package ch.weinetz.jraptor;

import java.io.IOException;
import java.time.LocalTime;
import com.opencsv.exceptions.CsvValidationException;

import ch.weinetz.jraptor.gtfs.model.GtfsFeed;

public class Starter {

	public static void main(String[] args) throws CsvValidationException, IOException, ClassNotFoundException {
		System.out.println(LocalTime.now());
		
		// Code
		GtfsInMemoryReader reader = new GtfsInMemoryReader("data/gtfs_fp2022_2022-08-17_04-15");
		GtfsFeed feed = reader.readGtfsFeed();
		System.out.println(feed);
		
		/*
		GtfsInMemoryReader reader = new GtfsInMemoryReader("data/gtfs_fp2022_2022-08-17_04-15");
		reader.readStopTimesToMemory();
		reader.readTripsToMemory();
		reader.readStopsToMemory();
		reader.readRoutesToMemory();
		reader.readCalendarsToMemory();
		Set<GtfsStopTime> stopTimes = reader.getGtfsStopTimes();
		Set<GtfsTrip> trips = reader.getGtfsTrips();
		Set<GtfsStop> stops = reader.getGtfsStops();
		Set<GtfsRoute> routes = reader.getGtfsRoutes();
		Set<GtfsCalendar> calendars = reader.getGtfsCalendars();

		System.out.println(LocalTime.now());

		// Prepare Sets
		Set<String> filteredServiceId = calendars.stream()
				.filter(c -> c.getWednesday())
				.map(c -> c.getServiceId())
				.collect(Collectors.toSet());
		
		Set<GtfsTrip> filteredTrips = trips.stream()
				.filter(t -> filteredServiceId.contains(t.getServiceId()))
				.collect(Collectors.toSet());
		
		Set<String> filteredRouteIds = filteredTrips.stream()
				.map(t -> t.getRouteId())
				.distinct()
				.collect(Collectors.toSet());
		
		Set<GtfsRoute> filteredRoutes = routes.stream()
				.filter(r -> filteredRouteIds.contains(r.getRouteId()))
				.collect(Collectors.toSet());
		
		Set<String> filteredTripIds = filteredTrips.stream()
				.map(t -> t.getTripId())
				.collect(Collectors.toSet());
		
		Set<GtfsStopTime> filteredStopTimes = stopTimes.stream()
				.filter(s -> filteredTripIds.contains(s.getTripId()))
				.collect(Collectors.toSet());
		
		
		System.out.println(routes.size());
		System.out.println(filteredRoutes.size());
		System.out.println(LocalTime.now());		
				
		Map routeStopIndex = RaptorRouteStopIndexBuilder.buildRaptorRouteStopIndex(filteredStopTimes, filteredRoutes, stops, filteredTrips);
		System.out.println(routeStopIndex);
		
		
		System.out.println(LocalTime.now());
		*/
	}
}
