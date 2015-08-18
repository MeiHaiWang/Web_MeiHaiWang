package common.model;

import java.util.Date;

public class UserInfo {
	private int UserId = -1;
	private String UserMail = "default";
	private String UserPass = "0000";
	private String UserCookie = "";
	private String UserImagePath="";
	private int UserSex = 0;
	private Date UserBirth = new Date(0);
	private String UserName ="";
	private String UserFavoriteSalonId = "";
	private String UserFavoriteStylistId = "";
	private String UserLatestViewStylistId = "";
	private String UserFavoriteHairStyleId = "";
	private String UserLatestViewHairStyleId = "";
	private Integer UserPoint = 0;
	private Integer UserIsStylist = -1;
	private Integer UserMasterSalonId = -1;
	private String UserPhoneNumber = "";

	public UserInfo(){
		
	}

	public void setUserMail(String mail){
		this.UserMail = mail != null ? mail : "";
	}
	
	public String getUserMail(){
		return UserMail;
	}

	public void setUserPass(String userPass){
		this.UserPass = userPass != null ? userPass : "";
	}
	
	public String getUserPass(){
		return UserPass;
	}

	public void setUserPhoneNumber(String userPhoneNumber){
		this.UserPhoneNumber = userPhoneNumber != null ? userPhoneNumber : "";
	}
	
	public String getUserPhoneNumber(){
		return UserPhoneNumber;
	}

	public void setUserId(int userId){
		this.UserId = userId;
	}
	
	public int getUserId(){
		return UserId;
	}
	public void setUserName(String UserName){
		this.UserName = UserName != null ? UserName : "";
	}
	
	public String getUserName(){
		return UserName;
	}

	public void setUserImagePath(String UserImagePath){
		this.UserImagePath = UserImagePath != null ? UserImagePath : "";
	}
	
	public String getUserImagePath(){
		return UserImagePath;
	}
	
	public void setUserSex(int sex){
		this.UserSex = sex;
	}
	
	public int getUserSex(){
		return UserSex;
	}

	public void setUserBirth(Date userBirth){
		this.UserBirth = userBirth != null ? userBirth : new Date(0);
	}
	
	public Date getUserBirth(){
		return UserBirth;
	}

	public void setUserPoint(int point){
		this.UserPoint = point;
	}
	
	public int getUserPoint(){
		return UserPoint;
	}

	public void setUserIsStylist(int flag){
		this.UserIsStylist = flag;
	}
	
	public int getUserIsStylist(){
		return UserIsStylist;
	}

	public void setUserMasterSalonId(int salonId){
		this.UserMasterSalonId = salonId;
	}
	
	public int getUserMasterSalonId(){
		return UserMasterSalonId;
	}
	
}
