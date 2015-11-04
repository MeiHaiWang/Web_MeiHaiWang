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
@Table(name = "t_hairSalonMaster")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class THairsalonmaster extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_hairSalonMaster_salonId")
	private Long id;

	@Column(name = "t_hairSalonMaster_name")
	private String t_hairSalonMaster_name;

	@Column(name = "t_hairSalonMaster_viewNumber")
	private Long t_hairSalonMaster_viewNumber;

	@Column(name = "t_hairSalonMaster_goodNumber")
	private Long t_hairSalonMaster_goodNumber;

	@Column(name = "t_hairSalonMaster_displayOrder")
	private Long t_hairSalonMaster_displayOrder;

	@Column(name = "t_hairSalonMaster_areaId")
	private String t_hairSalonMaster_areaId;

	@Column(name = "t_hairSalonMaster_menuId")
	private String t_hairSalonMaster_menuId;

	@Column(name = "t_hairSalonMaster_disableFlag")
	private Long t_hairSalonMaster_disableFlag;

	@Column(name = "t_hairSalonMaster_detailText")
	private String t_hairSalonMaster_detailText;

	@Column(name = "t_hairSalonMaster_couponId")
	private String t_hairSalonMaster_couponId;

	@Column(name = "t_hairSalonMaster_stylistId")
	private String t_hairSalonMaster_stylistId;

	@Column(name = "t_hairSalonMaster_salonImagePath")
	private String t_hairSalonMaster_salonImagePath;

	@Column(name = "t_hairSalonMaster_reviewId")
	private String t_hairSalonMaster_reviewId;

	@Column(name = "t_hairSalonMaster_hairStyleId")
	private String t_hairSalonMaster_hairStyleId;

	@Column(name = "t_hairSalonMaster_contactUserName")
	private String t_hairSalonMaster_contactUserName;

	@Column(name = "t_hairSalonMaster_address")
	private String t_hairSalonMaster_address;

	@Column(name = "t_hairSalonMaster_phoneNumber")
	private String t_hairSalonMaster_phoneNumber;

	@Column(name = "t_hairSalonMaster_mail")
	private String t_hairSalonMaster_mail;

	@Column(name = "t_hairSalonMaster_passward")
	private String t_hairSalonMaster_passward;

	@Column(name = "t_hairSalonMaster_openTime")
	private String t_hairSalonMaster_openTime;

	@Column(name = "t_hairSalonMaster_closeTime")
	private String t_hairSalonMaster_closeTime;

	@Column(name = "t_hairSalonMaster_closeDay")
	private String t_hairSalonMaster_closeDay;

	@Column(name = "t_hairSalonMaster_creditAvailable")
	private Long t_hairSalonMaster_creditAvailable;

	@Column(name = "t_hairSalonMaster_carParkAvailable")
	private Long t_hairSalonMaster_carParkAvailable;

	@Column(name = "t_hairSalonMaster_mapUrl")
	private String t_hairSalonMaster_mapUrl;

	@Column(name = "t_hairSalonMaster_mapImagePath")
	private String t_hairSalonMaster_mapImagePath;

	@Column(name = "t_hairSalonMaster_message")
	private String t_hairSalonMaster_message;

	@Column(name = "t_hairSalonMaster_mapLatitude")
	private Double t_hairSalonMaster_mapLatitude;

	@Column(name = "t_hairSalonMaster_mapLongitude")
	private Double t_hairSalonMaster_mapLongitude;

	@Column(name = "t_hairSalonMaster_mapInfoText")
	private String t_hairSalonMaster_mapInfoText;

	@Column(name = "t_hairSalonMaster_availableCountryId")
	private String t_hairSalonMaster_availableCountryId;

	@Column(name = "t_hairSalonMaster_favoriteNumber")
	private Long t_hairSalonMaster_favoriteNumber;

	@Column(name = "t_hairSalonMaster_isNetReservation")
	private Long t_hairSalonMaster_isNetReservation;

	@Column(name = "t_hairSalonMaster_searchConditionId")
	private String t_hairSalonMaster_searchConditionId;

	@Column(name = "t_hairSalonMaster_reservationId")
	private String t_hairSalonMaster_reservationId;

	@Column(name = "t_hairSalonMaster_seatId")
	private String t_hairSalonMaster_seatId;

}
