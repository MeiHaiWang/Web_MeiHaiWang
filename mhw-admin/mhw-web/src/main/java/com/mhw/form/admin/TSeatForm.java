package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TSeat;

@Data
@EqualsAndHashCode(callSuper=false)
public class TSeatForm extends BaseDbOperationForm<TSeat> {

	public TSeatForm() {
		
		super(TSeat.class);
	}
}
