package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

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
	public static final Map<String, String> mapSetters = createSettersMap();
	
	private static Map<String, String> createSettersMap() {
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
	public String getBlockId() {
		return blockId;
	}
	public String getShapeId() {
		return shapeId;
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
	public Method[] getOrderedMethodArray(String[] gtfsHeader) {
		Class<GtfsTrip> classObject = GtfsTrip.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}	
}
