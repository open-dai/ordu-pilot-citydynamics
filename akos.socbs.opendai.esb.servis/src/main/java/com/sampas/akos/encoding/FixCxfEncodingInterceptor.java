package com.sampas.akos.encoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.helpers.HttpHeaderHelper;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.MessageSenderInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


public class FixCxfEncodingInterceptor extends AbstractPhaseInterceptor<Message>
{

	public FixCxfEncodingInterceptor() {
	  
	    super(Phase.PREPARE_SEND);
	    
	    addBefore(MessageSenderInterceptor.class.getName());
	  }

	@SuppressWarnings("unchecked")
	public void handleMessage(Message message) throws Fault {
	  
	    Map<String, List<String>> headers = CastUtils.cast((Map) message.get(Message.PROTOCOL_HEADERS));
	    
	    if (headers == null) {
	    	
	    	headers = new HashMap<String, List<String>>();
	  
	    	message.put(Message.PROTOCOL_HEADERS, headers);
	    }
	    //String ct = (String) message.getExchange().get(Endpoint.class).get(HttpHeaderHelper.CONTENT_TYPE);
	    String ct = "text/xml;charset=UTF-8;";
	
	    if (ct != null) {
	    	
			List<String> contentType = new ArrayList<String>();
			  
			contentType.add(ct);
			  
			headers.put(HttpHeaderHelper.getHeaderKey(HttpHeaderHelper.CONTENT_TYPE), contentType);
			  
			message.put(HttpHeaderHelper.CONTENT_TYPE, ct);
	    }
	}
}
