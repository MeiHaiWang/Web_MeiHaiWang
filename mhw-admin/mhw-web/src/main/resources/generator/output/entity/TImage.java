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
@Table(name = "t_image")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TImage extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "t_image_id")
	private Long id;

	@Column(name = "t_image_name")
	private String t_image_name;

	@Column(name = "t_image_filepath")
	private String t_image_filepath;

	@Column(name = "t_image_size")
	private String t_image_size;

	@Column(name = "t_image_salonId")
	private Long t_image_salonId;

	@Column(name = "t_image_hash")
	private String t_image_hash;

}
