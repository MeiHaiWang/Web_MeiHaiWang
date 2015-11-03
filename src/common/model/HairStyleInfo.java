package common.model;

import java.util.ArrayList;
import java.util.List;

import common.util.ListUtilities;

public class HairStyleInfo extends BaseInfo implements IBaseInfo{
	private int hairStyleId = Integer.MIN_VALUE;
	private String hairStyleName ="";
	private String hairStyleImagePath="";
	private int stylistId = Integer.MIN_VALUE;
	private int salonId = Integer.MIN_VALUE;
	private int favoriteNumber = Integer.MIN_VALUE;
	private int isGood = 0;
	private int hairTypeId = 0;
	private String searchConditionId = "-1";
	private int goodNumber =-1;
	private int viewNumber = -1;
	private String areaId = "-1";
	private String areaName = "";
	private String message = "";
	private String updateTime;
	
	public HairStyleInfo(){
		
	}
	
	public void setHairStyleId(int hairStyleId){
		this.hairStyleId = hairStyleId;
	}
	
	public int getHairStyleId(){
		return hairStyleId;
	}
	public void setHairStyleName(String hairStyleName){
		this.hairStyleName = hairStyleName != null ? hairStyleName : "";
	}
	
	public String getHairStyleName(){
		return hairStyleName;
	}

	public void setHairStyleImagePath(String hairStyleImagePath){
		this.hairStyleImagePath = hairStyleImagePath != null ? hairStyleImagePath : "";
	}
	
	public String getHairStyleImagePathStr(){
		return hairStyleImagePath;
	}
	
	public List<String> getHairStyleImagePath(){
    	//imageがコンマで連結している場合がある↓
		List<String> imgPathList = new ArrayList<String>();
		ListUtilities listUtilities = new ListUtilities();
		imgPathList = listUtilities.separateData(hairStyleImagePath);
		return imgPathList;
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
	
	public void setHairTypeId(int hairTypeId){
		this.hairTypeId = hairTypeId;
	}
	
	public int getHairTypeId(){
		return hairTypeId;
	}

	public void setHairStyleSearchConditionId(
			String searchConditionId) {
		this.searchConditionId = searchConditionId != null ? searchConditionId : "";
	}
	public String getHairStyleSearchConditionId(){
		return searchConditionId;
	}

	public void setHairStyleGoodNumber(int goodNumber) {
		this.goodNumber = goodNumber;
	}
	public int getHairStyleGoodNumber(){
		return this.goodNumber ;
	}

	public void setHairStyleViewNumber(int viewNumber) {
		this.viewNumber  = viewNumber;
	}
	public int getHairStyleViewNumber(){
		return this.viewNumber ;
	}

	public void setHairStyleAreaId(String areaId) {
		this.areaId  = areaId != null ? areaId:"-1";
	}
	public String getHairStyleAreaId(){
		return this.areaId ;
	}

	public void setHairStyleAreaName(String areaName) {
		this.areaName   = areaName != null ? areaName:"";
	}
	public String getHairStyleAreaName(){
		return this.areaName;
	}

	public void setHairStyleMessage(String message) {
		this.message   = message != null ? message:"";
	}
	public String getHairStyleMessage(){
		return this.message;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime   = updateTime != null ? updateTime:"";
	}
	public String getUpdateTime(){
		return this.updateTime;
	}
	
	
}
