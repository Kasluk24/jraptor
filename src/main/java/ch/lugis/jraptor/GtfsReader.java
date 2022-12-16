package ch.lugis.jraptor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ch.lugis.jraptor.gtfs.model.GtfsAgency;
import ch.lugis.jraptor.gtfs.model.GtfsCalendar;
import ch.lugis.jraptor.gtfs.model.GtfsCalendarDate;
import ch.lugis.jraptor.gtfs.model.GtfsFrequency;
import ch.lugis.jraptor.gtfs.model.GtfsRoute;
import ch.lugis.jraptor.gtfs.model.GtfsStop;
import ch.lugis.jraptor.gtfs.model.GtfsStopTime;
import ch.lugis.jraptor.gtfs.model.GtfsTransfer;
import ch.lugis.jraptor.gtfs.model.GtfsTrip;

public class GtfsReader {
	// Fields
	private final Path gtfsDirectory;
	private Logger logger;
	private Set<GtfsAgency> gtfsAgencies;
	private Set<GtfsCalendar> gtfsCalendars;
	private Set<GtfsCalendarDate> gtfsCalendarDates;
	private Set<GtfsFrequency> gtfsFrequencies;
	private Set<GtfsRoute> gtfsRoutes;
	private Set<GtfsStop> gtfsStops;
	private Set<GtfsStopTime> gtfsStopTimes;
	private Set<GtfsTransfer> gtfsTransfers;
	private Set<GtfsTrip> gtfsTrips;
	
	// Constructor
	public GtfsReader(String gtfsDirectory) {
		if (gtfsDirectory == null) {
			this.gtfsDirectory = Paths.get(".");
		} else {
			this.gtfsDirectory = Paths.get(gtfsDirectory);
		}
		
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);		
	}
	
	// Public read methods
	public void readAllToMemory() throws IOException, CsvValidationException {
		Set<String> gtfsFiles = getGtfsFiles();
	    if (gtfsFiles.contains("agency.txt")) {
			readAgenciesToMemory();
	    }
	    if (gtfsFiles.contains("calendar.txt")) {
			readCalendarsToMemory();
	    }
	    if (gtfsFiles.contains("calendar_dates.txt")) {
			readCalendarDatesToMemory();
	    }
	    if (gtfsFiles.contains("frequencies.txt")) {
			readFrequenciesToMemory();
	    }
	    if (gtfsFiles.contains("routes.txt")) {
			readRoutesToMemory();
	    }
	    if (gtfsFiles.contains("stops.txt")) {
			readStopsToMemory();
	    }
	    if (gtfsFiles.contains("stop_times.txt")) {
			readStopTimesToMemory();
	    }
	    if (gtfsFiles.contains("transfers.txt")) {
			readTransfersToMemory();
	    }
	    if (gtfsFiles.contains("trips.txt")) {
			readTripsToMemory();
	    }
	}
	public void readAllToSqlite() throws IOException, CsvValidationException {
		Set<String> gtfsFiles = getGtfsFiles();
	    if (gtfsFiles.contains("agency.txt")) {
			readAgenciesToSqlite();
	    }
	    if (gtfsFiles.contains("calendar.txt")) {
			readCalendarsToSqlite();
	    }
	    if (gtfsFiles.contains("calendar_dates.txt")) {
			readCalendarDatesToSqlite();
	    }
	    if (gtfsFiles.contains("frequencies.txt")) {
			readFrequenciesToSqlite();
	    }
	    if (gtfsFiles.contains("routes.txt")) {
			readRoutesToSqlite();
	    }
	    if (gtfsFiles.contains("stops.txt")) {
			readStopsToSqlite();
	    }
	    if (gtfsFiles.contains("stop_times.txt")) {
			readStopTimesToSqlite();
	    }
	    if (gtfsFiles.contains("transfers.txt")) {
			readTransfersToSqlite();
	    }
	    if (gtfsFiles.contains("trips.txt")) {
			readTripsToSqlite();
	    }
	}
	
	// Read to memory
	public void readAgenciesToMemory() throws IOException, CsvValidationException {
    	logger.info("Read agency.txt to memory");
		
		gtfsAgencies = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("agency.txt"));
		String[] lineValues = reader.readNext();
		Method[] methods = GtfsAgency.getOrderedMethodArray(lineValues);
		
		while ((lineValues = reader.readNext()) != null) {
			GtfsAgency agency = new GtfsAgency();
			int i = 0;
			for (String value : lineValues) {
				if (methods[i] != null) {
					try {
						methods[i].invoke(agency, value);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// Internal error
						logger.severe("Fatal error in GtfsAgency class");
						e.printStackTrace();
					}
				}
				i++;
			}
			gtfsAgencies.add(agency);
		}

	}
	public void readCalendarsToMemory() throws IOException, CsvValidationException {
		logger.info("Read calendar.txt to memory");
		
		gtfsCalendars = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar.txt"));
		String[] lineValues = reader.readNext();
		
		Method[] methods = GtfsCalendar.getOrderedMethodArray(lineValues);
		
		while ((lineValues = reader.readNext()) != null) {
			GtfsCalendar calendar = new GtfsCalendar();
			int i = 0;
			for (String value : lineValues) {
				if (methods[i] != null) {
					try {
						methods[i].invoke(calendar, value);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// Internal error
						logger.severe("Fatal error in GtfsCalendar class");
						e.printStackTrace();
					}
				}
				i++;
			}
			gtfsCalendars.add(calendar);
		}
	}
	public void readCalendarDatesToMemory() throws IOException, CsvValidationException {
		logger.info("Read calendar_dates.txt to memory");
		
		gtfsCalendarDates = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar_dates.txt"));
		String[] lineValues = reader.readNext();
		
		Method[] methods = GtfsCalendarDate.getOrderedMethodArray(lineValues);
		
		while ((lineValues = reader.readNext()) != null) {
			GtfsCalendarDate calendarDate = new GtfsCalendarDate();
			int i = 0;
			for (String value : lineValues) {
				if (methods[i] != null) {
					try {
						methods[i].invoke(calendarDate, value);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// Internal error
						logger.severe("Fatal error in GtfsCalendarDate class");
						e.printStackTrace();
					}
				}
				i++;
			}
			gtfsCalendarDates.add(calendarDate);
		}
	}
	public void readFrequenciesToMemory() throws IOException, CsvValidationException {
		gtfsFrequencies = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("frequencies.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsFrequency.mapFields(lineValues);
		
		while((lineValues = reader.readNext()) != null) {
			GtfsFrequency frequency = new GtfsFrequency(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]],
					lineValues[valueOrder[4]]);
			gtfsFrequencies.add(frequency);
		}
	}
	public void readRoutesToMemory() throws IOException, CsvValidationException {
		gtfsRoutes = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("routes.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsRoute.mapFields(lineValues);
		
		while((lineValues = reader.readNext()) != null) {
			GtfsRoute route = new GtfsRoute(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]],
					lineValues[valueOrder[4]],
					lineValues[valueOrder[5]],
					lineValues[valueOrder[6]],
					lineValues[valueOrder[7]],
					lineValues[valueOrder[8]]);
			gtfsRoutes.add(route);
		}
	}
	public void readStopsToMemory() throws IOException, CsvValidationException {
		gtfsStops = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("stops.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsStop.mapFields(lineValues);
		
		while((lineValues = reader.readNext()) != null) {
			GtfsStop stop = new GtfsStop(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]],
					lineValues[valueOrder[4]],
					lineValues[valueOrder[5]],
					lineValues[valueOrder[6]],
					lineValues[valueOrder[7]],
					lineValues[valueOrder[8]]);
			gtfsStops.add(stop);
		}
	}
	public void readStopTimesToMemory() throws IOException, CsvValidationException {
		gtfsStopTimes = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("stop_times.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsStopTime.mapFields(lineValues);
		
		while ((lineValues = reader.readNext()) != null) {
			GtfsStopTime stopTime = new GtfsStopTime(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]],
					lineValues[valueOrder[4]],
					lineValues[valueOrder[5]],
					lineValues[valueOrder[6]],
					lineValues[valueOrder[7]],
					lineValues[valueOrder[8]]);
			gtfsStopTimes.add(stopTime);
		}
	}
	public void readTransfersToMemory() throws IOException, CsvValidationException {
		gtfsTransfers = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("transfers.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsTransfer.mapFields(lineValues);
		
		while((lineValues = reader.readNext()) != null) {
			GtfsTransfer transfer = new GtfsTransfer(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]]);
			gtfsTransfers.add(transfer);
		}
	}
	public void readTripsToMemory() throws IOException, CsvValidationException {
		gtfsTrips = new HashSet<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("trips.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsTrip.mapFields(lineValues);
		
		while((lineValues = reader.readNext()) != null) {
			GtfsTrip trip = new GtfsTrip(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]],
					lineValues[valueOrder[4]],
					lineValues[valueOrder[5]],
					lineValues[valueOrder[6]],
					lineValues[valueOrder[7]]);
			gtfsTrips.add(trip);
		}
	}
	
	// Read to sqlite
	public void readAgenciesToSqlite() {
		
	}
	public void readCalendarsToSqlite() {
		
	}
	public void readCalendarDatesToSqlite() {
		
	}
	public void readFrequenciesToSqlite() {
		
	}
	public void readRoutesToSqlite() {
		
	}
	public void readStopsToSqlite() {
		
	}
	public void readStopTimesToSqlite() {

	}
	public void readTransfersToSqlite() {
		
	}
	public void readTripsToSqlite() {
		
	}
	
	// Private methods
	private CSVReader createReader(Path gtfsFile) throws IOException {
		FileReader fileReader = new FileReader(gtfsFile.toFile());
		CSVReader reader = new CSVReader(fileReader);
		
		return reader;
	}
	private Set<String> getGtfsFiles() {
		File gtfsFolder = gtfsDirectory.toFile();
	    Set<String> gtfsFiles = Stream.of(gtfsFolder.listFiles())
	    	      .filter(file -> !file.isDirectory())
	    	      .map(File::getName)
	    	      .collect(Collectors.toSet());
		
		return gtfsFiles;
	}
	
	// Getters
	public Set<GtfsAgency> getGtfsAgencies() {
		return gtfsAgencies;
	}
	public Set<GtfsCalendar> getGtfsCalendars() {
		return gtfsCalendars;
	}
	public Set<GtfsCalendarDate> getGtfsCalendarDates() {
		return gtfsCalendarDates;
	}
	public Set<GtfsFrequency> getGtfsFrequencies() {
		return gtfsFrequencies;
	}
	public Set<GtfsRoute> getGtfsRoutes() {
		return gtfsRoutes;
	}
	public Set<GtfsStop> getGtfsStops() {
		return gtfsStops;
	}
	public Set<GtfsStopTime> getGtfsStopTimes() {
		return gtfsStopTimes;
	}
	public Set<GtfsTransfer> getGtfsTransfers() {
		return gtfsTransfers;
	}
	public Set<GtfsTrip> getGtfsTrips() {
		return gtfsTrips;
	}
}
