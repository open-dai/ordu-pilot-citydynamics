package com.sampas.ortak.spatial.transaction.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.ortak.spatial.transaction.model.BaseTransactionObject;


public class TransactionDAOOld extends BaseDaoSupport implements ITransactionDAOOld {

	@SuppressWarnings("unchecked")
	public List<BaseTransactionObject> readObjectTransactions(ITransactionable transactionableObject, Long processId, Long taskId, Long id, String processUser, Date transactionDateBegin,Date transactionDateEnd,String status) {
		
		List<BaseTransactionObject> toReturn = null;
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(transactionableObject.getId());
		
		String objectClassName = transactionableObject.getClass().getSimpleName();
		
		String transactionObjectClassName = objectClassName + "Trs";
		
		String hql = "Select trs from " + transactionObjectClassName + " as trs where trs.id>0";
		
		if (transactionableObject != null && transactionableObject.getId()!= null && transactionableObject.getId() > 0) {
			
			hql += " AND trs.nesneId=?";
			
			params.add(transactionableObject.getId());
		}
		if (processUser != null && !processUser.trim().equals("")) {
			
			hql += " AND trs.islemiYapan=?";
			
			params.add(processUser);
		}
		if (taskId != null && taskId.longValue() > 0) {
			
			hql += " AND trs.taskId=?";
			
			params.add(taskId);
		}
		if (processId != null && processId.longValue() > 0) {
			
			hql += " AND trs.surecId=?";
			
			params.add(processId);
		}
		if (transactionDateBegin != null) {
			
			hql += " AND trs.islemTarihi>=?";
			
			params.add(transactionDateBegin);
		}
		if (transactionDateEnd != null) {
			
			hql += " AND trs.islemTarihi<=?";
			
			params.add(transactionDateEnd);
		}
		if (status != null && !status.trim().equals("")) {
			
			hql += " AND trs.onayDurumu<=?";
			
			params.add(status);
		}
		if (id != null && id > 0){
			
			hql += " AND trs.id=?";
			
			params.add(id);
		}
		try {
		
			toReturn = (List<BaseTransactionObject>)super.readDBObjectByHQL(hql,params.toArray());
		} catch (Exception e) {
		
			toReturn = null;
			
			e.printStackTrace();
		}
		return toReturn;
	}
	
}
