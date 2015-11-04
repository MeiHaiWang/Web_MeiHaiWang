package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMasternews;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMasternewsForm extends BaseDbOperationForm<TMasternews> {

	public TMasternewsForm() {
		
		super(TMasternews.class);
	}
}
