package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.TUser;

@Data
@EqualsAndHashCode(callSuper=false)
public class TUserForm extends BaseDbOperationForm<TUser> {

	public TUserForm() {
		
		super(TUser.class);
	}
}
