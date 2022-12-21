package ch.lugis.jraptor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

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
import ch.lugis.jraptor.utils.GtfsReader;

public class GtfsInMemoryReader extends GtfsReader {
	// Fields
	private Set<GtfsAgency> gtfsAgencies;
	private Set<GtfsCalendar> gtfsCalendars;
	private Set<GtfsCalendarDate> gtfsCalendarDates;
	private Set<GtfsFrequency> gtfsFrequencies;
	private Set<GtfsRoute> gtfsRoutes;
	private Set<GtfsStop> gtfsStops;
	private Set<GtfsStopTime> gtfsStopTimes;
	private Set<GtfsTransfer> gtfsTransfers;
	private Set<GtfsTrip> gtfsTrips;
	
	public GtfsInMemoryReader(String gtfsDirectory) {
		super(gtfsDirectory);
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

	// Read to memory
	public void readAgenciesToMemory() throws IOException, CsvValidationException {
		logger.info("Read agency.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("agency.txt"));
		gtfsAgencies = readToMemory(reader, new GtfsAgency());
		reader.close();
	}
	public void readCalendarsToMemory() throws IOException, CsvValidationException {
		logger.info("Read calendar.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar.txt"));
		gtfsCalendars = readToMemory(reader, new GtfsCalendar());
		reader.close();
	}
	public void readCalendarDatesToMemory() throws IOException, CsvValidationException {
		logger.info("Read calendar_dates.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar_dates.txt"));
		gtfsCalendarDates = readToMemory(reader, new GtfsCalendarDate());
		reader.close();
	}
	public void readFrequenciesToMemory() throws IOException, CsvValidationException {
		logger.info("Read frequency.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("frequencies.txt"));
		gtfsFrequencies = readToMemory(reader, new GtfsFrequency());
		reader.close();
	}
	public void readRoutesToMemory() throws IOException, CsvValidationException {
		logger.info("Read routes.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("routes.txt"));
		gtfsRoutes = readToMemory(reader, new GtfsRoute());
		reader.close();
	}
	public void readStopsToMemory() throws IOException, CsvValidationException {
		logger.info("Read stops.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("stops.txt"));
		gtfsStops = readToMemory(reader, new GtfsStop());
		reader.close();
	}
	public void readStopTimesToMemory() throws IOException, CsvValidationException {
		logger.info("Read stop_times.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("stop_times.txt"));
		gtfsStopTimes = readToMemory(reader, new GtfsStopTime());
		reader.close();
	}
	public void readTransfersToMemory() throws IOException, CsvValidationException {
		logger.info("Read transfers.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("transfers.txt"));
		gtfsTransfers = readToMemory(reader, new GtfsTransfer());
		reader.close();
	}
	public void readTripsToMemory() throws IOException, CsvValidationException {
		logger.info("Read trips.txt to memory");
		CSVReader reader = createReader(gtfsDirectory.resolve("trips.txt"));
		gtfsTrips = readToMemory(reader, new GtfsTrip());
		reader.close();
	}
	
	// Private methods
	private <T extends GtfsTableData> Set<T> readToMemory(CSVReader reader, T gtfsObject) throws CsvValidationException, IOException {
		Set<T> dataset = new HashSet<>();
		String[] lineValues = reader.readNext(); // Reads the header
		@SuppressWarnings("unchecked")
		Class<T> gtfsClass = (Class<T>)gtfsObject.getClass();
		Method[] setterMethods = gtfsObject.getOrderedSetterArray(lineValues);
		
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
				logger.severe(String.format("Fatal error in %s class", gtfsClass.getName()));
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
