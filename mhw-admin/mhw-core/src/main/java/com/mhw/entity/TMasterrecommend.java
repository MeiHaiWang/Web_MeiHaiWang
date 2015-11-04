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
@Table(name = "t_masterRecommend")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMasterrecommend extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_masterRecommend_Id")
	private Long id;

	@Column(name = "t_masterRecommend_salonId")
	private Long t_masterRecommend_salonId;

	@Column(name = "t_masterRecommend_hairStyleId")
	private Long t_masterRecommend_hairStyleId;

	@Column(name = "t_masterRecommend_updateDate")
	private DateTime t_masterRecommend_updateDate;

	@Column(name = "t_masterRecommend_stylistId")
	private Long t_masterRecommend_stylistId;

}
