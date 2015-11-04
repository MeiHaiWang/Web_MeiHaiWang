package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMastermenucategory;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMastermenucategoryForm extends BaseDbOperationForm<TMastermenucategory> {

	public TMastermenucategoryForm() {
		
		super(TMastermenucategory.class);
	}
}
