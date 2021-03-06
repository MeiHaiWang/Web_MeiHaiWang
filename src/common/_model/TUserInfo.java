package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TUserInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tUserId = 0;

	public int getTUserId() {
		return tUserId;
	}
	
	public void setTUserId (int tUserId) {
		this.tUserId = tUserId;
	}

	private int tUserDisableFlag = 0;

	public int getTUserDisableFlag() {
		return tUserDisableFlag;
	}
	
	public void setTUserDisableFlag (int tUserDisableFlag) {
		this.tUserDisableFlag = tUserDisableFlag;
	}

	private String tUserMail = "";

	public String getTUserMail() {
		return tUserMail;
	}
	
	public void setTUserMail (String tUserMail) {
		this.tUserMail = tUserMail;
	}

	private String tUserPassward = "";

	public String getTUserPassward() {
		return tUserPassward;
	}
	
	public void setTUserPassward (String tUserPassward) {
		this.tUserPassward = tUserPassward;
	}

	private String tUserCookie = "";

	public String getTUserCookie() {
		return tUserCookie;
	}
	
	public void setTUserCookie (String tUserCookie) {
		this.tUserCookie = tUserCookie;
	}

	private String tUserImagePath = "";

	public String getTUserImagePath() {
		return tUserImagePath;
	}
	
	public void setTUserImagePath (String tUserImagePath) {
		this.tUserImagePath = tUserImagePath;
	}

	private int tUserSex = 0;

	public int getTUserSex() {
		return tUserSex;
	}
	
	public void setTUserSex (int tUserSex) {
		this.tUserSex = tUserSex;
	}

	private Date tUserBirth = new Date(0);

	public Date getTUserBirth() {
		return tUserBirth;
	}
	
	public void setTUserBirth (Date tUserBirth) {
		this.tUserBirth = tUserBirth;
	}

	private String tUserName = "";

	public String getTUserName() {
		return tUserName;
	}
	
	public void setTUserName (String tUserName) {
		this.tUserName = tUserName;
	}

	private String tUserFavoriteSalonId = "";

	public String getTUserFavoriteSalonId() {
		return tUserFavoriteSalonId;
	}
	
	public void setTUserFavoriteSalonId (String tUserFavoriteSalonId) {
		this.tUserFavoriteSalonId = tUserFavoriteSalonId;
	}

	private String tUserFavoriteStylistId = "";

	public String getTUserFavoriteStylistId() {
		return tUserFavoriteStylistId;
	}
	
	public void setTUserFavoriteStylistId (String tUserFavoriteStylistId) {
		this.tUserFavoriteStylistId = tUserFavoriteStylistId;
	}

	private String tUserFavoriteHairStyleId = "";

	public String getTUserFavoriteHairStyleId() {
		return tUserFavoriteHairStyleId;
	}
	
	public void setTUserFavoriteHairStyleId (String tUserFavoriteHairStyleId) {
		this.tUserFavoriteHairStyleId = tUserFavoriteHairStyleId;
	}

	private String tUserLatestViewSalonId = "";

	public String getTUserLatestViewSalonId() {
		return tUserLatestViewSalonId;
	}
	
	public void setTUserLatestViewSalonId (String tUserLatestViewSalonId) {
		this.tUserLatestViewSalonId = tUserLatestViewSalonId;
	}

	private String tUserLatestViewStylistId = "";

	public String getTUserLatestViewStylistId() {
		return tUserLatestViewStylistId;
	}
	
	public void setTUserLatestViewStylistId (String tUserLatestViewStylistId) {
		this.tUserLatestViewStylistId = tUserLatestViewStylistId;
	}

	private String tUserLatestViewHairStyleId = "";

	public String getTUserLatestViewHairStyleId() {
		return tUserLatestViewHairStyleId;
	}
	
	public void setTUserLatestViewHairStyleId (String tUserLatestViewHairStyleId) {
		this.tUserLatestViewHairStyleId = tUserLatestViewHairStyleId;
	}

	private int tUserPoint = 0;

	public int getTUserPoint() {
		return tUserPoint;
	}
	
	public void setTUserPoint (int tUserPoint) {
		this.tUserPoint = tUserPoint;
	}

	private int tUserHistorySalonId = 0;

	public int getTUserHistorySalonId() {
		return tUserHistorySalonId;
	}
	
	public void setTUserHistorySalonId (int tUserHistorySalonId) {
		this.tUserHistorySalonId = tUserHistorySalonId;
	}

	private int tUserMasterSalonId = 0;

	public int getTUserMasterSalonId() {
		return tUserMasterSalonId;
	}
	
	public void setTUserMasterSalonId (int tUserMasterSalonId) {
		this.tUserMasterSalonId = tUserMasterSalonId;
	}

	private String tUserTel = "";

	public String getTUserTel() {
		return tUserTel;
	}
	
	public void setTUserTel (String tUserTel) {
		this.tUserTel = tUserTel;
	}

	private String tUserReservationId = "";

	public String getTUserReservationId() {
		return tUserReservationId;
	}
	
	public void setTUserReservationId (String tUserReservationId) {
		this.tUserReservationId = tUserReservationId;
	}
	
	
	
	//append
	private int UserAge= 0;
	private String LatestCutStylist = "";
	private String LatestCutMemo;
	private int SalonTraffic; //来店回数
	private double TotalPayment;
	private String previousDate="";
	private String nextDate ="";
	private int isStylist = 0;

	public void setLatestCutStylist(String stylistName){
		this.LatestCutStylist = stylistName != null ? stylistName : "";
	}
	public String getLatestCutStylist(){
		return LatestCutStylist;
	}
	
	public void setLatestCutMemo(String memo){
		this.LatestCutMemo = memo != null ? memo : "";
	}
	public String getLatestCutMemo(){
		return LatestCutMemo;
	}

	public void setSalonTraffic(int salonTraffic) {
		this.SalonTraffic = salonTraffic;
	}
	public int getSalonTraffic(){
		return SalonTraffic;
	}

	public void setTotalPayment(double totalPayment2) {
		this.TotalPayment = totalPayment2;
	}
	public double getTotalPayment(){
		return TotalPayment;
	}

	public void setUserAge(int userAge){
		this.UserAge = userAge;
	}
	
	public int getUserAge(){
		return UserAge;
	}

	public void setReservationPreviousDate(String previousDate) {
		this.previousDate = previousDate != null ? previousDate : "";
	}
	public String getReservationPreviousDate(){
		return previousDate;
	}

	public void setReservationNextDate(String nextDate) {
		this.nextDate = nextDate != null ? nextDate : "";
	}
	public String getReservationNextDate(){
		return nextDate ;
	}
	
	public void setUserIsStylist(int flag){
		this.isStylist = flag;
	}
	
	public int getUserIsStylist(){
		return isStylist;
	}



}
