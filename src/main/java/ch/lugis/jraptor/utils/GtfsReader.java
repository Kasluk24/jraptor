package ch.lugis.jraptor.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVReader;

public class GtfsReader {
	// Fields
	protected final Path gtfsDirectory;
	protected Logger logger;
	
	// Constructor
	public GtfsReader(String gtfsDirectory) {
		Path workingDirectory = Paths.get(".");
		if (gtfsDirectory == null) {
			this.gtfsDirectory = workingDirectory;
		} else {
			this.gtfsDirectory = workingDirectory.relativize(Paths.get(gtfsDirectory));
		}
		
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);		
	}
	
	// Protected methods
	protected CSVReader createReader(Path gtfsFile) throws IOException {
		FileReader fileReader = new FileReader(gtfsFile.toFile());
		CSVReader reader = new CSVReader(fileReader);
		
		return reader;
	}
	protected Set<String> getGtfsFiles() {
		File gtfsFolder = gtfsDirectory.toFile();
	    Set<String> gtfsFiles = Stream.of(gtfsFolder.listFiles())
	    	      .filter(file -> !file.isDirectory())
	    	      .map(File::getName)
	    	      .collect(Collectors.toSet());
		
		return gtfsFiles;
	}
}
