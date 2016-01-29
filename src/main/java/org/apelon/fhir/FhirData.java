package org.apelon.fhir;

import java.util.List;

import org.apelon.config.Config;
import org.hl7.fhir.ValueSet;
import org.hl7.fhir.ValueSetContains;

import com.apelon.demo.dts.fhir.simpleclient.util.http.HttpMessageSender;
import com.apelon.dts.fhir.translator.valueset.xml.FhirXmlTranslator;

public class FhirData {

	public static List<ValueSetContains> getFhirFacilities(String valueSetName) {
		try {
			String uid = Config.getFhirUser();
			String pwd = Config.getFhirPassword();
			String url = Config.getFhirServer();
			url += "/ValueSet/" + valueSetName + "/$expand";
			HttpMessageSender client = new HttpMessageSender(uid, pwd);
			String valueSetXml = client.get(url);
			ValueSet valueSet = FhirXmlTranslator.getInstance().getValueSetFromXml(valueSetXml);
			List<ValueSetContains> contains = valueSet.getExpansion().getContains();
			return contains;
     } catch (Exception exp) {
            throw new RuntimeException(exp);
     }
	}
}
