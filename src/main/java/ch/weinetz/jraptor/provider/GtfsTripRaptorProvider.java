package ch.weinetz.jraptor.provider;

import java.time.LocalDateTime;
import java.util.Optional;

import com.raoulvdberge.raptor.model.Trip;
import com.raoulvdberge.raptor.provider.TripDetailsProvider;

import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;

public class GtfsTripRaptorProvider implements TripDetailsProvider<GtfsRoute, GtfsStop> {

	@Override
	public Optional<Trip<GtfsStop>> getEarliestTripAtStop(GtfsRoute route, int stopIndex, LocalDateTime time) {
		// TODO Auto-generated method stub
		return null;
	}


}
