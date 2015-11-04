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
@Table(name = "t_evaluation")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TEvaluation extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_evaluation_evaluationId")
	private Long id;

	@Column(name = "t_evaluation_name")
	private String t_evaluation_name;

	@Column(name = "t_evaluation_point")
	private Long t_evaluation_point;

	@Column(name = "t_evaluation_userId")
	private Long t_evaluation_userId;

}
