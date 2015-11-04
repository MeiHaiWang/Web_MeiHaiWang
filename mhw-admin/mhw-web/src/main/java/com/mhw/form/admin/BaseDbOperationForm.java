package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.BaseEntity;
import com.mhw.form.BaseForm;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseDbOperationForm<T extends BaseEntity> extends BaseForm {
	
	private T entity;
	
	public BaseDbOperationForm(Class<T> clazz) {
		
		try {
			this.entity = clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
