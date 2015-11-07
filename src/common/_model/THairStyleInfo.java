package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class THairStyleInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tHairStyleId = 0;

	public int getTHairStyleId() {
		return tHairStyleId;
	}
	
	public void setTHairStyleId (int tHairStyleId) {
		this.tHairStyleId = tHairStyleId;
	}

	private String tHairStyleName = "";

	public String getTHairStyleName() {
		return tHairStyleName;
	}
	
	public void setTHairStyleName (String tHairStyleName) {
		this.tHairStyleName = tHairStyleName;
	}

	private int tHairStyleHairTypeId = 0;

	public int getTHairStyleHairTypeId() {
		return tHairStyleHairTypeId;
	}
	
	public void setTHairStyleHairTypeId (int tHairStyleHairTypeId) {
		this.tHairStyleHairTypeId = tHairStyleHairTypeId;
	}

	private int tHairStyleGoodNumber = 0;

	public int getTHairStyleGoodNumber() {
		return tHairStyleGoodNumber;
	}
	
	public void setTHairStyleGoodNumber (int tHairStyleGoodNumber) {
		this.tHairStyleGoodNumber = tHairStyleGoodNumber;
	}

	private int tHairStyleViewNumber = 0;

	public int getTHairStyleViewNumber() {
		return tHairStyleViewNumber;
	}
	
	public void setTHairStyleViewNumber (int tHairStyleViewNumber) {
		this.tHairStyleViewNumber = tHairStyleViewNumber;
	}

	private int tHairStyleStylistId = 0;

	public int getTHairStyleStylistId() {
		return tHairStyleStylistId;
	}
	
	public void setTHairStyleStylistId (int tHairStyleStylistId) {
		this.tHairStyleStylistId = tHairStyleStylistId;
	}

	private String tHairStyleAreaId = "";

	public String getTHairStyleAreaId() {
		return tHairStyleAreaId;
	}
	
	public void setTHairStyleAreaId (String tHairStyleAreaId) {
		this.tHairStyleAreaId = tHairStyleAreaId;
	}

	private String tHairStyleImagePath = "";

	public String getTHairStyleImagePath() {
		return tHairStyleImagePath;
	}
	
	public void setTHairStyleImagePath (String tHairStyleImagePath) {
		this.tHairStyleImagePath = tHairStyleImagePath;
	}

	private int tHairStyleSalonId = 0;

	public int getTHairStyleSalonId() {
		return tHairStyleSalonId;
	}
	
	public void setTHairStyleSalonId (int tHairStyleSalonId) {
		this.tHairStyleSalonId = tHairStyleSalonId;
	}

	private Date tHairStyleUpdateDate = new Date(0);

	public Date getTHairStyleUpdateDate() {
		return tHairStyleUpdateDate;
	}
	
	public void setTHairStyleUpdateDate (Date tHairStyleUpdateDate) {
		this.tHairStyleUpdateDate = tHairStyleUpdateDate;
	}

	private int tHairStyleFavoriteNumber = 0;

	public int getTHairStyleFavoriteNumber() {
		return tHairStyleFavoriteNumber;
	}
	
	public void setTHairStyleFavoriteNumber (int tHairStyleFavoriteNumber) {
		this.tHairStyleFavoriteNumber = tHairStyleFavoriteNumber;
	}

	private String tHairStyleSearchConditionId = "";

	public String getTHairStyleSearchConditionId() {
		return tHairStyleSearchConditionId;
	}
	
	public void setTHairStyleSearchConditionId (String tHairStyleSearchConditionId) {
		this.tHairStyleSearchConditionId = tHairStyleSearchConditionId;
	}

	private String tHairStyleMessage = "";

	public String getTHairStyleMessage() {
		return tHairStyleMessage;
	}
	
	public void setTHairStyleMessage (String tHairStyleMessage) {
		this.tHairStyleMessage = tHairStyleMessage;
	}

}
