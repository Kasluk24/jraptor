package ch.lugis.jraptor.gtfs.model;

public class GtfsStop {
	// Fields
	private String stopId;
	private String stopCode;
	private String stopName;
	private String stopDesc;
	private Double stopLat;
	private Double stopLon;
	private GtfsLocationType locationType;
	private String parentStation;
	private String stopTimezone;
	
	// Constructor
	public GtfsStop() {};
	
	public GtfsStop(String stopId, String stopCode, String stopName, String stopDesc, Double stopLat, Double stopLon,
			GtfsLocationType locationType, String parentStation, String stopTimezone) {
		this.stopId = stopId;
		this.stopCode = stopCode;
		this.stopName = stopName;
		this.stopDesc = stopDesc;
		this.stopLat = stopLat;
		this.stopLon = stopLon;
		this.locationType = locationType;
		this.parentStation = parentStation;
		this.stopTimezone = stopTimezone;
	}
	
	// Getters
	public String getStopId() {
		return stopId;
	}
	public String getStopCode() {
		return stopCode;
	}
	public String getStopName() {
		return stopName;
	}
	public String getStopDesc() {
		return stopDesc;
	}
	public Double getStopLat() {
		return stopLat;
	}
	public Double getStopLon() {
		return stopLon;
	}
	public GtfsLocationType getLocationType() {
		return locationType;
	}
	public int getLocationTypeCode() {
		return locationType.getCode();
	}
	public String getParentStation() {
		return parentStation;
	}
	public String getStopTimezone() {
		return stopTimezone;
	}
	
	// Setters
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public void setStopCode(String stopCode) {
		this.stopCode = stopCode;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public void setStopDesc(String stopDesc) {
		this.stopDesc = stopDesc;
	}
	public void setStopLat(Double stopLat) {
		this.stopLat = stopLat;
	}
	public void setStopLon(Double stopLon) {
		this.stopLon = stopLon;
	}
	public void setLocationType(GtfsLocationType locationType) {
		this.locationType = locationType;
	}
	public void setLocationType(int locationTypeCode) {
		if (GtfsLocationType.getTypeByCode(locationTypeCode) != null) {
			this.locationType = GtfsLocationType.getTypeByCode(locationTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS location type", 
							locationTypeCode));
		}
	}
	public void setParentStation(String parentStation) {
		this.parentStation = parentStation;
	}
	public void setStopTimezone(String stopTimezone) {
		this.stopTimezone = stopTimezone;
	}
	
}
