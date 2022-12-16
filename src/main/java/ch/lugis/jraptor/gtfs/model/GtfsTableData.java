package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.Map;

public interface GtfsTableData {
	public static final Map<String, String> mapSetters = createSetterMap();
	
	private static Map<String, String> createSetterMap() {
		return null;
	}
	
	Method[] getOrderedMethodArray(String[] gtfsHeader);
}
