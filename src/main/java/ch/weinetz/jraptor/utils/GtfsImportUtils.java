package ch.weinetz.jraptor.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVReader;

public class GtfsImportUtils {
	
	// Creates an array of methods in the same order as a given array with headers
	// The map methodHeaderMapping 
	
	public static Method[] createOrderedMethodArray(
			Class<?> classObject, 
			Map<String, String> methodHeaderMapping, 
			String[] headers,
			Class<?>... parameterTypes) {
		Method[] methods = new Method[headers.length];
		int i = 0;
		for (String header : headers) {
			if (methodHeaderMapping.containsKey(header)) {
				try {
					methods[i] = classObject.getMethod(methodHeaderMapping.get(header), parameterTypes);
				} catch (NoSuchMethodException | SecurityException e) {
					// Fatal error
					e.printStackTrace();
				}
			} else {
				methods[i] = null;
			}
			i++;
		}
		return methods;		
	}
	
	public static String createSeparatedString(int count, String separator, String character) {
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<count; i++) {
			builder.append(character + separator);
		}
		return builder.substring(0, builder.length() - separator.length()).toString();
	}
	
	public static CSVReader createCsvReader(Path gtfsFile) throws IOException {
		FileReader fileReader = new FileReader(gtfsFile.toFile());
		CSVReader reader = new CSVReader(fileReader);
		
		return reader;
	}
	
	public static Set<String> getGtfsFiles(Path gtfsDirectory) {
		File gtfsFolder = gtfsDirectory.toFile();
	    Set<String> gtfsFiles = Stream.of(gtfsFolder.listFiles())
	    	      .filter(file -> !file.isDirectory())
	    	      .map(File::getName)
	    	      .collect(Collectors.toSet());
		
		return gtfsFiles;
	}
	
	public static Path getRelativePath(String path) {
		Path workingDirectory = Paths.get(".");
		if (path == null) {
			return workingDirectory;
		} else {
			return workingDirectory.relativize(Paths.get(path));
		}
	}

}
