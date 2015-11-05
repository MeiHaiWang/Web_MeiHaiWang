package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TMasternewsInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tMasterNewsId = 0;

	public int getTMasterNewsId() {
		return tMasterNewsId;
	}
	
	public void setTMasterNewsId (int tMasterNewsId) {
		this.tMasterNewsId = tMasterNewsId;
	}

	private String tMasterNewsName = "";

	public String getTMasterNewsName() {
		return tMasterNewsName;
	}
	
	public void setTMasterNewsName (String tMasterNewsName) {
		this.tMasterNewsName = tMasterNewsName;
	}

	private String tMasterNewImagePath = "";

	public String getTMasterNewImagePath() {
		return tMasterNewImagePath;
	}
	
	public void setTMasterNewImagePath (String tMasterNewImagePath) {
		this.tMasterNewImagePath = tMasterNewImagePath;
	}

	private String tMasterNewsURL = "";

	public String getTMasterNewsURL() {
		return tMasterNewsURL;
	}
	
	public void setTMasterNewsURL (String tMasterNewsURL) {
		this.tMasterNewsURL = tMasterNewsURL;
	}

	private Date tMasterNewsUpdateDate = new Date(0);

	public Date getTMasterNewsUpdateDate() {
		return tMasterNewsUpdateDate;
	}
	
	public void setTMasterNewsUpdateDate (Date tMasterNewsUpdateDate) {
		this.tMasterNewsUpdateDate = tMasterNewsUpdateDate;
	}

}
