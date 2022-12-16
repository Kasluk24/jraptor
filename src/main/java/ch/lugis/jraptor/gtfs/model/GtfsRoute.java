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
	public static Map<String, String> mapSetters = createSetterMap();
	
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
	public String getRouteUrl() {
		return routeUrl;
	}
	public String getRouteColor() {
		return routeColor;
	}
	public String getRouteTextColor() {
		return routeTextColor;
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
	public Method[] getOrderedMethodArray(String[] gtfsHeader) {
		Class<GtfsRoute> classObject = GtfsRoute.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}
}
