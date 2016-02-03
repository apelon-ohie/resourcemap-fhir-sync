package com.apelon.fhir;

import com.apelon.config.Config;
import com.apelon.dts.fhir.translator.valueset.xml.FhirXmlTranslator;
import com.apelon.fhir.objects.FhirValue;
import com.apelon.http.HttpMessageSender;
import com.apelon.util.AppLogger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.Logger;
import org.hl7.fhir.ValueSet;
import org.hl7.fhir.ValueSetContains;

import java.util.ArrayList;
import java.util.List;

public class FhirData {
	public static Logger logger = AppLogger.get();

	public static List<FhirValue> getValueset(String valueset) {
		String code = "";
		String label = "";
		List<FhirValue> ret = new ArrayList<FhirValue>();

		for(ValueSetContains row : getFhirFacilities(valueset)) {
			code = row.getCode().getValue();
			label = row.getDisplay().getValue();
			FhirValue fv = new FhirValue();
			fv.setCode(code);
			fv.setValue(label);
			ret.add(fv);
		}
		return ret;
	}

	public static List<FhirValue> getTestValueset(int count){
		List<FhirValue> ret = new ArrayList<FhirValue>();
		for(int x = 0; x < count; x++) {
			FhirValue fv = new FhirValue();
			fv.setValue("Value - " + x);
			fv.setCode("Code - " + x);
			ret.add(fv);
		}
		return ret;
	}

	public static List<ValueSetContains> getFhirFacilities(String valueSetName) {
		try {
			String uid = Config.getFhirUser();
			String pwd = Config.getFhirPassword();
			String url = Config.getFhirServer();
			url += "/ValueSet/" + valueSetName + "/$expand";

			//logger.info(uid + ":" + pwd);
			logger.info(url);

			String valueSetXml = HttpMessageSender.get(url);

			valueSetXml = valueSetXml.replace("<ns2:div/>", "");

			logger.debug(valueSetXml);
			ValueSet valueSet = FhirXmlTranslator.getInstance().getValueSetFromXml(valueSetXml);
			List<ValueSetContains> contains = valueSet.getExpansion().getContains();
			return contains;
     } catch (Exception exp) {
            throw new RuntimeException(exp);
     }
	}
}
