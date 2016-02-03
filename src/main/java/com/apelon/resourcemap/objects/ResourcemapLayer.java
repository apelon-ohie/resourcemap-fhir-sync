package com.apelon.resourcemap.objects;

import java.util.ArrayList;

public class ResourcemapLayer {

	private int collectionId; // 1666
	private int layerId; // 1670
	private String layerName; // Medical Facility Information
	private int layerOrder; // 2
	private ArrayList<ResourcemapField> fields;
	private ResourcemapField updateField;

	public ResourcemapField getUpdateField() {
		return updateField;
	}

	/**
	 * Sets this layers field to be updated and also adds to field
	 * List if it does not already contain field.
	 * @param updateField
     */
	public void setUpdateField(ResourcemapField updateField) {
		this.updateField = updateField;
//		if (!this.fields.contains(this.updateField)) {
//			this.fields.add(this.updateField);
//		}
	}

	public void addField(ResourcemapField field) {
		this.fields.add(field);
	}

	public ArrayList<ResourcemapField> getFields() {
		return fields;
	}

	public void setFields(ArrayList<ResourcemapField> fields) {
		this.fields = fields;
	}

	public int getLayerId() {
		return layerId;
	}

	public void setLayerId(int layerId) {
		this.layerId = layerId;
	}

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public int getLayerOrder() {
		return layerOrder;
	}

	public void setLayerOrder(int layerOrder) {
		this.layerOrder = layerOrder;
	}

}
