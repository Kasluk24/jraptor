package ch.lugis.jraptor.gtfs.model;

public class GtfsFrequency {
	// Fields
	private String tripId;
	private GtfsTime startTime;
	private GtfsTime endTime;
	private Integer headwaySecs;
	private GtfsFrequenciesExactTimesType exactTime;
	
	// Constructor
	public GtfsFrequency() {}

	public GtfsFrequency(String tripId, GtfsTime startTime, GtfsTime endTime, Integer headwaySecs,
			GtfsFrequenciesExactTimesType exactTime) {
		this.tripId = tripId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.headwaySecs = headwaySecs;
		this.exactTime = exactTime;
	}
	// Only Strings
	public GtfsFrequency(String tripId, String startTime, String endTime, String headwaySecs,
			String exactTime) {
		this.tripId = tripId;
		setStartTime(startTime);
		setEndTime(endTime);
		this.headwaySecs = Integer.valueOf(headwaySecs);
		setExactTime(Integer.valueOf(exactTime));
	}

	// Getters
	public String getTripId() {
		return tripId;
	}
	public GtfsTime getStartTime() {
		return startTime;
	}
	public String getStartTimeAsString() {
		return startTime.getTimeAsString();
	}
	public GtfsTime getEndTime() {
		return endTime;
	}
	public String getEndTimeAsString() {
		return endTime.getTimeAsString();
	}
	public Integer getHeadwaySecs() {
		return headwaySecs;
	}
	public GtfsFrequenciesExactTimesType getExactTime() {
		return exactTime;
	}
	public int getExactTimeCode() {
		return exactTime.getCode();
	}

	// Setters
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public void setStartTime(GtfsTime startTime) {
		this.startTime = startTime;
	}
	public void setStartTime(String timestring) {
		this.startTime = new GtfsTime(timestring);
	}
	public void setEndTime(GtfsTime endTime) {
		this.endTime = endTime;
	}
	public void setEndTime(String timestring) {
		this.endTime = new GtfsTime(timestring);
	}
	public void setHeadwaySecs(Integer headwaySecs) {
		this.headwaySecs = headwaySecs;
	}
	public void setExactTime(GtfsFrequenciesExactTimesType exactTime) {
		this.exactTime = exactTime;
	}
	public void setExactTime(int exactTimeCode) {
		if (GtfsFrequenciesExactTimesType.getTypeByCode(exactTimeCode) != null) {
			this.exactTime = GtfsFrequenciesExactTimesType.getTypeByCode(exactTimeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS exact time type", 
							exactTimeCode));
		}
	}
	
	// Public static methods
	public static int[] mapFields(String[] headerValues) {
		int[] valueOrder = new int[5];
		int counter = 0;
		for (String column : headerValues) {
			switch (column) {
				case "trip_id": valueOrder[0] = counter; break;
				case "start_time": valueOrder[1] = counter; break;
				case "end_time": valueOrder[2] = counter; break;
				case "headway_secs": valueOrder[3] = counter; break;
				case "exact_time": valueOrder[4] = counter; break;
			}
			counter++;
		}
		return valueOrder;	
	}
}
