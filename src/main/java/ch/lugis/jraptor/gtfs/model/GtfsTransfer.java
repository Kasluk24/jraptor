package ch.lugis.jraptor.gtfs.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ch.lugis.jraptor.utils.GtfsImport;

public class GtfsTransfer implements GtfsTableData {
	// Fields
	private String fromStopId;
	private String toStopId;
	private GtfsTransferType transferType;
	private Integer minTransferTime;
	public static final Map<String, String> mapSetters = createSettersMap();
	
	private static Map<String, String> createSettersMap() {
		Map<String, String> tempFields = new HashMap<>();
		tempFields.put("from_stop_id", "setFromStopId");
		tempFields.put("to_stop_id", "setToStopId");
		tempFields.put("transfer_type", "setTransferType");
		tempFields.put("min_transfer_time", "setMinTransferTime");
		return tempFields;
	}
	
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
	public void setTransferType(String transferTypeCode) {
		setTransferType(Integer.valueOf(transferTypeCode));
	}
	public void setMinTransferTime(Integer minTransferTime) {
		this.minTransferTime = minTransferTime;
	}
	public void setMinTransferTime(String minTransferTime) {
		this.minTransferTime = Integer.valueOf(minTransferTime);
	}
	
	@Override
	public String toString() {
		return "GtfsTransfer [fromStopId=" + fromStopId + ", toStopId=" + toStopId + ", transferType=" + transferType
				+ ", minTransferTime=" + minTransferTime + "]";
	}

	@Override
	public Method[] getOrderedSetterArray(String[] gtfsHeader) {
		Class<GtfsTransfer> classObject = GtfsTransfer.class;
		return GtfsImport.createOrderedMethodArray(classObject, mapSetters, gtfsHeader, String.class);
	}
}
