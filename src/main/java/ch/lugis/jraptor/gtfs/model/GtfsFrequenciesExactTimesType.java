package ch.lugis.jraptor.gtfs.model;

public enum GtfsFrequenciesExactTimesType {
	FREQUENCY_BASED(0),
	SHEDULE_BASED(1);
	
	private final int code;

	GtfsFrequenciesExactTimesType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static GtfsFrequenciesExactTimesType getTypeByCode(int code) {
		for (GtfsFrequenciesExactTimesType type : GtfsFrequenciesExactTimesType.values()) {
			return type;
		}
		return null;
	}
}
