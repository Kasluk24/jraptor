package ch.weinetz.jraptor.provider;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class RaptorRouteStopIndexBuilder  {
	/*
	 * Builds the RouteStopIndex as HashMap<GtfsRoute, HashMap<GtfsStop, Integer>>
	 * 
	 */
	
	public static Map<GtfsRoute, Map<GtfsStop, Integer>> buildRaptorRouteStopIndex(
			Set<GtfsStopTime> stopTimes,
			Set<GtfsRoute> routes,
			Set<GtfsStop> stops,
			Set<GtfsTrip> trips
	) {
		Map<GtfsRoute, Map<GtfsStop, Integer>> routeStopIndex = new HashMap<GtfsRoute, Map<GtfsStop, Integer>>();
		
		int i = 0;
		for (GtfsRoute route : routes) {
			
			System.out.println(LocalTime.now() + " started");
			i++;
			System.out.println("Processing route: " + i + " of: " + routes.size());
			System.out.println(LocalTime.now() + " log printed");
			
			String firstTripId = trips.stream()
					.filter(t -> t.getRouteId().equals(route.getRouteId()))
					.findFirst()
					.get()
					.getTripId();
			
			System.out.println(LocalTime.now() + " trip selected");
			
			Set<GtfsStopTime> relatedStopTimes = stopTimes.stream()
					.filter(s -> s.getTripId().equals(firstTripId))
					.collect(Collectors.toSet());
			
			System.out.println(LocalTime.now() + "stop times selected");
			
			Map<GtfsStop, Integer> stopIndex = new HashMap<GtfsStop, Integer>();
			relatedStopTimes.forEach((GtfsStopTime stopTime) -> {
				GtfsStop relatedStop = stops.stream()
						.filter(s -> s.getStopId().equals(stopTime.getStopId()))
						.findFirst()
						.get();
				
				stopIndex.put(relatedStop, stopTime.getStopSequence());
			});
			
			System.out.println(LocalTime.now() + " stop map set");
			
			routeStopIndex.put(route, stopIndex);
			
			System.out.println(LocalTime.now() + " route map set");
		}
		
		
		
		return routeStopIndex;
		
	}
}
