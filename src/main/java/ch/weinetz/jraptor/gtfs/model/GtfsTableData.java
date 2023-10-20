package ch.weinetz.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.Map;

public interface GtfsTableData {
	Method[] getOrderedSetterArray(String[] gtfsHeader);
	Method[] getOrderedGetterArray(String[] gtfsHeader);
	Map<String, String> getMapSetters();
	Map<String, String> getMapGetters();
	Map<String, String> getMapSqliteTypes();
	String getSqlTableName();
	String getGtfsFileName();
}
