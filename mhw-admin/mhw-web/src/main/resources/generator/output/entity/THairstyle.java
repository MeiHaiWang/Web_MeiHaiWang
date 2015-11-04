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
@Table(name = "t_hairStyle")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class THairstyle extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_hairStyle_id")
	private Long id;

	@Column(name = "t_hairStyle_name")
	private String t_hairStyle_name;

	@Column(name = "t_hairStyle_hairTypeId")
	private Long t_hairStyle_hairTypeId;

	@Column(name = "t_hairStyle_goodNumber")
	private Long t_hairStyle_goodNumber;

	@Column(name = "t_hairStyle_viewNumber")
	private Long t_hairStyle_viewNumber;

	@Column(name = "t_hairStyle_stylistId")
	private Long t_hairStyle_stylistId;

	@Column(name = "t_hairStyle_areaId")
	private String t_hairStyle_areaId;

	@Column(name = "t_hairStyle_imagePath")
	private String t_hairStyle_imagePath;

	@Column(name = "t_hairStyle_salonId")
	private Long t_hairStyle_salonId;

	@Column(name = "t_hairStyle_updateDate")
	private DateTime t_hairStyle_updateDate;

	@Column(name = "t_hairStyle_favoriteNumber")
	private Long t_hairStyle_favoriteNumber;

	@Column(name = "t_hairStyle_searchConditionId")
	private String t_hairStyle_searchConditionId;

	@Column(name = "t_hairStyle_message")
	private String t_hairStyle_message;

}
