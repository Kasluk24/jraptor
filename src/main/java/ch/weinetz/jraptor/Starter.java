package ch.weinetz.jraptor;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.exceptions.CsvValidationException;

import ch.weinetz.jraptor.filter.GtfsFeedFilter;
import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.utils.GtfsFeedFileReader;

public class Starter {

	public static void main(String[] args) throws CsvValidationException, IOException, ClassNotFoundException, InterruptedException {
		
		// Code
		GtfsFeedFileReader reader = new GtfsFeedFileReader("data/gtfs_fp2023_2023-10-11_04-15");
		GtfsFeed feed0 = reader.readFeedParallel();
		System.out.println(LocalTime.now());
		System.out.println(feed0);
		
		Set<GtfsDate> dates = new HashSet<>();
		dates.add(new GtfsDate("20231123"));
		dates.add(new GtfsDate("20231124"));
		
		Set<String> agencyIds = new HashSet<>();
		agencyIds.add("11");
		Set<GtfsAgency> agencies = feed0.getAgenciesByIds(agencyIds);
		
		GtfsFeedFilter feedFilter = new GtfsFeedFilter(feed0);
		
		System.out.println(LocalTime.now());
		feedFilter.filterFeedByDates(dates);
		System.out.println(LocalTime.now());
		feedFilter.filterFeedByAgencies(agencies);
		System.out.println(LocalTime.now());
		
		System.out.println(feed0);
		
		System.out.println(LocalTime.now());
		
		/*
		System.out.println(LocalTime.now());
		GtfsInMemoryReader reader = new GtfsInMemoryReader("data/gtfs_fp2023_2023-10-11_04-15");
		GtfsFeed feed1 = reader.readGtfsFeed();
		System.out.println(LocalTime.now());
		System.out.println(feed1.toString());
		*/

		/*
		System.out.println(LocalTime.now());
		GtfsFeedFileReader feedReader = new GtfsFeedFileReader("data/gtfs_fp2023_2023-10-11_04-15");
		GtfsFeed feed2 = feedReader.readFeed();
		System.out.println(LocalTime.now());
		System.out.println(feed2.toString());
		*/

		/*
		try {
			System.out.println(LocalTime.now());
			GtfsFeedFileReader feedReader2 = new GtfsFeedFileReader("data/gtfs_fp2023_2023-10-11_04-15");
			GtfsFeed feed3 = feedReader2.readFeedParallel();
			System.out.println(LocalTime.now());
			System.out.println(feed3.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		System.out.println(LocalTime.now());
		GtfsFileToSqliteConverter reader = new GtfsFileToSqliteConverter(
				"data/gtfs_fp2023_2023-10-11_04-15", 
				"data/gtfs_fp2023_2023-10-11_04-15.sqlite"
		);
		
		try {
			reader.readAllToSqlite();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(LocalTime.now());
		*/
		
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
