<#assign className = LOWER_UNDERSCORE.to(UPPER_CAMEL, tableName) />
package com.mhw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "${tableName}")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ${className} extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	<#list fields as field>
	<#if !['create_at', 'update_at', 'deleted']?seq_contains(field.name)>
	<#assign fieldName = field.name />
	<#if field.type == 'INT UNSIGNED' || field.type == 'INT'>
	<#if field_index == 0>
	@Id
	@GeneratedValue
	@Column(name = "${field.name}")
	private Long id;

	<#else>
	@Column(name = "${field.name}")
	private Long ${fieldName};

	</#if>
	<#elseif field.type == 'DOUBLE UNSIGNED' || field.type == 'DOUBLE'>
	@Column(name = "${field.name}")
	private Double ${fieldName};

	<#elseif field.type == 'VARCHAR' || field.type == 'TEXT'>
	@Column(name = "${field.name}")
	private String ${fieldName};

	<#elseif field.type == 'DATETIME'>
	@Column(name = "${field.name}")
	private DateTime ${fieldName};

	<#elseif field.type == 'BIT' || field.type == 'TINYINT'>
	@Column(name = "${field.name}")
	private boolean ${fieldName};

	</#if>
	</#if>
	</#list>
}
