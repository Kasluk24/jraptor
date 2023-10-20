package ch.weinetz.jraptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.exceptions.CsvValidationException;

import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;
import ch.weinetz.jraptor.provider.GtfsRouteRaptorProvider;
import ch.weinetz.jraptor.provider.RaptorRouteStopIndexBuilder;
import ch.weinetz.jraptor.utils.GtfsComparsion;

public class Starter {

	public static void main(String[] args) throws CsvValidationException, IOException, ClassNotFoundException {
		System.out.println(LocalTime.now());
		
		// Code
		GtfsInMemoryReader reader = new GtfsInMemoryReader("data/gtfs_fp2022_2022-08-17_04-15");
		GtfsFeed feed = reader.readGtfsFeed();
				
		
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
