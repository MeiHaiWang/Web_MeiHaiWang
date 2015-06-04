package common.model;

import java.util.Date;

public class ReservationInfo {
	//未完成
	private int ReservationId = Integer.MIN_VALUE;
	private int ReservationUserId = Integer.MIN_VALUE;
	private int ReservationSalonId = Integer.MIN_VALUE;
	private int ReservationStylistId = Integer.MIN_VALUE;
	private Date ReservationDate = new Date(0);
	private int isFinished = Integer.MIN_VALUE;
	private int isCanceled = Integer.MIN_VALUE;
	private String ReservationSalonName = "";
	private String ReservationStylistName = "";
	
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
		this.ReservationSalonId = stylistId;
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

	public void setReservationDate(Date ReserveDate){
		this.ReservationDate = ReserveDate != null ? ReserveDate : new Date(0);
	}
	
	public Date getReservationDate(){
		return ReservationDate;
	}

	
	public void setisFinished(int isFinished){
		this.isFinished = isFinished;
	}
	
	public int getisFinished(){
		return isFinished;
	}
	
	public void setisCanceled(int isCanceled){
		this.isCanceled = isCanceled;
	}
	
	public int getisCanceled(){
		return isCanceled;
	}

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

	
}





