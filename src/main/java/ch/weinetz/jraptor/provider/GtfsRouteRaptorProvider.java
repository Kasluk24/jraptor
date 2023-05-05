package ch.weinetz.jraptor.provider;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.raoulvdberge.raptor.provider.RouteDetailsProvider;

import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsRouteRaptorProvider implements RouteDetailsProvider<GtfsTrip, GtfsStop> {
	private Set<GtfsTrip> trips;
	private Set<GtfsStop> stops;
	private Set<GtfsStopTime> stopTimes;
	
	
	public GtfsRouteRaptorProvider(Set<GtfsTrip> trips, Set<GtfsStop> stops, Set<GtfsStopTime> stopTimes) {
		this.trips = trips;
		this.stops = stops;
		this.stopTimes = stopTimes;
	}
	
	
	@Override
	public boolean isStopBefore(GtfsTrip trip, GtfsStop stopA, GtfsStop stopB) {
		Set<GtfsStopTime> stopTimesByTrip = stopTimes.stream()
				.filter(s -> s.getTripId().equals(trip.getTripId()))
				.collect(Collectors.toSet());
		int stopSequenceA = stopTimesByTrip.stream()
				.filter(s -> s.getStopId().equals(stopA.getStopId()))
				.findFirst()
				.orElseThrow()
				.getStopSequence();
		int stopSequenceB = stopTimesByTrip.stream()
				.filter(s -> s.getStopId().equals(stopB.getStopId()))
				.findFirst()
				.orElseThrow()
				.getStopSequence();
		return (stopSequenceA < stopSequenceB) ? true : false;
	}

	@Override
	public int getRouteStopIndex(GtfsTrip trip, GtfsStop stop) {
		int stopIndex = stopTimes.stream()
				.filter(s -> s.getTripId().equals(trip.getTripId()))
				.filter(s -> s.getStopId().equals(stop.getStopId()))
				.findFirst()
				.orElseThrow()
				.getStopSequence();
		return stopIndex;
	}

	@Override
	public List<GtfsStop> getRoutePath(GtfsTrip trip) {
		Set<String> stopIds = stopTimes.stream()
				.filter(s -> s.getTripId().equals(trip.getTripId()))
				.map(GtfsStopTime::getStopId)
				.collect(Collectors.toSet());
		List<GtfsStop> stopsByRoute = stops.stream()
				.filter(s -> stopIds.contains(s.getStopId()))
				.collect(Collectors.toList());
		return stopsByRoute;
	}

}
