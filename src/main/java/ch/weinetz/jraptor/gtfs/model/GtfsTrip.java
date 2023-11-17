package ch.weinetz.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.weinetz.jraptor.utils.GtfsImportUtils;

public class GtfsTrip implements GtfsTableData  {
	// Fields
	private String routeId;
	private String serviceId;
	private String tripId;
	private String tripHeadsign;
	private String tripShortName;
	private GtfsDirectionId directionId;
	private String blockId;
	private String shapeId;
	public static final Map<String, String> mapSetters = createSetterMap();
	public static final Map<String, String> mapGetters = createGetterMap();
	public static final Map<String, String> mapSqliteTypes = createSqlTypeMap();
	public static final String sqlTableName = "trips";
	public static final String gtfsFileName = "trips.txt";
	
	// Constructor
	public GtfsTrip() {};
	
	public GtfsTrip(String routeId, String serviceId, String tripId, String tripHeadsign, String tripShortName,
			GtfsDirectionId directionId, String blockId, String shapeId) {
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.tripId = tripId;
		this.tripHeadsign = tripHeadsign;
		this.tripShortName = tripShortName;
		this.directionId = directionId;
		this.blockId = blockId;
		this.shapeId = shapeId;
	}
	// Only Strings
	public GtfsTrip(String routeId, String serviceId, String tripId, String tripHeadsign, String tripShortName,
			String directionId, String blockId, String shapeId) {
		this.routeId = routeId;
		this.serviceId = serviceId;
		this.tripId = tripId;
		this.tripHeadsign = tripHeadsign;
		this.tripShortName = tripShortName;
		setDirectionId(Integer.valueOf(directionId));
		this.blockId = blockId;
		this.shapeId = shapeId;
	}
	
	// Getters
	public String getRouteId() {
		return routeId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public String getTripId() {
		return tripId;
	}
	public String getTripHeadsign() {
		return tripHeadsign;
	}
	public String getTripShortName() {
		return tripShortName;
	}
	public GtfsDirectionId getDirectionId() {
		return directionId;
	}
	public int getDirectionIdCode() {
		return directionId.getCode();
	}
	public String getDirectionIdCodeAsString() {
		return String.valueOf(directionId.getCode());
	}
	public String getBlockId() {
		return blockId;
	}
	public String getShapeId() {
		return shapeId;
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
	public String getGtfsFileName() {
		return gtfsFileName;
	}
	
	// Setters
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public void setTripHeadsign(String tripHeadsign) {
		this.tripHeadsign = tripHeadsign;
	}
	public void setTripShortName(String tripShortName) {
		this.tripShortName = tripShortName;
	}
	public void setDirectionId(GtfsDirectionId directionId) {
		this.directionId = directionId;
	}
	public void setDirectionId(int directionIdCode) {
		if (GtfsDirectionId.getIdByCode(directionIdCode) != null) {
			this.directionId = GtfsDirectionId.getIdByCode(directionIdCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS direction id", 
							directionIdCode));
		}
	}
	public void setDirectionId(String directionIdCode) {
		setDirectionId(Integer.valueOf(directionIdCode));
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}
	
	@Override
	public String toString() {
		return "GtfsTrip [routeId=" + routeId + ", serviceId=" + serviceId + ", tripId=" + tripId + ", tripHeadsign="
				+ tripHeadsign + ", tripShortName=" + tripShortName + ", directionId=" + directionId + ", blockId="
				+ blockId + ", shapeId=" + shapeId + "]";
	}
	@Override
	public Method[] getOrderedSetterArray(String[] gtfsHeader) {
		Class<GtfsTrip> classObject = GtfsTrip.class;
		return GtfsImportUtils.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}	
	@Override
	public Method[] getOrderedGetterArray(String[] gtfsHeader) {
		Class<GtfsTrip> classObject = GtfsTrip.class;
		return GtfsImportUtils.createOrderedMethodArray(classObject, mapGetters, gtfsHeader);
	}
	@Override
	public boolean equals(Object object) {
		if (!object.getClass().equals(GtfsTrip.class)) {
			return false;
		} else if (((GtfsTrip) object).getTripId().equals(this.tripId)) {
			return true;
		} else {
			return false;
		}
	}		
	
	// Private static methods
	private static Map<String, String> createSetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("route_id", "setRouteId");
		tempFields.put("service_id", "setServiceId");
		tempFields.put("trip_id", "setTripId");
		tempFields.put("trip_headsign", "setTripHeadsign");
		tempFields.put("trip_short_name", "setTripShortName");
		tempFields.put("direction_id", "setDirectionId");
		tempFields.put("block_id", "setBlockId");
		tempFields.put("shape_id", "setShapeId");
		return tempFields;
	}
	private static Map<String, String> createGetterMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("route_id", "getRouteId");
		tempFields.put("service_id", "getServiceId");
		tempFields.put("trip_id", "getTripId");
		tempFields.put("trip_headsign", "getTripHeadsign");
		tempFields.put("trip_short_name", "getTripShortName");
		tempFields.put("direction_id", "getDirectionIdCodeAsString");
		tempFields.put("block_id", "getBlockId");
		tempFields.put("shape_id", "getShapeId");
		return tempFields;
	}
	private static Map<String, String> createSqlTypeMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("route_id", "TEXT");
		tempFields.put("service_id", "TEXT");
		tempFields.put("trip_id", "TEXT PRIMARY KEY");
		tempFields.put("trip_headsign", "TEXT");
		tempFields.put("trip_short_name", "TEXT");
		tempFields.put("direction_id", "INT");
		tempFields.put("block_id", "TEXT");
		tempFields.put("shape_id", "TEXT");
		return tempFields;
	}
}
