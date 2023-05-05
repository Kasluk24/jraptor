package ch.weinetz.jraptor;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.opencsv.exceptions.CsvValidationException;

import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;
import ch.weinetz.jraptor.provider.GtfsRouteRaptorProvider;

public class Starter {

	public static void main(String[] args) throws CsvValidationException, IOException {
		System.out.println(LocalTime.now());
		
		// Code
		GtfsInMemoryReader reader = new GtfsInMemoryReader("data/gtfs_fp2022_2022-08-17_04-15");
		reader.readStopTimesToMemory();
		reader.readTripsToMemory();
		reader.readStopsToMemory();
		Set<GtfsStopTime> stopTimes = reader.getGtfsStopTimes();
		Set<GtfsTrip> trips = reader.getGtfsTrips();
		Set<GtfsStop> stops = reader.getGtfsStops();
		
		GtfsStop stopA = stops.stream()
				.filter(s -> s.getStopId().equals("8500023:0:1"))
				.findFirst()
				.get();
		GtfsStop stopB = stops.stream()
				.filter(s -> s.getStopId().equals("8502001:0:2"))
				.findFirst()
				.get();
		GtfsTrip trip = trips.stream()
				.filter(t -> t.getTripId().equals("2.TA.91-BT-Y-j22-1.2.H"))
				.findFirst()
				.get();
		
		System.out.println(stopA);
		System.out.println(stopB);
		System.out.println(trip);
		
		GtfsRouteRaptorProvider provider = new GtfsRouteRaptorProvider(trips, stops, stopTimes);
		
		System.out.println(LocalTime.now() + " 1.1");
		boolean isABeforeB = provider.isStopBefore(trip, stopA, stopB);
		System.out.println(LocalTime.now() + " 1.2");
		
		System.out.println(isABeforeB);
		
		System.out.println(LocalTime.now() + " 2.1");
		int stopIndexA = provider.getRouteStopIndex(trip, stopA);
		int stopIndexB = provider.getRouteStopIndex(trip, stopB);
		System.out.println(LocalTime.now() + " 2.2");
		
		System.out.println(stopIndexA + " -- " + stopIndexB);
		
		System.out.println(LocalTime.now() + " 3.1");
		List<GtfsStop> stopsFromTrip = provider.getRoutePath(trip);
		System.out.println(LocalTime.now() + " 3.2");
		System.out.println(stopsFromTrip);
		
		System.out.println(LocalTime.now());
	}
}
