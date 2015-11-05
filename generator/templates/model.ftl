<#assign classNamePrefix = LOWER_UNDERSCORE.to(UPPER_CAMEL, tableName) />
package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class ${classNamePrefix}Info extends BaseInfo implements IBaseInfo {
	
	
	<#list fields as field>
	<#assign fieldName = LOWER_UNDERSCORE.to(LOWER_CAMEL, LOWER_CAMEL.to(LOWER_UNDERSCORE, field.name)) />
	<#assign fieldNameCamel = StringUtils.capitalise(fieldName) />
	<#if field.type == 'INT UNSIGNED' || field.type == 'INT'>
	private int ${fieldName} = 0;

	public int get${fieldNameCamel}() {
		return ${fieldName};
	}
	
	public void set${fieldNameCamel} (int ${fieldName}) {
		this.${fieldName} = ${fieldName};
	}

	<#elseif field.type == 'VARCHAR'>
	private String ${fieldName} = "";

	public String get${fieldNameCamel}() {
		return ${fieldName};
	}
	
	public void set${fieldNameCamel} (String ${fieldName}) {
		this.${fieldName} = ${fieldName};
	}

	<#elseif field.type == 'DOUBLE' || field.type == 'DOUBLE UNSIGNED'>
	private Double ${fieldName} = 0d;

	public Double get${fieldNameCamel}() {
		return ${fieldName};
	}
	
	public void set${fieldNameCamel} (Double ${fieldName}) {
		this.${fieldName} = ${fieldName};
	}

	<#elseif field.type == 'DATETIME'>
	private Date ${fieldName} = new Date(0);

	public Date get${fieldNameCamel}() {
		return ${fieldName};
	}
	
	public void set${fieldNameCamel} (Date ${fieldName}) {
		this.${fieldName} = ${fieldName};
	}

	<#else>
	error exists. ${field.type} type is not supported...
	</#if>
	</#list>
}
