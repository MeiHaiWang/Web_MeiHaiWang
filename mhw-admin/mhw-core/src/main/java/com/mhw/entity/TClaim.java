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
@Table(name = "t_claim")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TClaim extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_claim_id")
	private Long id;

	@Column(name = "t_claim_userId")
	private Long t_claim_userId;

	@Column(name = "t_claim_salonId")
	private Long t_claim_salonId;

	@Column(name = "t_claim_reserveId")
	private Long t_claim_reserveId;

	@Column(name = "t_claim_menuId")
	private Long t_claim_menuId;

	@Column(name = "t_claim_message")
	private String t_claim_message;

	@Column(name = "t_claim_date")
	private String t_claim_date;

}
