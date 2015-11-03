package common.model;

import java.util.ArrayList;
import java.util.List;

public class BeautyNewsInfo extends BaseInfo implements IBaseInfo {

	private int beautyNewsId = Integer.MIN_VALUE;
	private String beautyNewsName = "";
	private String beautyNewsImagePath = "";
	private String beautyNewsURL = "";
	private String updateDate="";
	public BeautyNewsInfo(){
		
	}

	public BeautyNewsInfo(int beautyNewsId,String beautyNewsName , String beautyNewsImagePath,
			 String beautyNewsURL ){
		this.beautyNewsId = beautyNewsId;
		this.beautyNewsName = beautyNewsName;
		this.beautyNewsImagePath = beautyNewsImagePath;
		this.beautyNewsURL = beautyNewsURL;
	}

	public void setbeautyNewsId(int beautyNewsId){
		this.beautyNewsId = beautyNewsId;
	}
	
	public int getbeautyNewsId(){
		return beautyNewsId;
	}
	public void setbeautyNewsName(String beautyNewsName){
		this.beautyNewsName = beautyNewsName;
	}
	
	public String getbeautyNewsName(){
		return beautyNewsName;
	}	

	public void setbeautyNewsImagePath(String beautyNewsImagePath){
		this.beautyNewsImagePath = beautyNewsImagePath;
	}
	
	public String getbeautyNewsImagePath(){
		return beautyNewsImagePath;
	}	
	
	public void setbeautyNewsURL(String beautyNewsURL){
		this.beautyNewsURL = beautyNewsURL;
	}
	
	public String getbeautyNewsURL(){
		return beautyNewsURL;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
