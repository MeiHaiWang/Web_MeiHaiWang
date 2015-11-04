<#assign className = LOWER_UNDERSCORE.to(UPPER_CAMEL, tableName) />
package com.mhw.form.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mhw.entity.${className};

@Data
@EqualsAndHashCode(callSuper=false)
public class ${className}Form extends BaseDbOperationForm<${className}> {

	public ${className}Form() {
		
		super(${className}.class);
	}
}
