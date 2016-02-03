package resourcemap;

import com.apelon.config.Config;
import com.apelon.fhir.FhirData;
import com.apelon.fhir.objects.FhirValue;
import com.apelon.http.HttpMessageSender;
import com.apelon.resourcemap.ResourcemapCommandBuilders;
import com.apelon.resourcemap.objects.ResourcemapField;
import com.apelon.resourcemap.objects.ResourcemapLayer;
import com.apelon.util.TestLogger;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class update_facility_type_layer_test {
	public static final Logger logger = TestLogger.get();

	@Test
	public void main() {
		ResourcemapLayer layer = new ResourcemapLayer();
		layer.setCollectionId(Config.getResourcemapCollectionTanzania());
		layer.setLayerId(1817);
		layer.setLayerName("Facility TYpe"); //Medical Facility Information
		layer.setLayerOrder(2);

		ResourcemapField field = new ResourcemapField();
		field.setFieldCode("facility_type");
		field.setFieldName("Facility Type");
		field.setFieldOrder(1);
		field.setFieldId(14414);
		field.setValuesetName(Config.getValuesetSnomedFacilityTypes());
		field.setNextId(3);
		layer.setUpdateField(field);

		//FHIR Valueset or TEST Valueset
		List<FhirValue> fhirData = FhirData.getValueset(Config.getValuesetSnomedFacilityTypes()); //FHIR
//		List<FhirValue> fhirData = FhirData.getTestValueset(10); //TEST

		//Build JSON Command
		ResourcemapCommandBuilders rmcb = new ResourcemapCommandBuilders();
		String command = rmcb.buildUpdateLayerPayload(layer, fhirData);

		//Get Resource Map API Endpoint
		String endpoint = Config.getResourcemapApiCollectionsUpdateUrl(layer.getCollectionId(), layer.getLayerId());

		//Execute Command
		boolean status = HttpMessageSender.executePut(endpoint, command);

		assertTrue(status);
	}
	
}
