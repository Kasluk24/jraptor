package ch.lugis.jraptor.gtfs.model;

public class GtfsTransfer {
	// Fields
	private String fromStopId;
	private String toStopId;
	private GtfsTransferType transferType;
	private Integer minTransferTime;
	
	// Constructor
	public GtfsTransfer() {}

	public GtfsTransfer(String fromStopId, String toStopId, GtfsTransferType transferType, Integer minTransferTime) {
		this.fromStopId = fromStopId;
		this.toStopId = toStopId;
		this.transferType = transferType;
		this.minTransferTime = minTransferTime;
	}
	// Only Strings
	public GtfsTransfer(String fromStopId, String toStopId, String transferType, String minTransferTime) {
		this.fromStopId = fromStopId;
		this.toStopId = toStopId;
		setTransferType(Integer.valueOf(transferType));
		this.minTransferTime = Integer.valueOf(minTransferTime);
	}

	// Getters
	public String getFromStopId() {
		return fromStopId;
	}
	public String getToStopId() {
		return toStopId;
	}
	public GtfsTransferType getTransferType() {
		return transferType;
	}
	public int getTransferTypeCode() {
		return transferType.getCode();
	}
	public Integer getMinTransferTime() {
		return minTransferTime;
	}
	
	// Setters
	public void setFromStopId(String fromStopId) {
		this.fromStopId = fromStopId;
	}
	public void setToStopId(String toStopId) {
		this.toStopId = toStopId;
	}
	public void setTransferType(GtfsTransferType transferType) {
		this.transferType = transferType;
	}
	public void setTransferType(int transferTypeCode) {
		if (GtfsTransferType.getTypeByCode(transferTypeCode) != null) {
			this.transferType = GtfsTransferType.getTypeByCode(transferTypeCode);
		} else {
			throw new IllegalArgumentException(
					String.format(
							"The value %s is not a valid GTFS transfer type", 
							transferTypeCode));
		}
	}
	public void setMinTransferTime(Integer minTransferTime) {
		this.minTransferTime = minTransferTime;
	}
}