package com.sampas.akos.common.listener;

import java.util.Date;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.context.SecurityContextHolder;

import com.sampas.akos.common.dao.AkosRevisionEntity;

public class AkosRevisionListener implements RevisionListener {

	public void newRevision(Object arg0) {
		AkosRevisionEntity entity=(AkosRevisionEntity)arg0;
		entity.setKaydeden(SecurityContextHolder.getContext().getAuthentication()
				.getName());
		entity.setKayitTarih(new Date());
		
	}
	

}
