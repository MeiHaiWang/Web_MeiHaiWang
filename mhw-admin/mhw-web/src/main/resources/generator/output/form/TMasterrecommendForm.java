package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMasterrecommend;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMasterrecommendForm extends BaseDbOperationForm<TMasterrecommend> {

	public TMasterrecommendForm() {
		
		super(TMasterrecommend.class);
	}
}
