package resourcemap;

import com.apelon.config.Config;
import com.apelon.resourcemap.ResourcemapHandler;
import com.apelon.resourcemap.objects.ResourcemapField;
import org.junit.Test;


public class update_facility_type_layer_test {

	@Test
	public void main() {
		ResourcemapHandler rmh = new ResourcemapHandler();
		ResourcemapField rmfl = new ResourcemapField();
		rmfl.setCollectionId(1667);
		rmfl.setLayerId(1669);
		rmfl.setLayerName("Medical Facility Information"); //Medical Facility Information
		rmfl.setLayerOrder(2);
		rmfl.setFieldCode("facility_type");
		rmfl.setFieldName("Facility Type");
		rmfl.setFieldOrder(1);
		rmfl.setFieldId(14371);
		rmfl.setValuesetName(Config.getValuesetSnomedFacilityTypes());
		rmfl.setNextId(3);

		rmh.updateFacilityLayerType(rmfl);
	}
	
}
