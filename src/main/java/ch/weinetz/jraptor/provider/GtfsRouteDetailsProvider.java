package ch.weinetz.jraptor.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.raoulvdberge.raptor.provider.RouteDetailsProvider;

import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsRouteDetailsProvider implements RouteDetailsProvider<GtfsTrip, GtfsStop> {
	private final GtfsFeed feed;
	
	public GtfsRouteDetailsProvider(GtfsFeed feed) {
		this.feed = feed;
	}

	@Override
	public boolean isStopBefore(GtfsTrip route, GtfsStop stopA, GtfsStop stopB) {
		int[] sequence = new int[2];
		for (GtfsStopTime stopTime : feed.getAllGtfsStopTimes()) {
			if (stopTime.getTripId().equals(route.getTripId())) {
				if (stopTime.getStopId().equals(stopA.getStopId())) {
					sequence[0] = stopTime.getStopSequence();
				} else if (stopTime.getStopId().equals(stopB.getStopId())) {
					sequence[1] = stopTime.getStopSequence();
				}
			}
		}
		return (sequence[0] < sequence[1]);
	}

	@Override
	public int getRouteStopIndex(GtfsTrip route, GtfsStop stop) {
		int stopSequence = -1;
		for (GtfsStopTime stopTime : feed.getAllGtfsStopTimes()) {
			if (stopTime.getTripId().equals(route.getTripId())) {
				if (stopTime.getStopId().equals(stop.getStopId())) {
					stopSequence = stopTime.getStopSequence();
				} 
			}
		}
		return stopSequence;
	}

	@Override
	public List<GtfsStop> getRoutePath(GtfsTrip route) {
		List<GtfsStopTime> stopTimes = feed.getAllGtfsStopTimes().stream()
				.filter(s -> s.getTripId().equals(route.getTripId()))
				.collect(Collectors.toList());
		
		Collections.sort(stopTimes, new Comparator<GtfsStopTime>() {
			@Override
			public int compare(GtfsStopTime o1, GtfsStopTime o2) {
				return o1.getStopSequence() - o2.getStopSequence();
			}
		});
		
		List<GtfsStop> stops = new LinkedList<>();
		for (GtfsStopTime stopTime : stopTimes) {
			stops.add(feed.getAllGtfsStops().stream()
					.filter(s -> s.getStopId().equals(stopTime.getStopId()))
					.findAny()
					.get());
		}
		
		return stops;
	}
}
