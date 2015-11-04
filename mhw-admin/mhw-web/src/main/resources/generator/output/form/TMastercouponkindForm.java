package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMastercouponkind;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMastercouponkindForm extends BaseDbOperationForm<TMastercouponkind> {

	public TMastercouponkindForm() {
		
		super(TMastercouponkind.class);
	}
}
