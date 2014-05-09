package com.sampas.akos.common.interceptor;

import org.aspectj.lang.JoinPoint;

public class TraceLogAspect{

	public Object before(JoinPoint call) throws Throwable{

//    	log = Logger.getLogger(call.getTarget().getClass());
//		log.error("AUDIT LOG: Begining method: "+call.toShortString());  
		return null;
	}

	public Object after(JoinPoint call) throws Throwable{
//    	log = Logger.getLogger(call.getTarget().getClass());
//		log.error("AUDIT LOG: Ending method: "+call.toShortString());    
        return null;
    }

}
