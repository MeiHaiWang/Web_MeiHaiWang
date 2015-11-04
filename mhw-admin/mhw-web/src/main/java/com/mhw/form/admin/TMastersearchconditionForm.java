package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMastersearchcondition;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMastersearchconditionForm extends BaseDbOperationForm<TMastersearchcondition> {

	public TMastersearchconditionForm() {
		
		super(TMastersearchcondition.class);
	}
}
