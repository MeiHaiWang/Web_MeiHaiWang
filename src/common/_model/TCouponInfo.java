package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TCouponInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tCouponId = 0;

	public int getTCouponId() {
		return tCouponId;
	}
	
	public void setTCouponId (int tCouponId) {
		this.tCouponId = tCouponId;
	}

	private String tCouponName = "";

	public String getTCouponName() {
		return tCouponName;
	}
	
	public void setTCouponName (String tCouponName) {
		this.tCouponName = tCouponName;
	}

	private int tCouponCouponKindId = 0;

	public int getTCouponCouponKindId() {
		return tCouponCouponKindId;
	}
	
	public void setTCouponCouponKindId (int tCouponCouponKindId) {
		this.tCouponCouponKindId = tCouponCouponKindId;
	}

	private String tCouponDetailText = "";

	public String getTCouponDetailText() {
		return tCouponDetailText;
	}
	
	public void setTCouponDetailText (String tCouponDetailText) {
		this.tCouponDetailText = tCouponDetailText;
	}

	private String tCouponUseCondition = "";

	public String getTCouponUseCondition() {
		return tCouponUseCondition;
	}
	
	public void setTCouponUseCondition (String tCouponUseCondition) {
		this.tCouponUseCondition = tCouponUseCondition;
	}

	private String tCouponImagePath = "";

	public String getTCouponImagePath() {
		return tCouponImagePath;
	}
	
	public void setTCouponImagePath (String tCouponImagePath) {
		this.tCouponImagePath = tCouponImagePath;
	}

	private int tCouponPrice = 0;

	public int getTCouponPrice() {
		return tCouponPrice;
	}
	
	public void setTCouponPrice (int tCouponPrice) {
		this.tCouponPrice = tCouponPrice;
	}

	private String tCouponDeadLine = "";

	public String getTCouponDeadLine() {
		return tCouponDeadLine;
	}
	
	public void setTCouponDeadLine (String tCouponDeadLine) {
		this.tCouponDeadLine = tCouponDeadLine;
	}

	private int tCouponIsFirstFlag = 0;

	public int getTCouponIsFirstFlag() {
		return tCouponIsFirstFlag;
	}
	
	public void setTCouponIsFirstFlag (int tCouponIsFirstFlag) {
		this.tCouponIsFirstFlag = tCouponIsFirstFlag;
	}

	private String tCouponPresentationCondition = "";

	public String getTCouponPresentationCondition() {
		return tCouponPresentationCondition;
	}
	
	public void setTCouponPresentationCondition (String tCouponPresentationCondition) {
		this.tCouponPresentationCondition = tCouponPresentationCondition;
	}

}
