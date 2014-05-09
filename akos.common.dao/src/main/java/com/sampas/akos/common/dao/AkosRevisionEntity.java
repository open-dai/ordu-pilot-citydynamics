package com.sampas.akos.common.dao;



import java.util.Date;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.sampas.akos.common.listener.AkosRevisionListener;

@RevisionEntity(AkosRevisionListener.class)
public class AkosRevisionEntity extends DefaultRevisionEntity {
	private String kaydeden;
	private Date kayitTarih;

	public String getKaydeden() {
		return kaydeden;
	}

	public void setKaydeden(String kaydeden) {
		this.kaydeden = kaydeden;
	}

	public Date getKayitTarih() {
		return kayitTarih;
	}

	public void setKayitTarih(Date kayitTarih) {
		this.kayitTarih = kayitTarih;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public long getTimestamp() {
		// TODO Auto-generated method stub
		return super.getTimestamp();
	}

	@Override
	public void setTimestamp(long timestamp) {
		// TODO Auto-generated method stub
		super.setTimestamp(timestamp);
	}
}
