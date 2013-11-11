package helpers;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import data_utils.SoapParameter;


public class EnvelopeBuilder {

	public SoapSerializationEnvelope create(String namespace, String method,
			SoapParameter[] params) {
		SoapSerializationEnvelope envelope = null;

		try {
			SoapObject request = new SoapObject(namespace, method);

			if (params == null) {

			} else {
				for (SoapParameter param : params) {
					request.addProperty(param.getKey(), param.getValue());
				}
			}

			envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
		} catch (Exception exc) {
			envelope = null;
		}

		return envelope;
	}

}
