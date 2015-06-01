package common.model;

public class ConditionTitleInfo {
	private int conditionTitleId = Integer.MIN_VALUE;
	private String conditionTitleName ="";

	public ConditionTitleInfo(){
		
	}
	
	public void setConditionTitleId(int conditionTitleId){
		this.conditionTitleId = conditionTitleId;
	}
	
	public int getConditionTitleId(){
		return conditionTitleId;
	}
	public void setConditionTitleName(String conditionTitleName){
		this.conditionTitleName = conditionTitleName != null ? conditionTitleName : "";
	}
	
	public String getConditionTitleName(){
		return conditionTitleName;
	}

	
}
