package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMenuInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tMenuMenuId = 0;

	public int getTMenuMenuId() {
		return tMenuMenuId;
	}
	
	public void setTMenuMenuId (int tMenuMenuId) {
		this.tMenuMenuId = tMenuMenuId;
	}

	private String tMenuName = "";

	public String getTMenuName() {
		return tMenuName;
	}
	
	public void setTMenuName (String tMenuName) {
		this.tMenuName = tMenuName;
	}

	private int tMenuPrice = 0;

	public int getTMenuPrice() {
		return tMenuPrice;
	}
	
	public void setTMenuPrice (int tMenuPrice) {
		this.tMenuPrice = tMenuPrice;
	}

	private int tMenuCategoryId = 0;

	public int getTMenuCategoryId() {
		return tMenuCategoryId;
	}
	
	public void setTMenuCategoryId (int tMenuCategoryId) {
		this.tMenuCategoryId = tMenuCategoryId;
	}

	private String tMenuDetailText = "";

	public String getTMenuDetailText() {
		return tMenuDetailText;
	}
	
	public void setTMenuDetailText (String tMenuDetailText) {
		this.tMenuDetailText = tMenuDetailText;
	}

	private String tMenuImagePath = "";

	public String getTMenuImagePath() {
		return tMenuImagePath;
	}
	
	public void setTMenuImagePath (String tMenuImagePath) {
		this.tMenuImagePath = tMenuImagePath;
	}

	private String tMenuTime = "";

	public String getTMenuTime() {
		return tMenuTime;
	}
	
	public void setTMenuTime (String tMenuTime) {
		this.tMenuTime = tMenuTime;
	}

}
