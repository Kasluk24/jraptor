package ch.weinetz.jraptor;


import java.util.LinkedList;
import java.util.List;

import ch.weinetz.jraptor.gtfs.model.*;
import ch.weinetz.jraptor.utils.GtfsTableReader;

public class GtfsFeedReader  {
	// Fields
	private String gtfsFeedDirectory;
	
	// Constructor
	public GtfsFeedReader(String gtfsFeedDirectory) {
		this.gtfsFeedDirectory = gtfsFeedDirectory;		
	}
	

	// Public methods
	public GtfsFeed readFeed() {
		GtfsFeed gtfsFeed = new GtfsFeed();
		
		gtfsFeed.setGtfsAgencies(new GtfsTableReader<>(gtfsFeedDirectory, GtfsAgency.class).getGtfsTable());
		gtfsFeed.setGtfsCalendars(new GtfsTableReader<>(gtfsFeedDirectory, GtfsCalendar.class).getGtfsTable());
		gtfsFeed.setGtfsCalendarDates(new GtfsTableReader<>(gtfsFeedDirectory, GtfsCalendarDate.class).getGtfsTable());
		gtfsFeed.setGtfsFrequencies(new GtfsTableReader<>(gtfsFeedDirectory, GtfsFrequency.class).getGtfsTable());
		gtfsFeed.setGtfsRoutes(new GtfsTableReader<>(gtfsFeedDirectory, GtfsRoute.class).getGtfsTable());
		gtfsFeed.setGtfsStops(new GtfsTableReader<>(gtfsFeedDirectory, GtfsStop.class).getGtfsTable());
		gtfsFeed.setGtfsStopTimes(new GtfsTableReader<>(gtfsFeedDirectory, GtfsStopTime.class).getGtfsTable());
		gtfsFeed.setGtfsTransfers(new GtfsTableReader<>(gtfsFeedDirectory, GtfsTransfer.class).getGtfsTable());
		gtfsFeed.setGtfsTrips(new GtfsTableReader<>(gtfsFeedDirectory, GtfsTrip.class).getGtfsTable());
		
		return gtfsFeed;
	}
	
	public GtfsFeed readFeedParallel() throws InterruptedException {
		GtfsFeed gtfsFeed = new GtfsFeed();
		
		List<GtfsTableReader> readers = new LinkedList<>();
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsAgency.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsCalendar.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsCalendarDate.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsFrequency.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsRoute.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsStop.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsStopTime.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsTransfer.class));
		readers.add(new GtfsTableReader<>(gtfsFeedDirectory, GtfsTrip.class));
		
		List<Thread> threads = new LinkedList<>();
		for (GtfsTableReader reader : readers) {
			threads.add(new Thread(reader));
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join();
		}
		
		gtfsFeed.setGtfsAgencies(readers.get(0).getGtfsTable());
		gtfsFeed.setGtfsCalendars(readers.get(1).getGtfsTable());
		gtfsFeed.setGtfsCalendarDates(readers.get(2).getGtfsTable());
		gtfsFeed.setGtfsFrequencies(readers.get(3).getGtfsTable());
		gtfsFeed.setGtfsRoutes(readers.get(4).getGtfsTable());
		gtfsFeed.setGtfsStops(readers.get(5).getGtfsTable());
		gtfsFeed.setGtfsStopTimes(readers.get(6).getGtfsTable());
		gtfsFeed.setGtfsTransfers(readers.get(7).getGtfsTable());
		gtfsFeed.setGtfsTrips(readers.get(8).getGtfsTable());
				
		return gtfsFeed;
	}
			
}
