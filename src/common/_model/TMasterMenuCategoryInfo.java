package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasterMenuCategoryInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tMenuCategoryCategoryId = 0;

	public int getTMenuCategoryCategoryId() {
		return tMenuCategoryCategoryId;
	}
	
	public void setTMenuCategoryCategoryId (int tMenuCategoryCategoryId) {
		this.tMenuCategoryCategoryId = tMenuCategoryCategoryId;
	}

	private String tMenuCategoryName = "";

	public String getTMenuCategoryName() {
		return tMenuCategoryName;
	}
	
	public void setTMenuCategoryName (String tMenuCategoryName) {
		this.tMenuCategoryName = tMenuCategoryName;
	}

}
