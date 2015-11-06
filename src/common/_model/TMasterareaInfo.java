package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasterAreaInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tAreaAreaId = 0;

	public int getTAreaAreaId() {
		return tAreaAreaId;
	}
	
	public void setTAreaAreaId (int tAreaAreaId) {
		this.tAreaAreaId = tAreaAreaId;
	}

	private String tAreaAreaName = "";

	public String getTAreaAreaName() {
		return tAreaAreaName;
	}
	
	public void setTAreaAreaName (String tAreaAreaName) {
		this.tAreaAreaName = tAreaAreaName;
	}

	private int tAreaLevel = 0;

	public int getTAreaLevel() {
		return tAreaLevel;
	}
	
	public void setTAreaLevel (int tAreaLevel) {
		this.tAreaLevel = tAreaLevel;
	}

	private int tAreaCountryId = 0;

	public int getTAreaCountryId() {
		return tAreaCountryId;
	}
	
	public void setTAreaCountryId (int tAreaCountryId) {
		this.tAreaCountryId = tAreaCountryId;
	}

	private int tAreaIsDetailFlag = 0;

	public int getTAreaIsDetailFlag() {
		return tAreaIsDetailFlag;
	}
	
	public void setTAreaIsDetailFlag (int tAreaIsDetailFlag) {
		this.tAreaIsDetailFlag = tAreaIsDetailFlag;
	}

	private int tAreaParentAreaId = 0;

	public int getTAreaParentAreaId() {
		return tAreaParentAreaId;
	}
	
	public void setTAreaParentAreaId (int tAreaParentAreaId) {
		this.tAreaParentAreaId = tAreaParentAreaId;
	}

}
