package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TSeatInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tSeatId = 0;

	public int getTSeatId() {
		return tSeatId;
	}
	
	public void setTSeatId (int tSeatId) {
		this.tSeatId = tSeatId;
	}

	private String tSeatName = "";

	public String getTSeatName() {
		return tSeatName;
	}
	
	public void setTSeatName (String tSeatName) {
		this.tSeatName = tSeatName;
	}

	private int tSeatSalonId = 0;

	public int getTSeatSalonId() {
		return tSeatSalonId;
	}
	
	public void setTSeatSalonId (int tSeatSalonId) {
		this.tSeatSalonId = tSeatSalonId;
	}

	private String tSeatMemo = "";

	public String getTSeatMemo() {
		return tSeatMemo;
	}
	
	public void setTSeatMemo (String tSeatMemo) {
		this.tSeatMemo = tSeatMemo;
	}

}
