package ch.lugis.jraptor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
	private List<GtfsAgency> gtfsAgencies;
	private List<GtfsCalendar> gtfsCalendars;
	private List<GtfsCalendarDate> gtfsCalendarDates;
	private List<GtfsFrequency> gtfsFrequencies;
	private List<GtfsRoute> gtfsRoutes;
	private List<GtfsStop> gtfsStops;
	private List<GtfsStopTime> gtfsStopTimes;
	private List<GtfsTransfer> gtfsTransfers;
	private List<GtfsTrip> gtfsTrips;
	
	// Constructor
	public GtfsReader(String gtfsDirectory) {
		if (gtfsDirectory == null) {
			this.gtfsDirectory = Paths.get(".");
		} else {
			this.gtfsDirectory = Paths.get(gtfsDirectory);
		}
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
	    if (gtfsFiles.contains("agency.txt")) {
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
	    if (gtfsFiles.contains("agency.txt")) {
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
		gtfsAgencies = new LinkedList<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("agency.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsAgency.mapFields(lineValues);
		
		while ((lineValues = reader.readNext()) != null) {
			GtfsAgency agency = new GtfsAgency(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]],
					lineValues[valueOrder[4]],
					lineValues[valueOrder[5]],
					lineValues[valueOrder[6]]);
			gtfsAgencies.add(agency);
		}

	}
	public void readCalendarsToMemory() throws IOException, CsvValidationException {
		gtfsCalendars = new LinkedList<>();
		CSVReader reader = createReader(gtfsDirectory.resolve("calendar.txt"));
		String[] lineValues = reader.readNext();
		int[] valueOrder = GtfsCalendar.mapFields(lineValues);
		
		while((lineValues = reader.readNext()) != null) {
			GtfsCalendar calendar = new GtfsCalendar(
					lineValues[valueOrder[0]],
					lineValues[valueOrder[1]],
					lineValues[valueOrder[2]],
					lineValues[valueOrder[3]],
					lineValues[valueOrder[4]],
					lineValues[valueOrder[5]],
					lineValues[valueOrder[6]],
					lineValues[valueOrder[7]],
					lineValues[valueOrder[8]],
					lineValues[valueOrder[9]]);
			gtfsCalendars.add(calendar);
		}
	}
	public void readCalendarDatesToMemory() {
		
	}
	public void readFrequenciesToMemory() {
		
	}
	public void readRoutesToMemory() {
		
	}
	public void readStopsToMemory() {
		
	}
	public void readStopTimesToMemory() throws IOException, CsvValidationException {
		gtfsStopTimes = new LinkedList<>();
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
	public void readTransfersToMemory() {
		
	}
	public void readTripsToMemory() {
		
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
	public List<GtfsAgency> getGtfsAgencies() {
		return gtfsAgencies;
	}
	public List<GtfsStopTime> getGtfsStopTimes() {
		return gtfsStopTimes;
	}
}
