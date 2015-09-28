package common.model;

import java.util.Date;

public class ReservationInfo {
	/*
	 * 　 userId,
		　 salonId,
		　 stylistId,
		　 date,
		　 menuId,
		　 seatId,
			memo　
	 */
	
	private int ReservationId = Integer.MIN_VALUE;
	private int ReservationUserId = Integer.MIN_VALUE;
	private int ReservationSalonId = Integer.MIN_VALUE;
	private int ReservationStylistId = Integer.MIN_VALUE;
//	private Date ReservationDate = new Date(0);
	private String ReservationDate = "";
	private int isFinished = Integer.MIN_VALUE;
	//private int isCanceled = Integer.MIN_VALUE;
	private String ReservationSalonName = "";
	private String ReservationStylistName = "";
	private String ReservationMenuId = "";
	private int ReservationSeatId;
	private String ReservationMemo;
	private String ReservationUserName;
	private String ReservationUserSex;
	private String ReservationMenuName;
	private String ReservationSeatName;
	private String ReservationUserTel;
	private int ReservationTime;
	
	
	public ReservationInfo(){
	}
	
	public void setReservationId(int ReservationId){
		this.ReservationId = ReservationId;
	}
	
	public int getReservationId(){
		return ReservationId;
	}

	public void setReservationUserId(Integer userId){
		this.ReservationUserId = userId;
	}
	
	public int getReservationUserId(){
		return ReservationUserId;
	}

	public void setReservationStylistId(int stylistId){
		this.ReservationStylistId = stylistId;
	}
	
	public int getReservationStylistId(){
		return ReservationStylistId;
	}
	public void setReservationSalonId(int salonId){
		this.ReservationSalonId = salonId;
	}
		
	public int getReservationSalonId(){
		return ReservationSalonId;
	}

	/*
	public void setReservationDate(Date ReserveDate){
		this.ReservationDate = ReserveDate != null ? ReserveDate : new Date(0);
	}
	public Date getReservationDate(){
		return ReservationDate;
	}
	*/
	public void setReservationDate(String ReserveDate){
		this.ReservationDate = ReserveDate != null ? ReserveDate : null;
	}
	public String getReservationDate(){
		return ReservationDate;
	}
	
	public void setisFinished(int isFinished){
		this.isFinished = isFinished;
	}
	
	public int getisFinished(){
		return isFinished;
	}

	/*
	public void setisCanceled(int isCanceled){
		this.isCanceled = isCanceled;
	}
	
	public int getisCanceled(){
		return isCanceled;
	}
	*/

	//tuika
	public void setReservationSalonName(String salonName){
		this.ReservationSalonName = salonName != null ? salonName : "";
	}
	
	public String getReservationSalonName(){
		return ReservationSalonName;
	}

	public void setReservationStylistName(String stylistName){
		this.ReservationStylistName = stylistName != null ? stylistName : "";
	}
	public String getReservationStylistName(){
		return ReservationStylistName;
	}
	
	public void setReservationMenuId(String menuId){
		this.ReservationMenuId = menuId != null ? menuId : "";
	}
	public String getReservationMenuId(){
		return ReservationMenuId;
	}

	public void setReservationSeatId(int seatId){
		this.ReservationSeatId = seatId;
	}
	public int getReservationSeatId(){
		return ReservationSeatId;
	}
	
	public void setReservationMemo(String memo){
		this.ReservationMemo = memo != null ? memo : "";
	}
	public String getReservationMemo(){
		return ReservationMemo;
	}
	
	public void setReservationUserName(String userName){
		this.ReservationUserName = userName != null ? userName : "";
	}
	public String getReservationUserName(){
		return ReservationUserName;
	}

	public void setReservationUserSex(String userSex){
		this.ReservationUserSex = userSex != null ? userSex : "";
	}
	public String getReservationUserSex(){
		return ReservationUserSex;
	}

	public void setReservationMenuName(String menuName){
		this.ReservationMenuName = menuName != null ? menuName : "";
	}
	public String getReservationMenuName(){
		return ReservationMenuName;
	}

	public void setReservationSeatName(String seatName){
		this.ReservationSeatName = seatName != null ? seatName : "";
	}
	public String getReservationSeatName(){
		return ReservationSeatName;
	}

	public void setReservationUserTel(String userTel){
		this.ReservationUserTel = userTel != null ? userTel : "";
	}
	public String getReservationUserTel() {
		return ReservationUserTel;
	}

	public void setReservationTime(int time){
		this.ReservationTime = time;
	}
	public int getReservationTime() {
		return ReservationTime;
	}
	
}





