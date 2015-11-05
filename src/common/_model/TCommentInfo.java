package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TCommentInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tCommentId = 0;

	public int getTCommentId() {
		return tCommentId;
	}
	
	public void setTCommentId (int tCommentId) {
		this.tCommentId = tCommentId;
	}

	private int tCommentUserId = 0;

	public int getTCommentUserId() {
		return tCommentUserId;
	}
	
	public void setTCommentUserId (int tCommentUserId) {
		this.tCommentUserId = tCommentUserId;
	}

	private String tCommentMessage = "";

	public String getTCommentMessage() {
		return tCommentMessage;
	}
	
	public void setTCommentMessage (String tCommentMessage) {
		this.tCommentMessage = tCommentMessage;
	}

	private int tCommentReviewId = 0;

	public int getTCommentReviewId() {
		return tCommentReviewId;
	}
	
	public void setTCommentReviewId (int tCommentReviewId) {
		this.tCommentReviewId = tCommentReviewId;
	}

	private Date tCommentDate = new Date(0);

	public Date getTCommentDate() {
		return tCommentDate;
	}
	
	public void setTCommentDate (Date tCommentDate) {
		this.tCommentDate = tCommentDate;
	}

}
