package common.model;

import java.util.ArrayList;
import java.util.List;

import common.util.ListUtilities;

public class HairSalonInfo {

	private int hairSalonId = Integer.MIN_VALUE;
	private String hairSalonName ="";
	private String hairSalonImagePath="";
	private List<String> areaNameList = new ArrayList<String>();
	private String message ="";
	private int favoriteNumber = Integer.MIN_VALUE;
	private int isGood = 0;
    private String telNumber ="";
    private String address ="";
    private String businessHour ="";
    private String regularHoliday ="";
    private String multiLingual ="";
    private int wordOfMonthCount = Integer.MIN_VALUE;
    private int isNetReservation = Integer.MIN_VALUE;
    private Double evaluationPointMid = Double.MIN_VALUE;
    private Double salonLatitude = Double.MIN_VALUE;
    private Double salonLongitude = Double.MIN_VALUE;
    private String salonMapInfo = "";
    private String salonMapImagePath = "";
    private int salonReviewId = Integer.MIN_VALUE;
    private String salonContactName = "";
    
	public HairSalonInfo(){
		
	}

	public void setHairSalonId(int hairSalonId){
		this.hairSalonId = hairSalonId;
	}
	
	public int getHairSalonId(){
		return hairSalonId;
	}
	public void setHairSalonName(String hairSalonName){
		this.hairSalonName = hairSalonName != null ? hairSalonName : "";
	}
	
	public String getHairSalonName(){
		return hairSalonName;
	}	

	public void setHairSalonImagePath(String hairSalonImagePath){
		this.hairSalonImagePath = hairSalonImagePath != null ? hairSalonImagePath : "";
	}
	
	public List<String> getHairSalonImagePath(){
		List<String> imgPathList = new ArrayList<String>();
		ListUtilities listUtilities = new ListUtilities();
		imgPathList = listUtilities.separateData(hairSalonImagePath);
		return imgPathList;
		//return hairSalonImagePath;
	}	

	public void setAreaNameList(List<String> areaNameList){
		this.areaNameList = areaNameList != null ? areaNameList : new ArrayList<String>();
	}
	
	public List<String> getAreaNameList(){
		return areaNameList;
	}
	
	public void setMessage(String message){
		this.message = message != null ? message : "";
	}
	
	public String getMessage(){
		return message;
	}

	public void setFavoriteNumber(int favoriteNumber){
		this.favoriteNumber = favoriteNumber;
	}
	
	public int getFavoriteNumber(){
		return favoriteNumber;
	}	
	public void setIsGood(int isGood){
		this.isGood = isGood;
	}
	
	public int getIsGood(){
		return isGood;
	}	
	
	public void setTelNumber(String tel){
		this.telNumber = tel != null ? tel : "";
	}
	
	public String getTel(){
		return telNumber;
	}	
	
	public void setAddress(String address){
		this.address = address != null ? address : "";
	}
	
	public String getAddress(){
		return address;
	}	
	
	public void setBusinessHour(String businessHour){
		this.businessHour = businessHour != null ? businessHour : "";
	}
	
	public String getBusinessHour(){
		return businessHour;
	}		

	public void setRegularHoliday(String regularHoliday){
		this.regularHoliday = regularHoliday != null ? regularHoliday : "";
	}
	
	public String getRegularHoliday(){
		return regularHoliday;
	}		
	
	public void setMultiLingual(String multiLingual){
		this.multiLingual = multiLingual != null ? multiLingual : "";
	}
	
	public String getMultiLingual(){
		return multiLingual;
	}		

	public void setWordOfMonth(int wordOfMonth){
		this.wordOfMonthCount = wordOfMonth;
	}
	
	public int getWordOfMonth(){
		return wordOfMonthCount;
	}		
	
	public void setIsNetReservation(int isNetReservation){
		this.isNetReservation = isNetReservation;
	}
	
	public int getIsNetReservation(){
		return isNetReservation;
	}	
	
	public void setEvaluationPointMid(double point){
		this.evaluationPointMid = point;
	}
	
	public double getEvaluationPointMid(){
		return evaluationPointMid;
	}

	public void setSalonLatitude(double latitude){
		this.salonLatitude = latitude;
	}
	
	public double getSalonLatitude(){
		return salonLatitude;
	}
	
	public void setSalonLongitude(double longitude){
		this.salonLongitude = longitude;
	}
	
	public double getSalonLongitude(){
		return salonLongitude;
	}
	
	public void setSalonMapInfo(String mapInfo){
		this.salonMapInfo = mapInfo != null ? mapInfo : "";
	}
	
	public String getSalonMapInfo(){
		return salonMapInfo;
	}
	
	public void setSalonMapImagePath(String url){
		this.salonMapImagePath = url != null ? url : "";
	}
	
	public String getSalonMapImagePath(){
		return salonMapImagePath;
	}
	
	public void setSalonReviewId(int reviewId){
		//this.salonReviewId = reviewId != null ? reviewId : "";
		this.salonReviewId = reviewId;
	}
	
	public int getSalonReviewId(){
		return salonReviewId;
	}

	public void setSalonContactUserName(String name){
		this.salonContactName = name != null ? name : "";
	}
	
	public String getSalonContactName(){
		return salonContactName;
	}


}

