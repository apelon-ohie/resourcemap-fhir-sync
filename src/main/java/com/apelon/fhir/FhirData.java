package com.apelon.fhir;

import com.apelon.config.Config;
import com.apelon.demo.dts.fhir.simpleclient.util.http.HttpMessageSender;
import com.apelon.dts.fhir.translator.valueset.xml.FhirXmlTranslator;
import com.apelon.util.AppLogger;
import org.apache.logging.log4j.Logger;
import org.hl7.fhir.ValueSet;
import org.hl7.fhir.ValueSetContains;

import java.util.List;

public class FhirData {

	public static Logger logger = AppLogger.get();

	public static List<ValueSetContains> getFhirFacilities(String valueSetName) {
		try {
			String uid = Config.getFhirUser();
			String pwd = Config.getFhirPassword();
			String url = Config.getFhirServer();
			url += "/ValueSet/" + valueSetName + "/$expand";

			logger.info(uid + ":" + pwd);
			logger.info(url);

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
