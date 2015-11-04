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
@Table(name = "t_reservation")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TReservation extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_reservation_id")
	private Long id;

	@Column(name = "t_reservation_userId")
	private Long t_reservation_userId;

	@Column(name = "t_reservation_salonId")
	private Long t_reservation_salonId;

	@Column(name = "t_reservation_stylistId")
	private Long t_reservation_stylistId;

	@Column(name = "t_reservation_Date")
	private DateTime t_reservation_Date;

	@Column(name = "t_reservation_isFinished")
	private Long t_reservation_isFinished;

	@Column(name = "t_reservation_menuId")
	private String t_reservation_menuId;

	@Column(name = "t_reservation_seatId")
	private Long t_reservation_seatId;

	@Column(name = "t_reservation_memo")
	private String t_reservation_memo;

	@Column(name = "t_reservation_appoint")
	private Long t_reservation_appoint;

}
