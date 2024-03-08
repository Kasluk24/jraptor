package ch.weinetz.jraptor.utils;


import java.util.LinkedList;
import java.util.List;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFeed;
import ch.weinetz.jraptor.gtfs.model.GtfsFrequency;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTransfer;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsFeedFileReader  {
	// Fields
	private String gtfsFeedDirectory;
	
	// Constructor
	public GtfsFeedFileReader(String gtfsFeedDirectory) {
		this.gtfsFeedDirectory = gtfsFeedDirectory;		
	}
	

	// Public methods
	public GtfsFeed readFeed() {
		GtfsFeed gtfsFeed = new GtfsFeed();
		
		gtfsFeed.setGtfsAgencies(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsAgency.class).getGtfsTable());
		gtfsFeed.setGtfsCalendars(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsCalendar.class).getGtfsTable());
		gtfsFeed.setGtfsCalendarDates(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsCalendarDate.class).getGtfsTable());
		gtfsFeed.setGtfsFrequencies(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsFrequency.class).getGtfsTable());
		gtfsFeed.setGtfsRoutes(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsRoute.class).getGtfsTable());
		gtfsFeed.setGtfsStops(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsStop.class).getGtfsTable());
		gtfsFeed.setGtfsStopTimes(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsStopTime.class).getGtfsTable());
		gtfsFeed.setGtfsTransfers(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsTransfer.class).getGtfsTable());
		gtfsFeed.setGtfsTrips(new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsTrip.class).getGtfsTable());
		
		return gtfsFeed;
	}
	
	public GtfsFeed readFeedParallel() throws InterruptedException {
		GtfsFeed gtfsFeed = new GtfsFeed();
		
		
		@SuppressWarnings("rawtypes") GtfsTableFileReader[] readers = {
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsAgency.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsCalendar.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsCalendarDate.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsFrequency.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsRoute.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsStop.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsStopTime.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsTransfer.class),
			new GtfsTableFileReader<>(gtfsFeedDirectory, GtfsTrip.class)
		};
		
		List<Thread> threads = new LinkedList<>();
		for (@SuppressWarnings("rawtypes") GtfsTableFileReader reader : readers) {
			threads.add(new Thread(reader));
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join();
		}
		
		for (@SuppressWarnings({ "rawtypes"})GtfsTableFileReader reader : readers) {
			gtfsFeed.setGtfsData(reader.getGtfsTable());
		}
		
		return gtfsFeed;
	}
			
}
