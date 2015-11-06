package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasterRecommendInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tMasterRecommendId = 0;

	public int getTMasterRecommendId() {
		return tMasterRecommendId;
	}
	
	public void setTMasterRecommendId (int tMasterRecommendId) {
		this.tMasterRecommendId = tMasterRecommendId;
	}

	private int tMasterRecommendSalonId = 0;

	public int getTMasterRecommendSalonId() {
		return tMasterRecommendSalonId;
	}
	
	public void setTMasterRecommendSalonId (int tMasterRecommendSalonId) {
		this.tMasterRecommendSalonId = tMasterRecommendSalonId;
	}

	private int tMasterRecommendHairStyleId = 0;

	public int getTMasterRecommendHairStyleId() {
		return tMasterRecommendHairStyleId;
	}
	
	public void setTMasterRecommendHairStyleId (int tMasterRecommendHairStyleId) {
		this.tMasterRecommendHairStyleId = tMasterRecommendHairStyleId;
	}

	private Date tMasterRecommendUpdateDate = new Date(0);

	public Date getTMasterRecommendUpdateDate() {
		return tMasterRecommendUpdateDate;
	}
	
	public void setTMasterRecommendUpdateDate (Date tMasterRecommendUpdateDate) {
		this.tMasterRecommendUpdateDate = tMasterRecommendUpdateDate;
	}

	private int tMasterRecommendStylistId = 0;

	public int getTMasterRecommendStylistId() {
		return tMasterRecommendStylistId;
	}
	
	public void setTMasterRecommendStylistId (int tMasterRecommendStylistId) {
		this.tMasterRecommendStylistId = tMasterRecommendStylistId;
	}

}
