package com.apelon.resourcemap;

import com.apelon.fhir.objects.FhirValue;
import com.apelon.resourcemap.objects.ResourcemapField;
import com.apelon.resourcemap.objects.ResourcemapLayer;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;

public class ResourcemapCommandBuilders {

	public String buildNewSitePayload(String name, double lat, double lng) {
		sb = new StringBuffer();

//		sb.append("Content-Disposition: form-data; name=\"site\" ");
//		sb.append("{\"name\":\"New Site\",\"lat\":\"-12.83425399397077\",\"lng\":\"30.15640980316266\"}");
		String json = "site={\"name\":\"" + name + "\",\"lat\":" + lat + ",\"lng\":" + lng + "} ";

		return json;
	}


	public StringBuffer sb;
	private void addCommandParam(String command) {
		sb.append(command);
	}
	public String buildUpdateLayerPayload(ResourcemapLayer layer, List<FhirValue> values) {
		ResourcemapField field = layer.getUpdateField();
		String code = "";
		String label = "";
		sb = new StringBuffer();

		addCommandParam("{");
			addCommandParam("\"layer\":{");
				addCommandParam("\"id\":\"" + layer.getLayerId() + "\",");
				addCommandParam("\"name\":\"" + layer.getLayerName() + "\",");
				addCommandParam("\"ord\":\"" + layer.getLayerOrder() + "\",");
				addCommandParam("\"fields_attributes\":{");
					addCommandParam("\"0\":{ ");
						addCommandParam("\"id\":\"" + field.getFieldId() + "\",");
						addCommandParam("\"name\":\"" + field.getFieldName() + "\",");
						addCommandParam("\"code\":\"" + field.getFieldCode() + "\",");
						addCommandParam("\"kind\":\"select_one\",");
						addCommandParam("\"ord\":\"" + field.getFieldOrder() + "\",");
						addCommandParam("\"layer_id\":\"" + layer.getLayerId() + "\",");
						addCommandParam("\"config\":{");
							addCommandParam("\"options\":[");
								int NEXT_ID = field.getNextId(); //NEXT_ID
								int count = 0;

								for(FhirValue v : values) { //TESTING
									addCommandParam("{ ");
										addCommandParam("\"id\":\"" + (NEXT_ID) + "\",");
										addCommandParam("\"code\":\"" + v.getCode() + "\",");
										addCommandParam("\"label\":\"" + v.getValue() + "\""); //TODO: JSONObject.escape
									addCommandParam("}");
									if(count != (values.size()-1)) {
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
