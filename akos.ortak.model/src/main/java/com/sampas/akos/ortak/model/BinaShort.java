package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;
import java.util.Date;

import org.hibernate.envers.Audited;


@SuppressWarnings("serial")
@Audited
public class BinaShort extends BaseObject implements IVersionable, ITransactionable, IHistoricalable{

	/**
	 * @uml.property  name="id"
	 */
	private Long id;

	/**
	 * Getter of the property <tt>id</tt>
	 * @return  Returns the id.
	 * @uml.property  name="id"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter of the property <tt>id</tt>
	 * @param id  The id to set.
	 * @uml.property  name="id"
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @uml.property  name="binaNo"
	 */
	private Long binaNo;

	/**
	 * Getter of the property <tt>binaNo</tt>
	 * @return  Returns the binaNo.
	 * @uml.property  name="binaNo"
	 */
	public Long getBinaNo() {
		return binaNo;
	}

	/**
	 * Setter of the property <tt>binaNo</tt>
	 * @param binaNo  The binaNo to set.
	 * @uml.property  name="binaNo"
	 */
	public void setBinaNo(Long binaNo) {
		this.binaNo = binaNo;
	}

	
	
	
	
}
