package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TClaim;

@Data
@EqualsAndHashCode(callSuper=false)
public class TClaimForm extends BaseDbOperationForm<TClaim> {

	public TClaimForm() {
		
		super(TClaim.class);
	}
}
