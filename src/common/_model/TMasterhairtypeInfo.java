package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasterhairtypeInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tHairTypeId = 0;

	public int getTHairTypeId() {
		return tHairTypeId;
	}
	
	public void setTHairTypeId (int tHairTypeId) {
		this.tHairTypeId = tHairTypeId;
	}

	private String tHairTypeName = "";

	public String getTHairTypeName() {
		return tHairTypeName;
	}
	
	public void setTHairTypeName (String tHairTypeName) {
		this.tHairTypeName = tHairTypeName;
	}

	private String tHairTypeSex = "";

	public String getTHairTypeSex() {
		return tHairTypeSex;
	}
	
	public void setTHairTypeSex (String tHairTypeSex) {
		this.tHairTypeSex = tHairTypeSex;
	}

	private String tHairTypeImagePath = "";

	public String getTHairTypeImagePath() {
		return tHairTypeImagePath;
	}
	
	public void setTHairTypeImagePath (String tHairTypeImagePath) {
		this.tHairTypeImagePath = tHairTypeImagePath;
	}

	private int tHairTypeSearchConditionId = 0;

	public int getTHairTypeSearchConditionId() {
		return tHairTypeSearchConditionId;
	}
	
	public void setTHairTypeSearchConditionId (int tHairTypeSearchConditionId) {
		this.tHairTypeSearchConditionId = tHairTypeSearchConditionId;
	}

}
