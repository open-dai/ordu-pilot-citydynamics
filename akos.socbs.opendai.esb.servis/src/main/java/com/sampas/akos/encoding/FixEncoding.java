package com.sampas.akos.encoding;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.interceptor.Fault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.xml.stream.XMLStreamWriter;
import com.ctc.wstx.sw.SimpleNsStreamWriter;
import java.io.Writer;
import java.io.IOException;


public class FixEncoding extends AbstractPhaseInterceptor<Message> {
	
    protected final Log logger = LogFactory.getLog(getClass());

    public FixEncoding() {
    	
        super(Phase.PRE_PROTOCOL);
    }

    public void handleMessage(Message message) {
    	
        String encoding = getEncoding(message);
        
        logger.debug("======= encoding: " + encoding);
        
        System.err.println("Error occoured !!! ERROR: " + encoding);

        try {
        	
            XMLStreamWriter origWriter = message.getContent(XMLStreamWriter.class);
            
            SimpleNsStreamWriter simpleNSWriter = (SimpleNsStreamWriter)origWriter;
            
            Writer writer = (Writer)simpleNSWriter.getProperty("com.ctc.wstx.outputUnderlyingWriter");
            
            writer.write("<?xml version='1.0' encoding='"+ encoding +"'?>\n");
            
        } catch (IOException ex) {
        	
            throw new Fault(ex);
        }
    }

    private String getEncoding(Message message) {
    	
//		Exchange ex = message.getExchange();
        
        String encoding=null ;//= (String)message.get(Message.ENCODING);
//        if (encoding == null && ex.getInMessage() != null) {
//            encoding = (String) ex.getInMessage().get(Message.ENCODING);
//            message.put(Message.ENCODING, encoding);
//        }
        if (encoding == null) {
        	
            encoding = "UTF-8";
            
            message.put(Message.ENCODING, encoding);
        }
        return encoding;
    }

    public void handleFault(Message messageParam) {
    	
    }
    
}
