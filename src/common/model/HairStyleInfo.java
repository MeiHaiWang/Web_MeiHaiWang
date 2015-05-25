package common.model;

public class HairStyleInfo {
	private int hairStyleId = Integer.MIN_VALUE;
	private String hairStyleName ="";
	private String hairStyleImagePath="";
	private int stylistId = Integer.MIN_VALUE;
	private int salonId = Integer.MIN_VALUE;

	public HairStyleInfo(){
		
	}

	public HairStyleInfo(int hairStyleId,String hairStyleName , String hairStyleImagePath,
			int stylistId , int salonId ){
		this.hairStyleId = hairStyleId;
		this.hairStyleName = hairStyleName;
		this.hairStyleImagePath = hairStyleImagePath;
		this.stylistId = stylistId;
		this.salonId = salonId;
	}
	
	public void setHairStyleId(int hairStyleId){
		this.hairStyleId = hairStyleId;
	}
	
	public int getHairStyleId(){
		return hairStyleId;
	}
	public void setHairStyleName(String hairStyleName){
		this.hairStyleName = hairStyleName;
	}
	
	public String getHairStyleName(){
		return hairStyleName;
	}

	public void setHairStyleImagePath(String hairStyleImagePath){
		this.hairStyleImagePath = hairStyleImagePath;
	}
	
	public String getHairStyleImagePath(){
		return hairStyleImagePath;
	}
	
	public void setStylistId(int stylistId){
		this.stylistId = stylistId;
	}
	
	public int getStylistId(){
		return stylistId;
	}

	public void setSalonId(int salonId){
		this.salonId = salonId;
	}
	
	public int getSalonId(){
		return salonId;
	}
	
}
