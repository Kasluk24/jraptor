package ch.weinetz.jraptor.provider;

import java.time.LocalDateTime;
import java.util.Optional;

import com.raoulvdberge.raptor.model.Trip;
import com.raoulvdberge.raptor.provider.TripDetailsProvider;

import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsTripRaptorProvider implements TripDetailsProvider<GtfsTrip, GtfsStop> {
	private final GtfsFeed feed;
	
	public GtfsTripRaptorProvider(GtfsFeed feed) {
		this.feed = feed;
	}		
	
	@Override
	public Optional<Trip<GtfsStop>> getEarliestTripAtStop(GtfsTrip route, int stopIndex, LocalDateTime time) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
