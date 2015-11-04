package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMastersearchconditiontitle;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMastersearchconditiontitleForm extends BaseDbOperationForm<TMastersearchconditiontitle> {

	public TMastersearchconditiontitleForm() {
		
		super(TMastersearchconditiontitle.class);
	}
}
