package common.model;

import java.util.ArrayList;
import java.util.Date;
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
    private String salonMapUrl = "";
    private String salonMapImagePath = "";
    private int salonReviewId = Integer.MIN_VALUE;
    private String salonContactName = "";
    
    //for SalonMaster
    //private String salonCountryName = "";
    private String salonDetailText = "";
    private String openTime = "";
    private String closeTime = "";
    private String closeDay = "";
    private String countryName = "";
    private boolean creditAvailable = false;
    private boolean carParkAvailable = false;
    private boolean japaneseAvailable = false;
    
    private int areaId = -1;
    
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

	public String getHairSalonImagePathOneLine(){
		return hairSalonImagePath;
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


	public void setSalonDetailText(String detailText){
		this.salonDetailText = detailText != null ? detailText : "";
	}
	
	public String getSalonDetailText(){
		return salonDetailText;
	}

	public void setSalonOpenTime(String openTime){
		this.openTime = openTime;
	}
	
	public String getSalonOpenTime(){
		return openTime;
	}

	public void setSalonCloseTime(String closeTime){
		this.closeTime = closeTime;
	}
	
	public String getSalonCloseTime(){
		return closeTime;
	}

	public void setSalonCloseDay(String closeDay){
		this.closeDay = closeDay != null ? closeDay : "";
	}
	
	public String getSalonCloseDay(){
		return closeDay;
	}

	public void setSalonCountryName(String countryName){
		this.countryName = countryName != null ? countryName : "";
	}
	
	public String getSalonCountryName(){
		return countryName;
	}

	public void setSalonCreditAvailable(boolean creditAvailable){
		this.creditAvailable = creditAvailable;
	}
	
	public boolean getSalonCreditAvailable(){
		return creditAvailable;
	}

	public void setSalonCarParkAvailable(boolean carParkAvailable){
		this.carParkAvailable = carParkAvailable;
	}
	
	public boolean getSalonCarParkAvailable(){
		return carParkAvailable;
	}

	public void setSalonJapaneseAvailable(boolean japaneseAvailable){
		this.japaneseAvailable = japaneseAvailable;
	}
	
	public boolean getJapaneseAvailable(){
		return japaneseAvailable;
	}

	public void setSalonMapUrl(String mapUrl) {
		this.salonMapUrl = mapUrl != null ? mapUrl : "";		
	}
	public String getSalonMapUrl(){
		return salonMapUrl;
	}

	public void setSalonAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getSalonAreaId(){
		return areaId;
	}

}


