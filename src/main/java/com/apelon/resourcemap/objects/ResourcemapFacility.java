package com.apelon.resourcemap.objects;

public class ResourcemapFacility {

    private String facilityName;
    private double facilityLat;
    private double facilityLong;
    private String facilityAddress;

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public double getFacilityLat() {
        return facilityLat;
    }

    public void setFacilityLat(double facilityLat) {
        this.facilityLat = facilityLat;
    }

    public double getFacilityLong() {
        return facilityLong;
    }

    public void setFacilityLong(double facilityLong) {
        this.facilityLong = facilityLong;
    }

    public String getFacilityAddress() {
        return facilityAddress;
    }

    public void setFacilityAddress(String facilityAddress) {
        this.facilityAddress = facilityAddress;
    }

    public long getFacilityFhirId() {
        return facilityFhirId;
    }

    public void setFacilityFhirId(long facilityFhirId) {
        this.facilityFhirId = facilityFhirId;
    }

    private long facilityFhirId;


}
