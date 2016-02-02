package resourcemap;

import com.apelon.config.Config;
import com.apelon.resourcemap.ResourcemapHandler;
import com.apelon.resourcemap.objects.ResourcemapField;
import org.junit.Test;


public class update_facility_region_layer_test {

	@Test
	public void main() {
		// http://resourcemap.instedd.org/api/collections/1709/layers.json
		ResourcemapHandler rmh = new ResourcemapHandler();
		ResourcemapField rmfl = new ResourcemapField();
		rmfl.setCollectionId(1709);
		rmfl.setLayerId(1816);
		rmfl.setLayerName("geography");
		rmfl.setLayerOrder(2);
		rmfl.setFieldCode("address");
		rmfl.setFieldName("address");
		rmfl.setFieldOrder(2);
		rmfl.setFieldId(14371);
		rmfl.setValuesetName(Config.getValuesetTanzaniaRegions());
		rmfl.setNextId(2);

		rmh.updateFacilityLayerType(rmfl);
	}

}
