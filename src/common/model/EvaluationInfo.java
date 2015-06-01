package common.model;

import java.util.Date;

public class EvaluationInfo {
	private int evaluationId = Integer.MIN_VALUE;
	private String evaluationName ="";
	private int evaluationPoint = Integer.MIN_VALUE;
	private int evaluationUserId = Integer.MIN_VALUE;

	public EvaluationInfo(){
	}
	
	public void setEvaluationId(int evaluationId){
		this.evaluationId = evaluationId;
	}
	
	public int getEvaluationId(){
		return evaluationId;
	}
	
	public void setEvaluationUserId(int evaluationUserId){
		this.evaluationUserId = evaluationUserId;
	}
	
	public int getEvaluationUserId(){
		return evaluationUserId;
	}

	public void setEvaluationName(String evaluationName){
		this.evaluationName = evaluationName != null ? evaluationName : "";
	}

	public String getEvaluationName(){
		return evaluationName;
	}

	public void setEvaluationPoint(int evaluationPoint){
		this.evaluationPoint = evaluationPoint;
	}
	
	public int getEvaluationPoint(){
		return evaluationPoint;
	}
	

	
}
