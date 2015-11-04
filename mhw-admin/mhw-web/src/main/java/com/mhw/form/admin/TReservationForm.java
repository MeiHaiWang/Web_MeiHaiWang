package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TReservation;

@Data
@EqualsAndHashCode(callSuper=false)
public class TReservationForm extends BaseDbOperationForm<TReservation> {

	public TReservationForm() {
		
		super(TReservation.class);
	}
}
