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
@Table(name = "t_seat")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TSeat extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_seat_id")
	private Long id;

	@Column(name = "t_seat_name")
	private String t_seat_name;

	@Column(name = "t_seat_salonId")
	private Long t_seat_salonId;

	@Column(name = "t_seat_memo")
	private String t_seat_memo;

}
