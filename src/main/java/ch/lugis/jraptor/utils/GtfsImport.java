package ch.lugis.jraptor.utils;

import java.lang.reflect.Method;
import java.util.Map;

public class GtfsImport {
	
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
}
