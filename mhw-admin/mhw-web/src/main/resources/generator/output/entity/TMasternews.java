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
@Table(name = "t_masterNews")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TMasternews extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_masterNewsId")
	private Long id;

	@Column(name = "t_masterNewsName")
	private String t_masterNewsName;

	@Column(name = "t_masterNewImagePath")
	private String t_masterNewImagePath;

	@Column(name = "t_masterNewsURL")
	private String t_masterNewsURL;

	@Column(name = "t_masterNewsUpdateDate")
	private DateTime t_masterNewsUpdateDate;

}
