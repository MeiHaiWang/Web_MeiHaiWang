package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TReservationInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tReservationId = 0;

	public int getTReservationId() {
		return tReservationId;
	}
	
	public void setTReservationId (int tReservationId) {
		this.tReservationId = tReservationId;
	}

	private int tReservationUserId = 0;

	public int getTReservationUserId() {
		return tReservationUserId;
	}
	
	public void setTReservationUserId (int tReservationUserId) {
		this.tReservationUserId = tReservationUserId;
	}

	private int tReservationSalonId = 0;

	public int getTReservationSalonId() {
		return tReservationSalonId;
	}
	
	public void setTReservationSalonId (int tReservationSalonId) {
		this.tReservationSalonId = tReservationSalonId;
	}

	private int tReservationStylistId = 0;

	public int getTReservationStylistId() {
		return tReservationStylistId;
	}
	
	public void setTReservationStylistId (int tReservationStylistId) {
		this.tReservationStylistId = tReservationStylistId;
	}

	private Date tReservationDate = new Date(0);

	public Date getTReservationDate() {
		return tReservationDate;
	}
	
	public void setTReservationDate (Date tReservationDate) {
		this.tReservationDate = tReservationDate;
	}

	private int tReservationIsFinished = 0;

	public int getTReservationIsFinished() {
		return tReservationIsFinished;
	}
	
	public void setTReservationIsFinished (int tReservationIsFinished) {
		this.tReservationIsFinished = tReservationIsFinished;
	}

	private String tReservationMenuId = "";

	public String getTReservationMenuId() {
		return tReservationMenuId;
	}
	
	public void setTReservationMenuId (String tReservationMenuId) {
		this.tReservationMenuId = tReservationMenuId;
	}

	private int tReservationSeatId = 0;

	public int getTReservationSeatId() {
		return tReservationSeatId;
	}
	
	public void setTReservationSeatId (int tReservationSeatId) {
		this.tReservationSeatId = tReservationSeatId;
	}

	private String tReservationMemo = "";

	public String getTReservationMemo() {
		return tReservationMemo;
	}
	
	public void setTReservationMemo (String tReservationMemo) {
		this.tReservationMemo = tReservationMemo;
	}

	private int tReservationAppoint = 0;

	public int getTReservationAppoint() {
		return tReservationAppoint;
	}
	
	public void setTReservationAppoint (int tReservationAppoint) {
		this.tReservationAppoint = tReservationAppoint;
	}

}
