package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMastercountryInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tCountryCountryId = 0;

	public int getTCountryCountryId() {
		return tCountryCountryId;
	}
	
	public void setTCountryCountryId (int tCountryCountryId) {
		this.tCountryCountryId = tCountryCountryId;
	}

	private String tCountryCountryName = "";

	public String getTCountryCountryName() {
		return tCountryCountryName;
	}
	
	public void setTCountryCountryName (String tCountryCountryName) {
		this.tCountryCountryName = tCountryCountryName;
	}

}
