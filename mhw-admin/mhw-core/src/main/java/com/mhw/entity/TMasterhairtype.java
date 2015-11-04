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
@Table(name = "t_masterHairType")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMasterhairtype extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_hairType_id")
	private Long id;

	@Column(name = "t_hairType_name")
	private String t_hairType_name;

	@Column(name = "t_hairType_sex")
	private String t_hairType_sex;

	@Column(name = "t_hairType_ImagePath")
	private String t_hairType_ImagePath;

	@Column(name = "t_hairType_searchConditionId")
	private Long t_hairType_searchConditionId;

}
