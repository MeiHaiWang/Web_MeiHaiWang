package common.model;

public class HairStyleInfo {
	private int hairStyleId = Integer.MIN_VALUE;
	private String hairStyleName ="";
	private String hairStyleImagePath="";
	private int stylistId = Integer.MIN_VALUE;
	private int salonId = Integer.MIN_VALUE;
	private int goodNumber = Integer.MIN_VALUE;
	private int isGood = 0;
	public HairStyleInfo(){
		
	}

	public HairStyleInfo(int hairStyleId,String hairStyleName , String hairStyleImagePath,
			int stylistId , int salonId,int goodNumber , int isGood){
		this.hairStyleId = hairStyleId;
		this.hairStyleName = hairStyleName;
		this.hairStyleImagePath = hairStyleImagePath;
		this.stylistId = stylistId;
		this.salonId = salonId;
		this.goodNumber = goodNumber;
		this.isGood = isGood;
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

	public void setGoodNumber(int goodNumber){
		this.goodNumber = goodNumber;
	}
	
	public int getGoodNumber(){
		return goodNumber;
	}

	public void setIsGood(int isGood){
		this.isGood = isGood;
	}
	
	public int getIsGood(){
		return isGood;
	}	
}
