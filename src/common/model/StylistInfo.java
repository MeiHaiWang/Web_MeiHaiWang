package common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.util.ListUtilities;

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
	
	//for Master
	private String phoneNumber = "";
	private String mail = "";
	private String imagePath = "";
	private Date birth = new Date();
	private String position = "";
	private String specialMenu = "";
	private String menuId = "";
	private String searchConditionId = "";
	private String restDay;
	private String restTime;
	private int userId = -1;
	
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

	public String getStylistImagePathStr(){
		return stylistImagePath;
	}
	
	public List<String> getStylistImagePath(){
		List<String> imgPathList = new ArrayList<String>();
		ListUtilities listUtilities = new ListUtilities();
		imgPathList = listUtilities.separateData(stylistImagePath);
		return imgPathList;
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
	
	public String getStylistYearsNumber(){
		return yearsNumber+"";
		//return yearsNumber+"年間";
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

	public void setPhoneNumber(String phone){
		this.phoneNumber = phone != null ? phone : "";
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setMail(String mail){
		this.mail = mail != null ? mail : "";
	}
	public String getMail(){
		return mail;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath != null ? imagePath : "";
	}
	public String getImagePath(){
		return imagePath;
	}

	public void setBirth(Date birth){
		this.birth = birth != null ? birth : new Date(0);
	}
	public Date getBirth(){
		return birth;
	}

	public void setPosition(String position){
		this.position = position != null ? position : "";
	}
	public String getPosition(){
		return position;
	}

	public void setSpecialMenu(String spMenu){
		this.specialMenu = spMenu != null ? spMenu : "";
	}
	public String getSpecialMenu(){
		return specialMenu;
	}

	public void setMenuId(String menuId){
		this.menuId = menuId != null ? menuId : "";
	}
	public String getMenuId(){
		return menuId;
	}

	public void setStylistSearchConditionId(String searchConditionId) {
		this.searchConditionId = searchConditionId;
	}
	public String getStylistSearchConditionId(){
		return searchConditionId;
	}

	public void setStylistRestDay(String restDay) {
		this.restDay = restDay != null ? restDay : "";
	}
	public String getStylistRestDay() {
		return restDay;
	}

	public void setStylistRestTime(String restTime) {
		this.restTime = restTime != null ? restTime : "";
	}
	public String getStylistRestTime() {
		return restTime;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserId() {
		return userId;
	}

}


