package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasterSearchConditionTypeInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tMasterSearchConditionTypeId = 0;

	public int getTMasterSearchConditionTypeId() {
		return tMasterSearchConditionTypeId;
	}
	
	public void setTMasterSearchConditionTypeId (int tMasterSearchConditionTypeId) {
		this.tMasterSearchConditionTypeId = tMasterSearchConditionTypeId;
	}

	private String tMasterSearchConditionTypeName = "";

	public String getTMasterSearchConditionTypeName() {
		return tMasterSearchConditionTypeName;
	}
	
	public void setTMasterSearchConditionTypeName (String tMasterSearchConditionTypeName) {
		this.tMasterSearchConditionTypeName = tMasterSearchConditionTypeName;
	}

}
