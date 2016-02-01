import com.apelon.fhir.FhirData;
import com.apelon.resourcemap.ResourcemapHandler;
import com.apelon.resourcemap.objects.ResourcemapField;
import java.util.List;

import com.apelon.util.AppLogger;
import org.apache.logging.log4j.Logger;

import org.hl7.fhir.ValueSetContains;
import org.junit.Test;

public class FhirValuesetDataTest {

    public static final Logger logger = AppLogger.get();

    /**
     * Test the FHIR Value-sets return data; and also verify
     * the returned data is correct
     */
    @Test
    public void main() {
        // VALUE-SETS
        //String valueset = "valueset-c80-facilitycodes"; //Facility Types Types
        String valueset = "RegionsOfTanzania"; //Tanzania Regions

        // Get FHIR Data
        List<ValueSetContains> facilitiesData = FhirData.getFhirFacilities(valueset);

        logger.info("FHIR Valueset values '" + valueset + "'");
        for(ValueSetContains row : facilitiesData) {
            logger.info("FHIR Code: " + row.getCode().getValue());
            logger.info("FHIR Value: " + row.getDisplay().getValue());
        }
    }

}
