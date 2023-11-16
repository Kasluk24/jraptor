package ch.weinetz.jraptor;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		
		
		@SuppressWarnings("rawtypes") GtfsTableReader[] readers = {
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsAgency.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsCalendar.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsCalendarDate.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsFrequency.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsRoute.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsStop.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsStopTime.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsTransfer.class),
			new GtfsTableReader<>(gtfsFeedDirectory, GtfsTrip.class)
		};
		
		List<Thread> threads = new LinkedList<>();
		for (@SuppressWarnings("rawtypes") GtfsTableReader reader : readers) {
			threads.add(new Thread(reader));
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join();
		}
		
		for (@SuppressWarnings({ "rawtypes"})GtfsTableReader reader : readers) {
			
			gtfsFeed.setGtfsData(reader.getGtfsTable());
		}
		
		return gtfsFeed;
	}
			
}
