package ch.lugis.jraptor.gtfs.model;


public class GtfsDate {
	
	// Fields
	private Integer year;
	private Integer month;
	private Integer day;
	
	// Constructor
	public GtfsDate() {};
	
	public GtfsDate(String dateAsString) {
		this.year = convertDatestringToYear(dateAsString);
		this.month = convertDatestringToMonth(dateAsString);
		this.day = convertDatestringToDay(dateAsString);
	}
	public GtfsDate(Integer year, Integer month, Integer day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	// Getters
	public String getDateAsString() {
		return convertYmdToString(year, month, day);
	}
	public Integer getYear() {
		return year;
	}
	public Integer getMonth() {
		return month;
	}
	public Integer getDay() {
		return day;
	}
	
	// Setters
	public void setDate(String datestring) {
		this.year = convertDatestringToYear(datestring);
		this.month = convertDatestringToMonth(datestring);
		this.day = convertDatestringToDay(datestring);
	}
	public void setDate(Integer year, Integer month, Integer day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	// Public static methods to convert date
	public static Integer convertDatestringToYear(String datestring) {
		return Integer.valueOf(datestring.substring(0, 4));
	}
	public static Integer convertDatestringToMonth(String datestring) {
		return Integer.valueOf(datestring.substring(4, 6));
	}
	public static Integer convertDatestringToDay(String datestring) {
		return Integer.valueOf(datestring.substring(6, 8));
	}
	public static String convertYmdToString(Integer year, Integer month, Integer day) {
		return String.format("%04d%02d%02d", year, month, day);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	public boolean equals(GtfsDate date) {
		if (year.equals(date.getYear()) && 
				month.equals(date.getMonth()) && 
				day.equals(date.getDay())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean equals(String datestring) {
		if (year.equals(convertDatestringToYear(datestring)) && 
				month.equals(convertDatestringToMonth(datestring)) && 
				day.equals(convertDatestringToDay(datestring))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean before(GtfsDate date) {
		return getBefore(date.getYear(), date.getMonth(), date.getDay());
	}
	public boolean before(String datestring) {
		return getBefore(convertDatestringToYear(datestring),
				convertDatestringToMonth(datestring),
				convertDatestringToDay(datestring));
	}
	
	public boolean after(GtfsDate date) {
		return getAfter(date.getYear(), date.getMonth(), date.getDay());
	}
	public boolean after(String datestring) {
		return getAfter(convertDatestringToYear(datestring),
				convertDatestringToMonth(datestring),
				convertDatestringToDay(datestring));
	}
	
	@Override
	public String toString() {
		return convertYmdToString(year, month, day);
	}
	
	// Private methods
	private boolean getBefore(Integer year, Integer month, Integer day) {
		// Equal dates are always included
		// Check year
		if (this.year < year) {
			return true;
		} else if (this.year > year) {
			return false;
		} else {
			// Check month if year is equal
			if (this.month < month) {
				return true;
			} else if (this.month > month) {
				return false;
			} else {
				// Check day if month and year is equal
				if (this.day <= day) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
	private boolean getAfter(Integer year, Integer month, Integer day) {
		// Equal dates are always included
		// Check year
		if (this.year > year) {
			return true;
		} else if (this.year < year) {
			return false;
		} else {
			// Check month if year is equal
			if (this.month > month) {
				return true;
			} else if (this.month < month) {
				return false;
			} else {
				// Check day if year and month is equal
				if (this.day > day) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

}
