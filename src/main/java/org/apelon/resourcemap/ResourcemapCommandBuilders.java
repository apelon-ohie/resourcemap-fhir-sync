package org.apelon.resourcemap;

import java.util.List;

import org.apelon.fhir.FhirData;
import org.apelon.resourcemap.objects.ResourcemapField;
import org.hl7.fhir.ValueSetContains;

public class ResourcemapCommandBuilders {

	public StringBuffer sb;
	
	private void addCommandParam(String command) {
		sb.append(command);
	}
	
	
	public String buildUpdateLayerPayload(ResourcemapField config) {
		sb = new StringBuffer();
		addCommandParam("{");
			addCommandParam("\"layer\":{");
				addCommandParam("\"id\":\"" + config.getLayerId() + "\",");
				addCommandParam("\"name\":\"" + config.getLayerName() + "\",");
				addCommandParam("\"ord\":\"" + config.getLayerOrder() + "\",");
				addCommandParam("\"fields_attributes\":{");
					addCommandParam("\"0\":{ ");
						addCommandParam("\"id\":\"" + config.getFieldId() + "\",");
						addCommandParam("\"name\":\"Facility Type\",");
						addCommandParam("\"code\":\"facility_type\",");
						addCommandParam("\"kind\":\"select_one\",");
						addCommandParam("\"ord\":\"" + config.getFieldOrder() + "\","); 
						addCommandParam("\"layer_id\":\"" + config.getLayerId() + "\",");
						addCommandParam("\"config\":{");
							addCommandParam("\"options\":[");
								int NEXT_ID = config.getNextId(); //NEXT_ID 
								int count = 0;
								List<ValueSetContains> facilitiesData = FhirData.getFhirFacilities(config.getValuesetName());
								for(ValueSetContains row : facilitiesData) {
									addCommandParam("{ ");
										addCommandParam("\"id\":\"" + (NEXT_ID) + "\",");
										addCommandParam("\"code\":\"" + row.getCode().getValue() + "\",");
										addCommandParam("\"label\":\"" + (row.getDisplay().getValue()) + "\""); //TODO: JSONObject.escape
									addCommandParam("}");
									if(count != (facilitiesData.size()-1)) {
										addCommandParam(","); 
									}
									count++; NEXT_ID++;
								}
							addCommandParam("]");
						addCommandParam("}");
					addCommandParam("}");
				addCommandParam("}");
			addCommandParam("}");
		addCommandParam("}");
		System.out.println(sb.toString()); //Debug
		return sb.toString();
	}
	
}
