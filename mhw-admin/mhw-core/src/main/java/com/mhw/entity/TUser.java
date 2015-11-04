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
@Table(name = "t_user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TUser extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_user_Id")
	private Long id;

	@Column(name = "t_user_disableFlag")
	private Long t_user_disableFlag;

	@Column(name = "t_user_mail")
	private String t_user_mail;

	@Column(name = "t_user_passward")
	private String t_user_passward;

	@Column(name = "t_user_cookie")
	private String t_user_cookie;

	@Column(name = "t_user_imagePath")
	private String t_user_imagePath;

	@Column(name = "t_user_sex")
	private Long t_user_sex;

	@Column(name = "t_user_birth")
	private DateTime t_user_birth;

	@Column(name = "t_user_name")
	private String t_user_name;

	@Column(name = "t_user_favoriteSalonId")
	private String t_user_favoriteSalonId;

	@Column(name = "t_user_favoriteStylistId")
	private String t_user_favoriteStylistId;

	@Column(name = "t_user_favoriteHairStyleId")
	private String t_user_favoriteHairStyleId;

	@Column(name = "t_user_latestViewSalonId")
	private String t_user_latestViewSalonId;

	@Column(name = "t_user_latestViewStylistId")
	private String t_user_latestViewStylistId;

	@Column(name = "t_user_latestViewHairStyleId")
	private String t_user_latestViewHairStyleId;

	@Column(name = "t_user_point")
	private Long t_user_point;

	@Column(name = "t_user_historySalonId")
	private Long t_user_historySalonId;

	@Column(name = "t_user_MasterSalonId")
	private Long t_user_MasterSalonId;

	@Column(name = "t_user_tel")
	private String t_user_tel;

	@Column(name = "t_user_reservationId")
	private String t_user_reservationId;

}
