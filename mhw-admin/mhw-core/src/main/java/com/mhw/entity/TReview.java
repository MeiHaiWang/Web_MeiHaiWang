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
@Table(name = "t_review")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TReview extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_review_id")
	private Long id;

	@Column(name = "t_review_userId")
	private Long t_review_userId;

	@Column(name = "t_review_postedDate")
	private DateTime t_review_postedDate;

	@Column(name = "t_review_commentId")
	private String t_review_commentId;

	@Column(name = "t_review_text")
	private String t_review_text;

	@Column(name = "t_review_evaluation_point")
	private String t_review_evaluation_point;

}
