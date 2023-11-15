package ch.weinetz.jraptor.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ch.weinetz.jraptor.gtfs.model.GtfsTableData;

public class GtfsTableReader <T extends GtfsTableData> extends GtfsReader implements Runnable  {
	private Set<T> gtfsTable = new HashSet<>();
	private Class<T> gtfsClass;
	
	// Constructor
	public GtfsTableReader(String gtfsDirectory, Class<T> gtfsClass) {
		super(gtfsDirectory);
		this.gtfsClass = gtfsClass;
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
			String gtfsFileName = gtfsObject.getGtfsFileName();
			logger.info("Read " + gtfsFileName + " to memory");
			if (getGtfsFiles().contains(gtfsFileName)) {
				CSVReader reader = createReader(gtfsDirectory.resolve(gtfsFileName));
				readToMemory(reader, gtfsObject);
				reader.close();
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
	
	private void readToMemory(CSVReader reader, T gtfsObject) throws CsvValidationException, IOException {
		String[] lineValues = reader.readNext(); // Reads the header
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
				gtfsTable.add(gtfsObjectInstance);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | SecurityException | NoSuchMethodException e) {
				// Internal error
				logger.severe(String.format("Fatal error in %s class", gtfsClass.getName()));
				e.printStackTrace();
			}
		}
	}	

}
