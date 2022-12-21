package ch.lugis.jraptor;

import java.io.IOException;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ch.lugis.jraptor.gtfs.model.GtfsAgency;
import ch.lugis.jraptor.gtfs.model.GtfsTableData;
import ch.lugis.jraptor.utils.GtfsReader;
import ch.lugis.jraptor.utils.SqliteHandler;

public class GtfsSqliteReader extends GtfsReader {
	// Fields
	private SqliteHandler sqliteHandler;
	
	public GtfsSqliteReader(String gtfsDirectory) {
		super(gtfsDirectory);
		
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
	
	public void readAgenciesToSqlite() throws IOException {
		logger.info("Read agency.txt to database");
		CSVReader reader = createReader(gtfsDirectory.resolve("agencytxt"));
		readToSqlite(reader, new GtfsAgency(), "agency");
		reader.close();
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
	private void createSqliteHandler(String pathToDatabase) {
		if (pathToDatabase == null || pathToDatabase.isBlank()) {
			pathToDatabase = "GTFS_Data.sqlite";
		}
		sqliteHandler = new SqliteHandler(pathToDatabase);
	}
	
	private <T extends GtfsTableData> void createSqliteTable(String[] header, T gtfsObject) {
		sqliteHandler.executeSql("DROP TABLE IF EXISTS ?", T.sqlTableName);
		// Create table
	}
	
	private <T extends GtfsTableData> void readToSqlite(CSVReader reader, T gtfsObject, String tableName) {
		
	}

}
