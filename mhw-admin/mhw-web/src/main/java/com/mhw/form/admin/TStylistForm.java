package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TStylist;

@Data
@EqualsAndHashCode(callSuper=false)
public class TStylistForm extends BaseDbOperationForm<TStylist> {

	public TStylistForm() {
		
		super(TStylist.class);
	}
}
