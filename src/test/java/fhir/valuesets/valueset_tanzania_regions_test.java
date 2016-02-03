package fhir.valuesets;

import com.apelon.fhir.FhirData;
import com.apelon.resourcemap.ResourcemapHandler;
import com.apelon.resourcemap.objects.ResourcemapField;
import java.util.List;

import com.apelon.util.AppLogger;
import com.apelon.util.TestLogger;
import org.apache.logging.log4j.Logger;

import org.hl7.fhir.ValueSetContains;
import org.junit.Test;

public class valueset_tanzania_regions_test {

    public static final Logger logger = TestLogger.get();

    /**
     * Test the FHIR Value-sets return data; and also verify
     * the returned data is correct
     */
    @Test
    public void main() {
        // VALUE-SETS
        String valueset = "RegionsOfTanzania"; //Tanzania Regions

        // Get FHIR Data
        List<ValueSetContains> facilitiesData = FhirData.getFhirFacilities(valueset);

        logger.info("FHIR Valueset values '" + valueset + "'");
        for(ValueSetContains row : facilitiesData) {
            logger.info("FHIR Code: " + row.getCode().getValue());
            logger.info("FHIR Value: " + row.getDisplay().getValue());
        }
        //TODO: Add every code + name to stringbuffer
        //TODO: assertTrue() if this is equal to the saved file in resources
    }

}
