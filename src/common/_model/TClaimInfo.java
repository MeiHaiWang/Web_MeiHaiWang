package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TClaimInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tClaimId = 0;

	public int getTClaimId() {
		return tClaimId;
	}
	
	public void setTClaimId (int tClaimId) {
		this.tClaimId = tClaimId;
	}

	private int tClaimUserId = 0;

	public int getTClaimUserId() {
		return tClaimUserId;
	}
	
	public void setTClaimUserId (int tClaimUserId) {
		this.tClaimUserId = tClaimUserId;
	}

	private int tClaimSalonId = 0;

	public int getTClaimSalonId() {
		return tClaimSalonId;
	}
	
	public void setTClaimSalonId (int tClaimSalonId) {
		this.tClaimSalonId = tClaimSalonId;
	}

	private int tClaimReserveId = 0;

	public int getTClaimReserveId() {
		return tClaimReserveId;
	}
	
	public void setTClaimReserveId (int tClaimReserveId) {
		this.tClaimReserveId = tClaimReserveId;
	}

	private int tClaimMenuId = 0;

	public int getTClaimMenuId() {
		return tClaimMenuId;
	}
	
	public void setTClaimMenuId (int tClaimMenuId) {
		this.tClaimMenuId = tClaimMenuId;
	}

	private String tClaimMessage = "";

	public String getTClaimMessage() {
		return tClaimMessage;
	}
	
	public void setTClaimMessage (String tClaimMessage) {
		this.tClaimMessage = tClaimMessage;
	}

	private String tClaimDate = "";

	public String getTClaimDate() {
		return tClaimDate;
	}
	
	public void setTClaimDate (String tClaimDate) {
		this.tClaimDate = tClaimDate;
	}

}
