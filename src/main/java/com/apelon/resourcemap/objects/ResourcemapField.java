package com.apelon.resourcemap.objects;

public class ResourcemapField {

	private String valuesetName; // valueset-c80-facilitycodes
	private String fieldName; // "Facility Type"
	private  String fieldCode; // facility_type
	private int fieldOrder; // 1
	private int nextId; // 2
	private int fieldId;

	public String getValuesetName() {
		return valuesetName;
	}

	public void setValuesetName(String valuesetName) {
		this.valuesetName = valuesetName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public int getFieldOrder() {
		return fieldOrder;
	}

	public void setFieldOrder(int fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
}
