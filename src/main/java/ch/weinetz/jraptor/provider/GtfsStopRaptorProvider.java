package ch.weinetz.jraptor.provider;

import java.util.Map;
import java.util.Set;

import com.raoulvdberge.raptor.provider.StopDetailsProvider;

import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;

public class GtfsStopRaptorProvider implements StopDetailsProvider<GtfsStop, GtfsRoute> {
	private final Map<GtfsStop, Set<GtfsRoute>> routesByStop;
	private final Set<GtfsStop> stops;
	
	public GtfsStopRaptorProvider(Map<GtfsStop, Set<GtfsRoute>> routesByStop, Set<GtfsStop> stops) {
		this.routesByStop = routesByStop;
		this.stops = stops;
	}
	
	@Override
	public Set<GtfsStop> getStops() {
		return stops;
	}
		
	@Override
	public Set<GtfsRoute> getRoutesByStop(GtfsStop stop) {
		return routesByStop.get(stop);
	}

	
}
