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
@Table(name = "t_coupon")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TCoupon extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_coupon_Id")
	private Long id;

	@Column(name = "t_coupon_name")
	private String t_coupon_name;

	@Column(name = "t_coupon_couponKindId")
	private Long t_coupon_couponKindId;

	@Column(name = "t_coupon_detailText")
	private String t_coupon_detailText;

	@Column(name = "t_coupon_useCondition")
	private String t_coupon_useCondition;

	@Column(name = "t_coupon_imagePath")
	private String t_coupon_imagePath;

	@Column(name = "t_coupon_price")
	private Long t_coupon_price;

	@Column(name = "t_coupon_deadLine")
	private String t_coupon_deadLine;

	@Column(name = "t_coupon_isFirstFlag")
	private Long t_coupon_isFirstFlag;

	@Column(name = "t_coupon_presentationCondition")
	private String t_coupon_presentationCondition;

}
