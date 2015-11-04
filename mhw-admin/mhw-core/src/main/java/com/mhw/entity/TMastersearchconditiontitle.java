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
@Table(name = "t_masterSearchConditionTitle")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMastersearchconditiontitle extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_masterSearchConditionTitle_id")
	private Long id;

	@Column(name = "t_masterSearchConditionTitle_name")
	private String t_masterSearchConditionTitle_name;

	@Column(name = "t_masterSearchConditionTitle_typeId")
	private Long t_masterSearchConditionTitle_typeId;

}
