package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMasterhairtype;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMasterhairtypeForm extends BaseDbOperationForm<TMasterhairtype> {

	public TMasterhairtypeForm() {
		
		super(TMasterhairtype.class);
	}
}
