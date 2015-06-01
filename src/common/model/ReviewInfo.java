package common.model;

import java.util.Date;

public class ReviewInfo {
	private int ReviewId = Integer.MIN_VALUE;
	private int ReviewUserId = Integer.MIN_VALUE;
	private String ReviewText ="";
	private String ReviewCommentId ="";
	private Date ReviewPostedDate = new Date();

	public ReviewInfo(){
	}
	
	public void setReviewId(int ReviewId){
		this.ReviewId = ReviewId;
	}
	
	public int getReviewId(){
		return ReviewId;
	}
	
	public void setReviewUserId(int ReviewUserId){
		this.ReviewUserId = ReviewUserId;
	}
	
	public int getReviewUserId(){
		return ReviewUserId;
	}

	public void setReviewText(String ReviewText){
		this.ReviewText = ReviewText != null ? ReviewText : "";
	}
	
	public String getReviewText(){
		return ReviewText;
	}

	public void setReviewCommentId(String ReviewCommentId){
		this.ReviewCommentId = ReviewCommentId != null ? ReviewCommentId : "";
	}
	
	public String getReviewCommentId(){
		return ReviewCommentId;
	}

	public void setReviewPostedDate(Date ReviewPostedDate){
		this.ReviewPostedDate = ReviewPostedDate != null ? ReviewPostedDate : new Date(0);
	}
	
	public Date getReviewPostedDate(){
		return ReviewPostedDate;
	}

	
	
}
