package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

public class GtfsRoute implements GtfsTableData {
	// Fields
	private String routeId;
	private String agencyId;
	private String routeShortName;
	private String routeLongName;
	private String routeDesc;
	private Integer routeType;
	private String routeUrl;
	private String routeColor;
	private String routeTextColor;
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapGetters = createGetterMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = "routes";
	
	// Constructor
	public GtfsRoute() {};
	
	public GtfsRoute(String routeId, String agencyId, String routeShortName, String routeLongName, String routeDesc,
			Integer routeType, String routeUrl, String routeColor, String routeTextColor) {
		this.routeId = routeId;
		this.agencyId = agencyId;
		this.routeShortName = routeShortName;
		this.routeLongName = routeLongName;
		this.routeDesc = routeDesc;
		this.routeType = routeType;
		this.routeUrl = routeUrl;
		this.routeColor = routeColor;
		this.routeTextColor = routeTextColor;
	}
	// Only Strings
	public GtfsRoute(String routeId, String agencyId, String routeShortName, String routeLongName, String routeDesc,
			String routeType, String routeUrl, String routeColor, String routeTextColor) {
		this.routeId = routeId;
		this.agencyId = agencyId;
		this.routeShortName = routeShortName;
		this.routeLongName = routeLongName;
		this.routeDesc = routeDesc;
		this.routeType = Integer.valueOf(routeType);
		this.routeUrl = routeUrl;
		this.routeColor = routeColor;
		this.routeTextColor = routeTextColor;
	}
	
	// Getters
	public String getRouteId() {
		return routeId;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public String getRouteShortName() {
		return routeShortName;
	}
	public String getRouteLongName() {
		return routeLongName;
	}
	public String getRouteDesc() {
		return routeDesc;
	}
	public Integer getRouteType() {
		return routeType;
	}
	public String getRouteTypeAsString() {
		return String.valueOf(routeType);
	}
	public String getRouteUrl() {
		return routeUrl;
	}
	public String getRouteColor() {
		return routeColor;
	}
	public String getRouteTextColor() {
		return routeTextColor;
	}
	public Map<String, String> getMapSetters() {
		return mapSetters;
	}
	public Map<String, String> getMapGetters() {
		return mapGetters;
	}
	public Map<String, String> getMapSqliteTypes() {
		return mapSqliteTypes;
	}
	public String getSqlTableName() {
		return sqlTableName;
	}
	
	// Setters
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public void setRouteShortName(String routeShortName) {
		this.routeShortName = routeShortName;
	}
	public void setRouteLongName(String routeLongName) {
		this.routeLongName = routeLongName;
	}
	public void setRouteDesc(String routeDesc) {
		this.routeDesc = routeDesc;
	}
	public void setRouteType(Integer routeType) {
		this.routeType = routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = Integer.valueOf(routeType);
	}
	public void setRouteUrl(String routeUrl) {
		this.routeUrl = routeUrl;
	}
	public void setRouteColor(String routeColor) {
		this.routeColor = routeColor;
	}
	public void setRouteTextColor(String routeTextColor) {
		this.routeTextColor = routeTextColor;
	}
	
	@Override
	public String toString() {
		return "GtfsRoute [routeId=" + routeId + ", agencyId=" + agencyId + ", routeShortName=" + routeShortName
				+ ", routeLongName=" + routeLongName + ", routeDesc=" + routeDesc + ", routeType=" + routeType
				+ ", routeUrl=" + routeUrl + ", routeColor=" + routeColor + ", routeTextColor=" + routeTextColor + "]";
	}
	@Override
	public Method[] getOrderedSetterArray(String[] gtfsHeader) {
		Class<GtfsRoute> classObject = GtfsRoute.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}	
	@Override
	public Method[] getOrderedGetterArray(String[] gtfsHeader) {
		Class<GtfsRoute> classObject = GtfsRoute.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapGetters, gtfsHeader);
	}	
	
	// Private static methods
	private static Map<String, String> createSetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("route_id", "setRouteId");
		tempFields.put("agency_id", "setAgencyId");
		tempFields.put("route_short_name", "setRouteShortName");
		tempFields.put("route_long_name", "setRouteLongName");
		tempFields.put("route_type", "setRouteType");
		tempFields.put("route_url", "setRouteUrl");
		tempFields.put("route_color", "setRouteColor");
		tempFields.put("route_text_color", "setRouteTextColor");
		return tempFields;
	}
	private static Map<String, String> createGetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("route_id", "getRouteId");
		tempFields.put("agency_id", "getAgencyId");
		tempFields.put("route_short_name", "getRouteShortName");
		tempFields.put("route_long_name", "getRouteLongName");
		tempFields.put("route_type", "getRouteTypeAsString");
		tempFields.put("route_url", "getRouteUrl");
		tempFields.put("route_color", "getRouteColor");
		tempFields.put("route_text_color", "getRouteTextColor");
		return tempFields;
	}
	private static Map<String, String> createSqlTypeMap() {
		Map<String, String> tempTypes = new HashMap<>();
		tempTypes.put("route_id", "TEXT PRIMARY KEY");
		tempTypes.put("agency_id", "TEXT");
		tempTypes.put("route_short_name", "TEXT");
		tempTypes.put("route_long_name", "TEXT");
		tempTypes.put("route_type", "INT");
		tempTypes.put("route_url", "TEXT");
		tempTypes.put("route_color", "TEXT");
		tempTypes.put("route_text_color", "TEXT");
		return tempTypes;
	}
}
