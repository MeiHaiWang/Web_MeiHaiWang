package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasterCouponKindInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tCouponKindId = 0;

	public int getTCouponKindId() {
		return tCouponKindId;
	}
	
	public void setTCouponKindId (int tCouponKindId) {
		this.tCouponKindId = tCouponKindId;
	}

	private String tCouponKindName = "";

	public String getTCouponKindName() {
		return tCouponKindName;
	}
	
	public void setTCouponKindName (String tCouponKindName) {
		this.tCouponKindName = tCouponKindName;
	}

}
