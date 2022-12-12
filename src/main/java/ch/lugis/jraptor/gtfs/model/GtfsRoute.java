package ch.lugis.jraptor.gtfs.model;

public class GtfsRoute {
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
	private Integer routeSortOrder;
	
	// Constructor
	public GtfsRoute() {};
	
	public GtfsRoute(String routeId, String agencyId, String routeShortName, String routeLongName, String routeDesc,
			Integer routeType, String routeUrl, String routeColor, String routeTextColor, Integer routeSortOrder) {
		this.routeId = routeId;
		this.agencyId = agencyId;
		this.routeShortName = routeShortName;
		this.routeLongName = routeLongName;
		this.routeDesc = routeDesc;
		this.routeType = routeType;
		this.routeUrl = routeUrl;
		this.routeColor = routeColor;
		this.routeTextColor = routeTextColor;
		this.routeSortOrder = routeSortOrder;
	}
	// Only Strings
	public GtfsRoute(String routeId, String agencyId, String routeShortName, String routeLongName, String routeDesc,
			Integer routeType, String routeUrl, String routeColor, String routeTextColor, String routeSortOrder) {
		this.routeId = routeId;
		this.agencyId = agencyId;
		this.routeShortName = routeShortName;
		this.routeLongName = routeLongName;
		this.routeDesc = routeDesc;
		this.routeType = routeType;
		this.routeUrl = routeUrl;
		this.routeColor = routeColor;
		this.routeTextColor = routeTextColor;
		this.routeSortOrder = Integer.valueOf(routeSortOrder);
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
	public Integer getRouteSortOrder() {
		return routeSortOrder;
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
	public void setRouteUrl(String routeUrl) {
		this.routeUrl = routeUrl;
	}
	public void setRouteColor(String routeColor) {
		this.routeColor = routeColor;
	}
	public void setRouteTextColor(String routeTextColor) {
		this.routeTextColor = routeTextColor;
	}
	public void setRouteSortOrder(Integer routeSortOrder) {
		this.routeSortOrder = routeSortOrder;
	}
	
	// Public static methods
	public static int[] mapFields(String[] headerValues) {
		int[] valueOrder = new int[10];
		int counter = 0;
		for (String column : headerValues) {
			switch (column) {
				case "route_id": valueOrder[0] = counter; break;
				case "agency_id": valueOrder[1] = counter; break;
				case "route_short_name": valueOrder[2] = counter; break;
				case "route_long_name": valueOrder[3] = counter; break;
				case "route_desc": valueOrder[4] = counter; break;
				case "route_type": valueOrder[5] = counter; break;
				case "route_url": valueOrder[6] = counter; break;
				case "route_color": valueOrder[7] = counter; break;
				case "route_text_color": valueOrder[8] = counter; break;
				case "route_sort_order": valueOrder[9] = counter; break;
			}
			counter++;
		}
		return valueOrder;	
	}
}
