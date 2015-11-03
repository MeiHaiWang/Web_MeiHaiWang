package common.model;

import java.util.Date;

public class ReviewInfo extends BaseInfo implements IBaseInfo{
	private int ReviewId = Integer.MIN_VALUE;
	private int ReviewUserId = Integer.MIN_VALUE;
	private String ReviewText ="";
	private String ReviewCommentId ="";
	private Date ReviewPostedDate = new Date();
	//private Double ReviewPoint = Double.MAX_VALUE;
	private String ReviewPoint = "";
	private String ReviewUserName = "";
	private int ReviewUserGender=-1;
	private Date ReviewUserBirth=new Date();

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

	/*
	public void setReviewPoint(double ReviewPoint){
		this.ReviewPoint = ReviewPoint;
	}
	
	public double getReviewPoint(){
		return ReviewPoint;
	}
	*/
	public void setReviewPoint(String ReviewPoint){
		this.ReviewPoint = ReviewPoint!=null?ReviewPoint:"-1";
	}
	
	public String getReviewPoint(){
		return ReviewPoint;
	}

	public void setReviewUserName(String ReviewUserName) {
		this.ReviewUserName = ReviewUserName!=null?ReviewUserName:"";
	}
	
	public String getReviewUserName(){
		return ReviewUserName;
	}

	public void setReviewUserSex(int sex) {
		this.ReviewUserGender = sex;
	}
	public int getReviewUserSex(){
		return ReviewUserGender;
	}
		
	public void setReviewUserBirth(Date ReviewUserBirth){
		this.ReviewUserBirth = ReviewUserBirth != null ? ReviewUserBirth : new Date(0);
	}
	public Date getReviewUserBirth(){
		return ReviewUserBirth;
	}

}
