package common.model;

import java.util.Date;

public class ClaimInfo {
	/**
	 *    t_claim_reservationId,
		   t_claim_userId,
		　	t_claim_salonId,
		　	t_claim_menuId,
		   t_claim_date,
		　	t_claim_message
	 */
	private int ClaimId = Integer.MIN_VALUE;
	private int ClaimUserId = Integer.MIN_VALUE;
	private String ClaimMessage="";
	private String ClaimUserName="";
	private int ClaimMenuId = Integer.MIN_VALUE;
//	private Date ClaimDate = new Date();
	private String ClaimDate = "";
	private int ClaimSalonId;
	private int ClaimReservationId;

	public ClaimInfo(){
	}
	
	public void setClaimId(int ClaimId){
		this.ClaimId = ClaimId;
	}	
	public int getClaimId(){
		return ClaimId;
	}
	
	public void setClaimUserId(int ClaimUserId){
		this.ClaimUserId = ClaimUserId;
	}
	public int getClaimUserId(){
		return ClaimUserId;
	}

	public void setClaimUserName(String userName){
		this.ClaimUserName = userName != null ? userName : "";
	}
	public String getClaimUserName(){
		return ClaimUserName;
	}


	public void setClaimMessage(String ClaimText){
		this.ClaimMessage = ClaimText != null ? ClaimText : "";
	}
	public String getClaimMessage(){
		return ClaimMessage;
	}

	public void setClaimReservationId(int ClaimReservationId){
		this.ClaimReservationId = ClaimReservationId;
	}
	public int getClaimReservationId(){
		return ClaimReservationId;
	}

	public void setClaimMenuId(int ClaimMenuId){
		this.ClaimMenuId = ClaimMenuId;
	}
	public int getClaimMenuId(){
		return ClaimMenuId;
	}

	public void setClaimSalonId(int ClaimSalonId){
		this.ClaimSalonId = ClaimSalonId;
	}
	public int getClaimSalonId(){
		return ClaimSalonId;
	}

	public void setClaimDate(String ClaimDate){
		this.ClaimDate = ClaimDate != null ? ClaimDate : null;
	}
	public String getClaimDate(){
		return ClaimDate;
	}
	/*
	public void setClaimDate(Date ClaimDate){
		this.ClaimDate = ClaimDate != null ? ClaimDate : new Date(0);
	}
	public Date getClaimDate(){
		return ClaimDate;
	}
	*/

}
