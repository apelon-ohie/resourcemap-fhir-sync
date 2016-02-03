package resourcemap;

import com.apelon.config.Config;
import com.apelon.config.ResourceMapData;
import com.apelon.fhir.FhirData;
import com.apelon.fhir.objects.FhirValue;
import com.apelon.http.HttpMessageSender;
import com.apelon.resourcemap.ResourcemapCommandBuilders;
import com.apelon.resourcemap.objects.ResourcemapField;
import com.apelon.resourcemap.objects.ResourcemapLayer;
import com.apelon.util.TestLogger;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class new_facility_test {
	public static final Logger logger = TestLogger.get();

	@Test
	public void main() {
		// http://resourcemap.instedd.org/api/collections/1709/layers.json
		ResourcemapLayer layer = new ResourcemapLayer();
		layer.setCollectionId(Config.getResourcemapCollectionTanzania());
		layer.setLayerId(1816);
		layer.setLayerName("Geography"); //Medical Facility Information
		layer.setLayerOrder(1);

		ResourcemapField field = new ResourcemapField();
		field.setFieldCode("regions");
		field.setFieldName("regions");
		field.setFieldOrder(1);
		field.setFieldId(14413);
		field.setValuesetName(Config.getValuesetSnomedFacilityTypes());
		field.setNextId(2);
		layer.setUpdateField(field);

		//Dummy Data or FHIR Data
//		List<FhirValue> fhirData = FhirData.getValueset(Config.getValuesetSnomedFacilityTypes());
		List<FhirValue> fhirData = FhirData.getTestValueset(10);

		String endpoint = Config.getResourcemapApiCreateSiteUrl(1709);

		logger.debug(endpoint);

		ResourcemapCommandBuilders rmcb = new ResourcemapCommandBuilders();
		String command = rmcb.buildNewSitePayload("VasNewSite", -4.193853, 35.025691);
		boolean result = HttpMessageSender.createResourcemapSite(endpoint, command);

		assertTrue(result);

//		String command = "";
//		for(FhirValue fv : fhirData) {
//			command = rmcb.buildNewSitePayload(fv.getValue(), -4.193853, 35.025691);
//			result = HttpMessageSender.createResourcemapSite(endpoint, command);
//		}

		logger.debug(command);
	}

}
