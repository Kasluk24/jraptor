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
	
	// Read functions
	public void readAll() throws IOException, CsvValidationException {
		File gtfsFolder = gtfsDirectory.toFile();
	    Set<String> gtfsFiles = Stream.of(gtfsFolder.listFiles())
	    	      .filter(file -> !file.isDirectory())
	    	      .map(File::getName)
	    	      .collect(Collectors.toSet());
	    
	    if (gtfsFiles.contains("agency.txt")) {
			readAgencies();
	    }
	    if (gtfsFiles.contains("calendar.txt")) {
			readCalendars();
	    }
	    if (gtfsFiles.contains("calendar_dates.txt")) {
			readCalendarDates();
	    }
	    if (gtfsFiles.contains("agency.txt")) {
			readFrequencies();
	    }
	    if (gtfsFiles.contains("routes.txt")) {
			readRoutes();
	    }
	    if (gtfsFiles.contains("stops.txt")) {
			readStops();
	    }
	    if (gtfsFiles.contains("stop_times.txt")) {
			readStopTimes();
	    }
	    if (gtfsFiles.contains("transfers.txt")) {
			readTransfers();
	    }
	    if (gtfsFiles.contains("trips.txt")) {
			readTrips();
	    }
	}
	
	public void readAgencies() throws IOException, CsvValidationException {
		gtfsAgencies = new LinkedList<>();
		gtfsAgencies.clear();
		CSVReader reader = createReader(gtfsDirectory.resolve("agency.txt"));
		String[] lineValues = reader.readNext();

		int[] valueOrder = new int[7];
		int counter = 0;
		for (String column : lineValues) {
			switch (column) {
				case "agency_id": valueOrder[0] = counter; break;
				case "agency_name": valueOrder[1] = counter; break;
				case "agency_url": valueOrder[2] = counter; break;
				case "agency_timezone": valueOrder[3] = counter; break;
				case "agency_lang": valueOrder[4] = counter; break;
				case "agency_phone": valueOrder[5] = counter; break;
				case "agency_email": valueOrder[6] = counter; break;
			}
			counter++;
		}
		
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
	public void readCalendars() {
		
	}
	public void readCalendarDates() {
		
	}
	public void readFrequencies() {
		
	}
	public void readRoutes() {
		
	}
	public void readStops() {
		
	}
	public void readStopTimes() throws IOException, CsvValidationException {
		gtfsStopTimes = new LinkedList<>();
		gtfsStopTimes.clear();
		CSVReader reader = createReader(gtfsDirectory.resolve("stop_times.txt"));
		String[] lineValues = reader.readNext();

		int[] valueOrder = new int[9];
		int counter = 0;
		for (String column : lineValues) {
			switch (column) {
				case "trip_id": valueOrder[0] = counter;
				case "arrival_time": valueOrder[1] = counter;
				case "departure_time": valueOrder[2] = counter;
				case "stop_id": valueOrder[3] = counter;
				case "stop_sequence": valueOrder[4] = counter;
				case "stop_headsign": valueOrder[5] = counter;
				case "pickup_type": valueOrder[6] = counter;
				case "dropoff_type": valueOrder[7] = counter;
				case "shape_dist_traveled": valueOrder[8] = counter;
			}
			counter++;
		}
		
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
	public void readTransfers() {
		
	}
	public void readTrips() {
		
	}
	
	private CSVReader createReader(Path gtfsFile) throws IOException {
		FileReader fileReader = new FileReader(gtfsFile.toFile());
		CSVReader reader = new CSVReader(fileReader);
		
		return reader;
	}

	// Getters
	public List<GtfsAgency> getGtfsAgencies() {
		return gtfsAgencies;
	}
	public List<GtfsStopTime> getGtfsStopTimes() {
		return gtfsStopTimes;
	}
}
