package common.model;

public class StylistInfo {
	private int stylistId = Integer.MIN_VALUE;
	private String stylistName ="";
	private String stylistImagePath="";
	private int salonId = Integer.MIN_VALUE;
	private int isgood = 0;
	private int favoriteNumber = Integer.MIN_VALUE;
	private int stylistGender = Integer.MIN_VALUE;
	private int yearsNumber = Integer.MIN_VALUE;
	private String stylistMessage="";
	private int isNetReservation = Integer.MIN_VALUE;
	
	public StylistInfo(){
		
	}
	
	public void setStylistId(int stylistId){
		this.stylistId = stylistId;
	}
	
	public int getStylistId(){
		return stylistId;
	}
	
	public void setStylistGender(int stylistGender){
		this.stylistGender = stylistGender;
	}

	public int getStylistGender(){
		return stylistGender;
	}
	
	public void setStylistName(String stylistName){
		this.stylistName = stylistName != null ? stylistName : "";
	}
	
	public String getStylistName(){
		return stylistName;
	}
	
	public void setStylistImagePath(String stylistImagePath){
		this.stylistImagePath = stylistImagePath != null ? stylistImagePath : "";
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
	
	public void setFavoriteNumber(int favoriteNumber){
		this.favoriteNumber = favoriteNumber;
	}
	
	public int getFavoriteNumber(){
		return favoriteNumber;
	}

	public void setStylistYears(int yearsNumber){
		this.yearsNumber = yearsNumber;
	}
	
	public int getStylistYearsNumber(){
		return yearsNumber;
	}

	public void setStylistMessage(String stylistMessage){
		this.stylistMessage = stylistMessage != null ? stylistMessage : "";
	}
	public String getStylistMessage(){
		return stylistMessage;
	}

	public void setIsNetReservation(int isNetReservation){
		this.isNetReservation = isNetReservation;
	}
	
	public int getIsNetReservation(){
		return isNetReservation;
	}
}
