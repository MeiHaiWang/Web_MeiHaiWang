package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TReview;

@Data
@EqualsAndHashCode(callSuper=false)
public class TReviewForm extends BaseDbOperationForm<TReview> {

	public TReviewForm() {
		
		super(TReview.class);
	}
}
