package common.model;

import java.util.ArrayList;
import java.util.List;

public class HairSalonInfo {

	private int hairSalonId = Integer.MIN_VALUE;
	private String hairSalonName ="";
	private String hairSalonImagePath="";
	private List<String> areaNameList = new ArrayList<String>();
	private String message ="";

	public HairSalonInfo(){
		
	}

	public HairSalonInfo(int hairSalonId,String hairSalonName , String hairSalonImagePath,
			List<String> areNameList , String message ){
		this.hairSalonId = hairSalonId;
		this.hairSalonName = hairSalonName;
		this.hairSalonImagePath = hairSalonImagePath;
		this.areaNameList = areNameList;
		this.message = message;
		
	}

	public void setHairSalonId(int hairSalonId){
		this.hairSalonId = hairSalonId;
	}
	
	public int getHairSalonId(){
		return hairSalonId;
	}
	public void setHairSalonName(String hairSalonName){
		this.hairSalonName = hairSalonName;
	}
	
	public String getHairSalonName(){
		return hairSalonName;
	}	

	public void setHairSalonImagePath(String hairSalonImagePath){
		this.hairSalonImagePath = hairSalonImagePath;
	}
	
	public String getHairSalonImagePath(){
		return hairSalonImagePath;
	}	

	public void setAreaNameList(List<String> areaNameList){
		this.areaNameList = areaNameList;
	}
	
	public List<String> getAreaNameList(){
		return areaNameList;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
}