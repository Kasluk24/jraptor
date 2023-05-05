package ch.weinetz.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.Map;

public interface GtfsTableData {
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapGetters = createGetterMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = null;
	
	private static Map<String, String> createSetterMap() {
		return null;
	}
	private static Map<String, String> createGetterMap() {
		return null;
	}
	private static Map<String, String> createSqlTypeMap() {
		return null;
	}
	
	Method[] getOrderedSetterArray(String[] gtfsHeader);
	Method[] getOrderedGetterArray(String[] gtfsHeader);
	Map<String, String> getMapSetters();
	Map<String, String> getMapGetters();
	Map<String, String> getMapSqliteTypes();
	String getSqlTableName();
}
