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
@Table(name = "t_masterSearchCondition")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMastersearchcondition extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_masterSearchCondition_id")
	private Long id;

	@Column(name = "t_masterSearchCondition_name")
	private String t_masterSearchCondition_name;

	@Column(name = "t_masterSearchCondition_titleId")
	private Long t_masterSearchCondition_titleId;

}
