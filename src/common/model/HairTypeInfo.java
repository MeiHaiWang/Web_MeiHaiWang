package common.model;

/**
 * 
 * @author 
 * ID, name, sex, image
 */

public class HairTypeInfo extends BaseInfo implements IBaseInfo{
	private int hairTypeId = Integer.MIN_VALUE;
	private String hairTypeName ="";
	private String hairTypeImagePath="";
	private int hairTypeSex = 0;

	public HairTypeInfo(){
		
	}
	
	public void setHairTypeId(int hairTypeId){
		this.hairTypeId = hairTypeId;
	}
	
	public int getHairTypeId(){
		return hairTypeId;
	}
	public void setHairTypeName(String hairTypeName){
		this.hairTypeName = hairTypeName != null ? hairTypeName : "";
	}
	
	public String getHairTypeName(){
		return hairTypeName;
	}

	public void setHairTypeImagePath(String hairTypeImagePath){
		this.hairTypeImagePath = hairTypeImagePath != null ? hairTypeImagePath : "";
	}
	
	public String getHairTypeImagePath(){
		return hairTypeImagePath;
	}
	
	public void setHairTypeSex(int sex){
		this.hairTypeSex = sex;
	}
	
	public int getHairTypeSex(){
		return hairTypeSex;
	}

}
