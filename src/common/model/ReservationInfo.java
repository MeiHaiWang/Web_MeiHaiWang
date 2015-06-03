package common.model;

public class ReservationInfo {
	//未完成
	private int ReservationId = Integer.MIN_VALUE;
	private String ReservationName ="";
	private int ReservationLevel = Integer.MIN_VALUE;
	private int ReservationCountryId = Integer.MIN_VALUE;
	private int isDetailFlag = Integer.MIN_VALUE;
	
	public ReservationInfo(){
	}
	
	public void setReservationId(int ReservationId){
		this.ReservationId = ReservationId;
	}
	
	public int getReservationId(){
		return ReservationId;
	}

	public void setReservationName(String ReservationName){
		this.ReservationName = ReservationName != null ? ReservationName : "";
	}
	
	public String getReservationName(){
		return ReservationName;
	}

	public void setReservationLevel(int ReservationLevel){
		this.ReservationLevel = ReservationLevel;
	}
	
	public int getReservationLevel(){
		return ReservationLevel;
	}
		
	public void setReservationCountryId(int ReservationCountryId){
		this.ReservationCountryId = ReservationCountryId;
	}
	
	public int getReservationCountryId(){
		return ReservationCountryId;
	}

	public void setisDetailFlag(int isDetailFlag){
		this.isDetailFlag = isDetailFlag;
	}
	
	public int getisDetailFlag(){
		return isDetailFlag;
	}

}
