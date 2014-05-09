package com.sampas.ortak.spatial.transaction.dao;

import java.util.Date;
import java.util.List;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.ortak.spatial.transaction.model.BaseTransactionObject;


public interface ITransactionDAOOld {

	public List<BaseTransactionObject> readObjectTransactions(ITransactionable transactionableObject, Long processId, Long taskId, Long id, String processUser, Date transactionDateBegin, Date transactionDateEnd, String status);
	
}
