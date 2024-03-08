package ch.weinetz.jraptor.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ch.weinetz.jraptor.gtfs.model.GtfsAgency;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendar;
import ch.weinetz.jraptor.gtfs.model.GtfsCalendarDate;
import ch.weinetz.jraptor.gtfs.model.GtfsFrequency;
import ch.weinetz.jraptor.gtfs.model.GtfsRoute;
import ch.weinetz.jraptor.gtfs.model.GtfsStop;
import ch.weinetz.jraptor.gtfs.model.GtfsStopTime;
import ch.weinetz.jraptor.gtfs.model.GtfsTableData;
import ch.weinetz.jraptor.gtfs.model.GtfsTransfer;
import ch.weinetz.jraptor.gtfs.model.GtfsTrip;

public class GtfsFileToSqliteConverter {
	// Fields
	private final Path gtfsDirectory;
	private Logger logger;
	private SqliteHandler sqliteHandler;
	private boolean closeConnection;
	
	public GtfsFileToSqliteConverter(String gtfsDirectory, String pathToDatabase) {
		this.gtfsDirectory = GtfsImportUtils.getAbsolutePath(gtfsDirectory);
		this.sqliteHandler = new SqliteHandler(pathToDatabase);
		this.closeConnection = true;
		
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);
	}
	
	public void readAllToSqlite() throws IOException, CsvValidationException, SQLException {
		Set<String> gtfsFiles = GtfsImportUtils.getGtfsFiles(gtfsDirectory);
		this.closeConnection = false;
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
	    sqliteHandler.closeConnection();
	}
	
	public void readAgenciesToSqlite() throws IOException, CsvValidationException, SQLException {
		logger.info("Read agency.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("agency.txt"));
		readToSqlite(reader, new GtfsAgency());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readCalendarsToSqlite() throws IOException, CsvValidationException, SQLException {
		logger.info("Read calendar.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("calendar.txt"));
		readToSqlite(reader, new GtfsCalendar());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readCalendarDatesToSqlite() throws CsvValidationException, IOException, SQLException {
		logger.info("Read calendar_dates.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("calendar_dates.txt"));
		readToSqlite(reader, new GtfsCalendarDate());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readFrequenciesToSqlite() throws CsvValidationException, IOException, SQLException {
		logger.info("Read frequencies.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("frequencies.txt"));
		readToSqlite(reader, new GtfsFrequency());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readRoutesToSqlite() throws CsvValidationException, IOException, SQLException {
		logger.info("Read routes.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("routes.txt"));
		readToSqlite(reader, new GtfsRoute());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readStopsToSqlite() throws CsvValidationException, IOException, SQLException {
		logger.info("Read stops.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("stops.txt"));
		readToSqlite(reader, new GtfsStop());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readStopTimesToSqlite() throws CsvValidationException, IOException, SQLException {
		logger.info("Read stop_times.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("stop_times.txt"));
		readToSqlite(reader, new GtfsStopTime());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readTransfersToSqlite() throws CsvValidationException, IOException, SQLException {
		logger.info("Read transfers.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("transfers.txt"));
		readToSqlite(reader, new GtfsTransfer());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	public void readTripsToSqlite() throws CsvValidationException, IOException, SQLException {
		logger.info("Read trips.txt to database");
		CSVReader reader = GtfsImportUtils.createCsvReader(gtfsDirectory.resolve("trips.txt"));
		readToSqlite(reader, new GtfsTrip());
		reader.close();
		if (closeConnection) sqliteHandler.closeConnection();
	}
	
	// Private methods
	private <T extends GtfsTableData> void createSqliteTable(String[] header, T gtfsObject) {
		sqliteHandler.executeSql(String.format("DROP TABLE IF EXISTS %s;", gtfsObject.getSqlTableName()));
		
		StringBuilder createTableSql = new StringBuilder();
		createTableSql.append(String.format("CREATE TABLE %s (", gtfsObject.getSqlTableName()));
		for (String column : header) {
			createTableSql.append(column);
			createTableSql.append(" ");
			createTableSql.append(gtfsObject.getMapSqliteTypes().get(column));
			createTableSql.append(", ");
		}
		createTableSql.replace(createTableSql.length() - 2, createTableSql.length(), ");");
		
		sqliteHandler.executeSql(createTableSql.toString());
	}
	
	private <T extends GtfsTableData> void readToSqlite(CSVReader reader, T gtfsObject) throws CsvValidationException, IOException, SQLException {
		String[] lineValues = reader.readNext(); // Reads the header
		createSqliteTable(lineValues, gtfsObject);
		@SuppressWarnings("unchecked")
		Class<T> gtfsClass = (Class<T>)gtfsObject.getClass();
		Method[] setterMethods = gtfsObject.getOrderedSetterArray(lineValues);
		Method[] getterMethods = gtfsObject.getOrderedGetterArray(lineValues);
		
		String insertSql = "INSERT INTO " + 
				gtfsObject.getSqlTableName() + 
				" VALUES (" + 
				GtfsImportUtils.createSeparatedString(lineValues.length, ", ", "?") +
				");";
		Connection connection = sqliteHandler.getConnection();
		connection.setAutoCommit(false);
		PreparedStatement insertStatement = connection.prepareStatement(insertSql);
		
		try {
			while ((lineValues = reader.readNext()) != null) {
				T gtfsObjectInstance = gtfsClass.getConstructor().newInstance();
				int i = 0;
				for (String value : lineValues) {
					if (setterMethods[i] != null) {
							setterMethods[i].invoke(gtfsObjectInstance, value);
							insertStatement.setString(i + 1, (String)getterMethods[i].invoke(gtfsObjectInstance));	
					}
					i++;
				}
				sqliteHandler.executeSql(insertStatement);	
			}
		} catch (IllegalArgumentException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// Internal error
			logger.severe(String.format("Fatal error in %s class", gtfsClass.getName()));
			e.printStackTrace();
		} finally {
			connection.commit();
		}
	}
}
