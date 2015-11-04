package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TImage;

@Data
@EqualsAndHashCode(callSuper=false)
public class TImageForm extends BaseDbOperationForm<TImage> {

	public TImageForm() {
		
		super(TImage.class);
	}
}
