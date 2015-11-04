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
@Table(name = "t_comment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TComment extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_comment_id")
	private Long id;

	@Column(name = "t_comment_userId")
	private Long t_comment_userId;

	@Column(name = "t_comment_message")
	private String t_comment_message;

	@Column(name = "t_comment_reviewId")
	private Long t_comment_reviewId;

	@Column(name = "t_comment_date")
	private DateTime t_comment_date;

}
