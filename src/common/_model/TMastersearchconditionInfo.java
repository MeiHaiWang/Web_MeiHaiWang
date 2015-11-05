package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMastersearchconditionInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tMasterSearchConditionId = 0;

	public int getTMasterSearchConditionId() {
		return tMasterSearchConditionId;
	}
	
	public void setTMasterSearchConditionId (int tMasterSearchConditionId) {
		this.tMasterSearchConditionId = tMasterSearchConditionId;
	}

	private String tMasterSearchConditionName = "";

	public String getTMasterSearchConditionName() {
		return tMasterSearchConditionName;
	}
	
	public void setTMasterSearchConditionName (String tMasterSearchConditionName) {
		this.tMasterSearchConditionName = tMasterSearchConditionName;
	}

	private int tMasterSearchConditionTitleId = 0;

	public int getTMasterSearchConditionTitleId() {
		return tMasterSearchConditionTitleId;
	}
	
	public void setTMasterSearchConditionTitleId (int tMasterSearchConditionTitleId) {
		this.tMasterSearchConditionTitleId = tMasterSearchConditionTitleId;
	}

}
