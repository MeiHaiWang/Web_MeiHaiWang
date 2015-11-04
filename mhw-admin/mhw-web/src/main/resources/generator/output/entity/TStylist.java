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
@Table(name = "t_stylist")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TStylist extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_stylist_Id")
	private Long id;

	@Column(name = "t_stylist_name")
	private String t_stylist_name;

	@Column(name = "t_stylist_sex")
	private Long t_stylist_sex;

	@Column(name = "t_stylist_detailText")
	private String t_stylist_detailText;

	@Column(name = "t_stylist_userId")
	private Long t_stylist_userId;

	@Column(name = "t_stylist_imagePath")
	private String t_stylist_imagePath;

	@Column(name = "t_stylist_position")
	private String t_stylist_position;

	@Column(name = "t_stylist_message")
	private String t_stylist_message;

	@Column(name = "t_stylist_experienceYear")
	private Long t_stylist_experienceYear;

	@Column(name = "t_stylist_specialMenu")
	private String t_stylist_specialMenu;

	@Column(name = "t_stylist_goodNumber")
	private Long t_stylist_goodNumber;

	@Column(name = "t_stylist_viewNumber")
	private Long t_stylist_viewNumber;

	@Column(name = "t_stylist_mail")
	private String t_stylist_mail;

	@Column(name = "t_stylist_phoneNumber")
	private String t_stylist_phoneNumber;

	@Column(name = "t_stylist_birth")
	private DateTime t_stylist_birth;

	@Column(name = "t_stylist_menuId")
	private String t_stylist_menuId;

	@Column(name = "t_stylist_hairStyleId")
	private String t_stylist_hairStyleId;

	@Column(name = "t_stylist_salonId")
	private Long t_stylist_salonId;

	@Column(name = "t_stylist_favoriteNumber")
	private Long t_stylist_favoriteNumber;

	@Column(name = "t_stylist_isNetReservation")
	private Long t_stylist_isNetReservation;

	@Column(name = "t_stylist_searchConditionId")
	private String t_stylist_searchConditionId;

	@Column(name = "t_stylist_areaId")
	private String t_stylist_areaId;

	@Column(name = "t_stylist_restday")
	private String t_stylist_restday;

	@Column(name = "t_stylist_resttime")
	private String t_stylist_resttime;

	@Column(name = "t_stylist_reservationId")
	private String t_stylist_reservationId;

}
