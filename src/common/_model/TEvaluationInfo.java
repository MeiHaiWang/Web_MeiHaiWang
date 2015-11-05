package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TEvaluationInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tEvaluationEvaluationId = 0;

	public int getTEvaluationEvaluationId() {
		return tEvaluationEvaluationId;
	}
	
	public void setTEvaluationEvaluationId (int tEvaluationEvaluationId) {
		this.tEvaluationEvaluationId = tEvaluationEvaluationId;
	}

	private String tEvaluationName = "";

	public String getTEvaluationName() {
		return tEvaluationName;
	}
	
	public void setTEvaluationName (String tEvaluationName) {
		this.tEvaluationName = tEvaluationName;
	}

	private int tEvaluationPoint = 0;

	public int getTEvaluationPoint() {
		return tEvaluationPoint;
	}
	
	public void setTEvaluationPoint (int tEvaluationPoint) {
		this.tEvaluationPoint = tEvaluationPoint;
	}

	private int tEvaluationUserId = 0;

	public int getTEvaluationUserId() {
		return tEvaluationUserId;
	}
	
	public void setTEvaluationUserId (int tEvaluationUserId) {
		this.tEvaluationUserId = tEvaluationUserId;
	}

}
