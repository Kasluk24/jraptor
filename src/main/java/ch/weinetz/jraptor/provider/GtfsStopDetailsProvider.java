package ch.weinetz.jraptor.provider;

import java.util.Set;
import java.util.stream.Collectors;

import com.raoulvdberge.raptor.provider.StopDetailsProvider;

import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsStopDetailsProvider implements StopDetailsProvider<GtfsStop, GtfsTrip> {
	private final GtfsFeed feed;
	
	public GtfsStopDetailsProvider(GtfsFeed feed) {
		this.feed = feed;
	}
	
	@Override
	public Set<GtfsStop> getStops() {
		return this.feed.getAllGtfsStops();
	}

	@Override
	public Set<GtfsTrip> getRoutesByStop(GtfsStop stop) {
		Set<String> tripIds = this.feed.getAllGtfsStopTimes().stream()
				.filter(s -> s.getStopId().equals(stop.getStopId()))
				.map(s -> s.getTripId())
				.distinct()
				.collect(Collectors.toSet());
		return this.feed.getAllGtfsTrips().stream()
				.filter(t -> tripIds.contains(t.getTripId()))
				.collect(Collectors.toSet());
	}
}
