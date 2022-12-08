package ch.lugis.jraptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ch.lugis.jraptor.gtfs.model.*;

public class GtfsReader {
	// Fields
	private final String gtfsDirectory;
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
			this.gtfsDirectory = ".";
		} else {
			this.gtfsDirectory = gtfsDirectory;
		}
	}
	
	// Read functions
	public void readAll() {
		
	}
	
	public void readAgencies() throws IOException, CsvValidationException {
		gtfsAgencies.clear();
		CSVReader reader = createReader(gtfsDirectory + "/agencies.txt");
		String[] lineValues = reader.readNext();

		int[] valueOrder = new int[lineValues.length];
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
		
		GtfsAgency agency = new GtfsAgency();
		while ((lineValues = reader.readNext()) != null) {
			
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
	public void readStopTimes() {
		
	}
	public void readTransfers() {
		
	}
	public void readTrips() {
		
	}
	
	private CSVReader createReader(String filePath) throws IOException {
		FileReader fileReader = new FileReader(filePath);
		CSVReader reader = new CSVReader(fileReader);
		
		return reader;
	}
}
