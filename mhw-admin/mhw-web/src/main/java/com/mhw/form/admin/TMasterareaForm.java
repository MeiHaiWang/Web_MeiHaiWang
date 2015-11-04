package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMasterarea;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMasterareaForm extends BaseDbOperationForm<TMasterarea> {

	public TMasterareaForm() {
		
		super(TMasterarea.class);
	}
}
