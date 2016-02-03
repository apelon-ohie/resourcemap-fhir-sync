package com.apelon.resourcemap;

import com.apelon.config.Config;
import com.apelon.fhir.FhirData;
import com.apelon.fhir.objects.FhirValue;
import com.apelon.http.HttpMessageSender;
import com.apelon.resourcemap.objects.ResourcemapField;
import com.apelon.resourcemap.objects.ResourcemapLayer;

import javax.annotation.Resource;
import java.util.List;

public class ResourcemapHandler {
	
	private ResourcemapCommandBuilders rmcb;

	public void createFhirLocations(String collection_id, String valueset) {
		
		String endpoint = Config.getFhirServer() + "/api/applications/" + collection_id + "/sites.json";
		
	}

	public void createFacility(int collection, String valueset) {
		//FHIR Valueset
		List<FhirValue> fhirData = FhirData.getValueset(valueset);

		String endpoint = Config.getResourcemapApiCreateSiteUrl(collection);

		String command = "";
		for(FhirValue fv : fhirData) {
			command = rmcb.buildNewSitePayload(fv.getValue(), -4.193853, 35.025691);
			HttpMessageSender.createResourcemapSite(endpoint, command);
		}
	}
	
	public void updateFacilityLayerType(ResourcemapLayer layer, String valueset) {
		ResourcemapField field = layer.getUpdateField();

		//FHIR Valueset
		List<FhirValue> fhirData = FhirData.getValueset(valueset);

		//Build JSON Command
		String command = rmcb.buildUpdateLayerPayload(layer, fhirData);

		//Get Resource Map API Endpoint
		String endpoint = Config.getResourcemapApiCollectionsUpdateUrl(layer.getCollectionId(), layer.getLayerId());

		//Execute Command
		HttpMessageSender.executePut(endpoint, command);
	}

}
