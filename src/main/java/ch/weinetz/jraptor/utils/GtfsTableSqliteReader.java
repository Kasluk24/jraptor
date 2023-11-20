package ch.weinetz.jraptor.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ch.weinetz.jraptor.gtfs.model.GtfsTableData;

public class GtfsTableSqliteReader <T extends GtfsTableData> implements Runnable  {
	private Set<T> gtfsTable = new HashSet<>();
	protected final Path gtfsDatabase;
	protected Logger logger;
	private Class<T> gtfsClass;
	private SqliteHandler sqliteHandler;
	
	// Constructor
	public GtfsTableSqliteReader(String gtfsDatabase, Class<T> gtfsClass) {
		this.gtfsDatabase = GtfsImportUtils.getRelativePath(gtfsDatabase);
		this.gtfsClass = gtfsClass;
		this.sqliteHandler = new SqliteHandler(gtfsDatabase);
		
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);
	}

	@Override
	public void run() {
		startReader();
	}
	
	
	// Getter
	public Set<T> getGtfsTable() {
		if (gtfsTable.size() == 0) {
			startReader();
		}
		return gtfsTable;
	}	

	// Private methods
	private void startReader() {
		try {			
			T gtfsObject = gtfsClass.getConstructor().newInstance();
			String gtfsTableName = gtfsObject.getSqlTableName();
			logger.info("Read " + gtfsTableName);
			
			if (sqliteHandler.getDataTables().contains(gtfsTableName)) {
				readToMemory(gtfsTableName, gtfsObject);
			}
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			// Internal error
			logger.severe(String.format("Fatal error in %s class", gtfsClass.getName()));
			e.printStackTrace();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readToMemory(String tableName, T gtfsObject) throws CsvValidationException, IOException {
		List<String> header = sqliteHandler.getColumnNames(tableName); // Reads the header
		Method[] setterMethods = gtfsObject.getOrderedSetterArray(header.toArray(new String[0]));
		
		ResultSet results = sqliteHandler.executeSql(String.format("SELECT * FROM %s;", tableName));
		try {
			while (results.next()) {
				T gtfsObjectInstance = gtfsClass.getConstructor().newInstance();
				for (int i = 0; i < header.size(); i++) {
					if (setterMethods[i] != null) {
						setterMethods[i].invoke(gtfsObjectInstance, results.getString(i + 1));
					}
					i++;
				}
				gtfsTable.add(gtfsObjectInstance);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | SecurityException | NoSuchMethodException e) {
			// Internal error
			logger.severe(String.format("Fatal error in %s class", gtfsClass.getName()));
			e.printStackTrace();
		} catch (SQLException e) {
			logger.severe(String.format("Fatal sql error while reading data to %s class", gtfsClass.getName()));
			e.printStackTrace();
		}
	}

}
