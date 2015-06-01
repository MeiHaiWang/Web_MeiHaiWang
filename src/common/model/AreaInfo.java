package common.model;

public class AreaInfo {
	private int AreaId = Integer.MIN_VALUE;
	private String AreaName ="";
	private int AreaLevel = Integer.MIN_VALUE;
	private int AreaCountryId = Integer.MIN_VALUE;
	private int isDetailFlag = Integer.MIN_VALUE;
	
	public AreaInfo(){
	}
	
	public void setAreaId(int AreaId){
		this.AreaId = AreaId;
	}
	
	public int getAreaId(){
		return AreaId;
	}

	public void setAreaName(String AreaName){
		this.AreaName = AreaName != null ? AreaName : "";
	}
	
	public String getAreaName(){
		return AreaName;
	}

	public void setAreaLevel(int AreaLevel){
		this.AreaLevel = AreaLevel;
	}
	
	public int getAreaLevel(){
		return AreaLevel;
	}
		
	public void setAreaCountryId(int AreaCountryId){
		this.AreaCountryId = AreaCountryId;
	}
	
	public int getAreaCountryId(){
		return AreaCountryId;
	}

	public void setisDetailFlag(int isDetailFlag){
		this.isDetailFlag = isDetailFlag;
	}
	
	public int getisDetailFlag(){
		return isDetailFlag;
	}

}
