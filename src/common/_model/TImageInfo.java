package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TImageInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tImageId = 0;

	public int getTImageId() {
		return tImageId;
	}
	
	public void setTImageId (int tImageId) {
		this.tImageId = tImageId;
	}

	private String tImageName = "";

	public String getTImageName() {
		return tImageName;
	}
	
	public void setTImageName (String tImageName) {
		this.tImageName = tImageName;
	}

	private String tImageFilepath = "";

	public String getTImageFilepath() {
		return tImageFilepath;
	}
	
	public void setTImageFilepath (String tImageFilepath) {
		this.tImageFilepath = tImageFilepath;
	}

	private String tImageSize = "";

	public String getTImageSize() {
		return tImageSize;
	}
	
	public void setTImageSize (String tImageSize) {
		this.tImageSize = tImageSize;
	}

	private int tImageSalonId = 0;

	public int getTImageSalonId() {
		return tImageSalonId;
	}
	
	public void setTImageSalonId (int tImageSalonId) {
		this.tImageSalonId = tImageSalonId;
	}

	private String tImageHash = "";

	public String getTImageHash() {
		return tImageHash;
	}
	
	public void setTImageHash (String tImageHash) {
		this.tImageHash = tImageHash;
	}

}
