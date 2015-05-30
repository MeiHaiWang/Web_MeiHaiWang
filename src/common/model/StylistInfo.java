package common.model;

public class StylistInfo {
	private int stylistId = Integer.MIN_VALUE;
	private String stylistName ="";
	private String stylistImagePath="";
	private int salonId = Integer.MIN_VALUE;
	private int isgood = 0;
	private int goodNumber = Integer.MIN_VALUE;
	
	public StylistInfo(){
		
	}

	public StylistInfo(int stylistId,String stylistName , String stylistImagePath,
			int salonId , int isgood , int goodNumber ){
		this.stylistId = stylistId;
		this.stylistName = stylistName;
		this.stylistImagePath = stylistImagePath;
		this.salonId = salonId;
		this.isgood = isgood;
		this.goodNumber = goodNumber;
	}
	
	public void setStylistId(int stylistId){
		this.stylistId = stylistId;
	}
	
	public int getStylistId(){
		return stylistId;
	}
	
	public void setStylistName(String stylistName){
		this.stylistName = stylistName;
	}
	
	public String getStylistName(){
		return stylistName;
	}
	
	public void setStylistImagePath(String stylistImagePath){
		this.stylistImagePath = stylistImagePath;
	}
	
	public String getStylistImagePath(){
		return stylistImagePath;
	}	
	
	public void setSalonId(int salonId){
		this.salonId = salonId;
	}
	
	public int getSalonId(){
		return salonId;
	}

	public void setIsGood(int isGood){
		this.isgood = isGood;
	}
	
	public int getIsGood(){
		return isgood;
	}
	
	public void setGoodNumber(int goodNumber){
		this.goodNumber = goodNumber;
	}
	
	public int getGoodNumber(){
		return goodNumber;
	}
}
