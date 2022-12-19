package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.Map;

public interface GtfsTableData {
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapSqlTypes = createSqlTypeMap();
	public static final String sqlTableName = null;
	
	private static Map<String, String> createSetterMap() {
		return null;
	}
	private static Map<String, String> createSqlTypeMap() {
		return null;
	}
	
	Method[] getOrderedMethodArray(String[] gtfsHeader);
	
	
}
