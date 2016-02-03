package fhir.valuesets;

import com.apelon.fhir.FhirData;
import com.apelon.util.AppLogger;
import com.apelon.util.TestLogger;
import org.apache.logging.log4j.Logger;
import org.hl7.fhir.ValueSetContains;
import org.junit.Test;

import java.util.List;

public class valueset_snomed_facility_types_test {

    public static final Logger logger = TestLogger.get();

    /**
     * Test the FHIR Value-sets return data; and also verify
     * the returned data is correct
     */
    @Test
    public void main() {
        // VALUE-SETS
        String valueset = "valueset-c80-facilitycodes"; //Facility Types Types

        // Get FHIR Data
        List<ValueSetContains> facilitiesData = FhirData.getFhirFacilities(valueset);

        logger.info("FHIR Valueset values '" + valueset + "'");
        for(ValueSetContains row : facilitiesData) {

            logger.info("FHIR Code: " + row.getCode().getValue() + " + " + row.getDisplay().getValue());
        }
    }

}
