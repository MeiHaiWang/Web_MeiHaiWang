package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMastercountry;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMastercountryForm extends BaseDbOperationForm<TMastercountry> {

	public TMastercountryForm() {
		
		super(TMastercountry.class);
	}
}
