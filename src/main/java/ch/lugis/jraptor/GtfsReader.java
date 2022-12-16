package ch.lugis.jraptor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashSet;

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
import ch.lugis.jraptor.gtfs.model.GtfsTableData;
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
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar.txt"));
		gtfsAgencies = readToMemory(reader, new GtfsAgency());
	}
	public void readCalendarsToMemory() throws IOException, CsvValidationException {
		logger.info("Read calendar.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar.txt"));
		gtfsCalendars = readToMemory(reader, new GtfsCalendar());
	}
	public void readCalendarDatesToMemory() throws IOException, CsvValidationException {
		logger.info("Read calendar_dates.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar_dates.txt"));
		gtfsCalendarDates = readToMemory(reader, new GtfsCalendarDate());
	}
	public void readFrequenciesToMemory() throws IOException, CsvValidationException {
		logger.info("Read frequency.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("frequencies.txt"));
		gtfsFrequencies = readToMemory(reader, new GtfsFrequency());
	}
	public void readRoutesToMemory() throws IOException, CsvValidationException {
		logger.info("Read routes.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("routes.txt"));
		gtfsRoutes = readToMemory(reader, new GtfsRoute());
	}
	public void readStopsToMemory() throws IOException, CsvValidationException {
		logger.info("Read stops.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("stops.txt"));
		gtfsStops = readToMemory(reader, new GtfsStop());
	}
	public void readStopTimesToMemory() throws IOException, CsvValidationException {
//		logger.info("Read stop_times.txt to memory");
//		CSVReader reader = createReader(gtfsDirectory.resolve("stop_times.txt"));
//		gtfsStopTimes = readToMemory(reader, new GtfsStopTime());
	}
	public void readTransfersToMemory() throws IOException, CsvValidationException {
//		logger.info("Read transfers.txt to memory");
//		CSVReader reader = createReader(gtfsDirectory.resolve("transfers.txt"));
//		gtfsTransfers = readToMemory(reader, new GtfsTransfer());
	}
	public void readTripsToMemory() throws IOException, CsvValidationException {
//		logger.info("Read trips.txt to memory");
//		CSVReader reader = createReader(gtfsDirectory.resolve("trips.txt"));
//		gtfsTrips = readToMemory(reader, new GtfsTrip());
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
	
	private <T extends GtfsTableData> Method[] getSetters(T gtfsObject, String[] headers) {
		return gtfsObject.getOrderedMethodArray(headers);
	}

	private <T extends GtfsTableData> Set<T> readToMemory(CSVReader reader, T gtfsObject) throws CsvValidationException, IOException {
		Set<T> dataset = new HashSet<>();
		String[] lineValues = reader.readNext(); // Reads the header
		Class<T> gtfsClass = (Class<T>) gtfsObject.getClass();
		Method[] setterMethods = getSetters(gtfsObject, lineValues);
		
		
		while ((lineValues = reader.readNext()) != null) {
			try {
				T gtfsObjectInstance = gtfsClass.getConstructor().newInstance();
				int i = 0;
				for (String value : lineValues) {
					if (setterMethods[i] != null) {
							setterMethods[i].invoke(gtfsObjectInstance, value);
					}
					i++;
				}
				dataset.add(gtfsObjectInstance);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | SecurityException | NoSuchMethodException e) {
				// Internal error
				logger.severe(String.format("Fatal error in {} class", gtfsClass.getName()));
				e.printStackTrace();
			}
		}
		return dataset;
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
