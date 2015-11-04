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
@Table(name = "t_masterArea")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMasterarea extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_area_areaId")
	private Long id;

	@Column(name = "t_area_areaName")
	private String t_area_areaName;

	@Column(name = "t_area_level")
	private Long t_area_level;

	@Column(name = "t_area_countryId")
	private Long t_area_countryId;

	@Column(name = "t_area_isDetailFlag")
	private Long t_area_isDetailFlag;

	@Column(name = "t_area_parentAreaId")
	private Long t_area_parentAreaId;

}
