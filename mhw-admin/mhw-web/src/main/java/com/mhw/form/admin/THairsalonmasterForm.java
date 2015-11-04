package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.THairsalonmaster;

@Data
@EqualsAndHashCode(callSuper=false)
public class THairsalonmasterForm extends BaseDbOperationForm<THairsalonmaster> {

	public THairsalonmasterForm() {
		
		super(THairsalonmaster.class);
	}
}
