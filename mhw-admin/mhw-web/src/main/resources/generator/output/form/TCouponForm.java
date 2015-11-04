package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TCoupon;

@Data
@EqualsAndHashCode(callSuper=false)
public class TCouponForm extends BaseDbOperationForm<TCoupon> {

	public TCouponForm() {
		
		super(TCoupon.class);
	}
}
