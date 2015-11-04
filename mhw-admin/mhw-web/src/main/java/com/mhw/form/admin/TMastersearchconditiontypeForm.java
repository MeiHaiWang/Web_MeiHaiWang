package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMastersearchconditiontype;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMastersearchconditiontypeForm extends BaseDbOperationForm<TMastersearchconditiontype> {

	public TMastersearchconditiontypeForm() {
		
		super(TMastersearchconditiontype.class);
	}
}
