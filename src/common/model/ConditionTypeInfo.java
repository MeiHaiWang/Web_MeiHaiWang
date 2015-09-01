package common.model;

public class ConditionTypeInfo {
	private int conditionTypeId = Integer.MIN_VALUE;
	private String conditionTypeName ="";

	public ConditionTypeInfo(){
		
	}
	
	public void setConditionTypeId(int id){
		this.conditionTypeId = id;
	}
	
	public int getConditionTypeId(){
		return conditionTypeId;
	}
	public void setConditionTypeName(String name){
		this.conditionTypeName = name != null ? name : "";
	}
	
	public String getConditionTypeName(){
		return conditionTypeName;
	}
}
