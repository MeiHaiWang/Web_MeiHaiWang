package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TMenu;

@Data
@EqualsAndHashCode(callSuper=false)
public class TMenuForm extends BaseDbOperationForm<TMenu> {

	public TMenuForm() {
		
		super(TMenu.class);
	}
}
