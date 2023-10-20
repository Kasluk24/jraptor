package ch.weinetz.jraptor.gtfs.model;

public class GtfsTime {
	// Fields
	private Integer time;  // Time in seconds as Integer
	
	// Constructor
	public GtfsTime() {};
	public GtfsTime(Integer timeAsSeconds) {
		this.time = timeAsSeconds;
	}
	public GtfsTime(Double timeAsMinutes) {
		this.time = convertMinutesToSeconds(timeAsMinutes);
	}
	public GtfsTime(String timeAsString) {
		this.time = convertStringToSecons(timeAsString);
	}
	
	// Getters
	public Integer getTimeAsSeconds() {
		return time;
	}
	public String getTimeAsString() {
		return convertSecondsToString(time);
	}
	
	// Setters
	public void setTime(Integer timeAsSeconds) {
		this.time = timeAsSeconds;
	}
	public void setTime(Double timeAsMinutes) {
		this.time = convertMinutesToSeconds(timeAsMinutes);
	}
	public void setTime(String timeAsString) {
		this.time = convertStringToSecons(timeAsString);
	}
	
	// Public static methods to convert time
	public static Integer convertMinutesToSeconds(Double minutes) {
		return Long.valueOf(Math.round(minutes * 60)).intValue();
	}
	public static Integer convertStringToSecons(String timestring) {
		String[] singleValues = timestring.split(":");
		Integer hours = Integer.valueOf(singleValues[0]);
		Integer minutes = Integer.valueOf(singleValues[1]);
		Integer seconds = Integer.valueOf(singleValues[2]);
		
		return seconds + minutes * 60 + hours * 3600;
	}
	public static String convertSecondsToString(Integer seconds) {
		Integer hours = (Integer) seconds / 3600;
		Integer minutes = (Integer) (seconds - hours * 3600) / 60;
		seconds = Long.valueOf(Math.round(seconds - hours * 3600 - minutes * 60)).intValue();
		
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GtfsTime other = (GtfsTime) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return convertSecondsToString(time);
	}
	
	
	
}
