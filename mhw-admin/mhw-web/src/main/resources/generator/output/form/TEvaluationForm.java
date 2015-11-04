package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TEvaluation;

@Data
@EqualsAndHashCode(callSuper=false)
public class TEvaluationForm extends BaseDbOperationForm<TEvaluation> {

	public TEvaluationForm() {
		
		super(TEvaluation.class);
	}
}
