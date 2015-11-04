package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TComment;

@Data
@EqualsAndHashCode(callSuper=false)
public class TCommentForm extends BaseDbOperationForm<TComment> {

	public TCommentForm() {
		
		super(TComment.class);
	}
}
