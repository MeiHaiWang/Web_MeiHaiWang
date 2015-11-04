package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.THairstyle;

@Data
@EqualsAndHashCode(callSuper=false)
public class THairstyleForm extends BaseDbOperationForm<THairstyle> {

	public THairstyleForm() {
		
		super(THairstyle.class);
	}
}
