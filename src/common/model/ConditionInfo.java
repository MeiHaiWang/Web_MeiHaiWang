package common.model;

public class ConditionInfo {
	private int conditionId = Integer.MIN_VALUE;
	private String conditionName ="";
	private int conditionTitleId = Integer.MIN_VALUE;

	public ConditionInfo(){
		
	}
	
	public void setConditionId(int conditionId){
		this.conditionId = conditionId;
	}
	
	public int getConditionId(){
		return conditionId;
	}
	
	public void setConditionName(String conditionName){
		this.conditionName = conditionName != null ? conditionName : "";
	}
	
	public String getConditionName(){
		return conditionName;
	}

	public void setConditionTitleId(int conditionTitleId){
		this.conditionTitleId = conditionTitleId;
	}
	
	public int getConditionTitleId(){
		return conditionTitleId;
	}

}
