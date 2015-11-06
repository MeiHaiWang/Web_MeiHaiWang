package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasterSearchConditionTitleInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tMasterSearchConditionTitleId = 0;

	public int getTMasterSearchConditionTitleId() {
		return tMasterSearchConditionTitleId;
	}
	
	public void setTMasterSearchConditionTitleId (int tMasterSearchConditionTitleId) {
		this.tMasterSearchConditionTitleId = tMasterSearchConditionTitleId;
	}

	private String tMasterSearchConditionTitleName = "";

	public String getTMasterSearchConditionTitleName() {
		return tMasterSearchConditionTitleName;
	}
	
	public void setTMasterSearchConditionTitleName (String tMasterSearchConditionTitleName) {
		this.tMasterSearchConditionTitleName = tMasterSearchConditionTitleName;
	}

	private int tMasterSearchConditionTitleTypeId = 0;

	public int getTMasterSearchConditionTitleTypeId() {
		return tMasterSearchConditionTitleTypeId;
	}
	
	public void setTMasterSearchConditionTitleTypeId (int tMasterSearchConditionTitleTypeId) {
		this.tMasterSearchConditionTitleTypeId = tMasterSearchConditionTitleTypeId;
	}

}
