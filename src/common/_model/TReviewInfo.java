package common._model;

import common.model.BaseInfo;
import common.model.IBaseInfo;

import java.util.Date;

public class TReviewInfo extends BaseInfo implements IBaseInfo {
	
	
	private int tReviewId = 0;

	public int getTReviewId() {
		return tReviewId;
	}
	
	public void setTReviewId (int tReviewId) {
		this.tReviewId = tReviewId;
	}

	private int tReviewUserId = 0;

	public int getTReviewUserId() {
		return tReviewUserId;
	}
	
	public void setTReviewUserId (int tReviewUserId) {
		this.tReviewUserId = tReviewUserId;
	}

	private Date tReviewPostedDate = new Date(0);

	public Date getTReviewPostedDate() {
		return tReviewPostedDate;
	}
	
	public void setTReviewPostedDate (Date tReviewPostedDate) {
		this.tReviewPostedDate = tReviewPostedDate;
	}

	private String tReviewCommentId = "";

	public String getTReviewCommentId() {
		return tReviewCommentId;
	}
	
	public void setTReviewCommentId (String tReviewCommentId) {
		this.tReviewCommentId = tReviewCommentId;
	}

	private String tReviewText = "";

	public String getTReviewText() {
		return tReviewText;
	}
	
	public void setTReviewText (String tReviewText) {
		this.tReviewText = tReviewText;
	}

	private String tReviewEvaluationPoint = "";

	public String getTReviewEvaluationPoint() {
		return tReviewEvaluationPoint;
	}
	
	public void setTReviewEvaluationPoint (String tReviewEvaluationPoint) {
		this.tReviewEvaluationPoint = tReviewEvaluationPoint;
	}

}
