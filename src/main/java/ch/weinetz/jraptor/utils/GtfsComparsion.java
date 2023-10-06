package ch.weinetz.jraptor.utils;

import java.util.function.BiPredicate;

import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsComparsion {

	public static BiPredicate<GtfsTrip, GtfsCalendar> tripsByCalendars = (t, c) -> c.getServiceId().equals(t.getServiceId());
}
