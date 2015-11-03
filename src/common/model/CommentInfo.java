package common.model;

import java.util.Date;

public class CommentInfo extends BaseInfo implements IBaseInfo{
	private int CommentId = Integer.MIN_VALUE;
	private int CommentUserId = Integer.MIN_VALUE;
	private String CommentMessage="";
	private String CommentUserName="";
	private int CommentReviewId = Integer.MIN_VALUE;
	private Date CommentDate = new Date();

	public CommentInfo(){
	}
	
	public void setCommentId(int CommentId){
		this.CommentId = CommentId;
	}
	
	public int getCommentId(){
		return CommentId;
	}
	
	public void setCommentUserId(int CommentUserId){
		this.CommentUserId = CommentUserId;
	}
	
	public int getCommentUserId(){
		return CommentUserId;
	}

	public void setCommentUserName(String userName){
		this.CommentUserName = userName != null ? userName : "";
	}
	
	public String getCommentUserName(){
		return CommentUserName;
	}


	public void setCommentMessage(String CommentText){
		this.CommentMessage = CommentText != null ? CommentText : "";
	}
	
	public String getCommentMessage(){
		return CommentMessage;
	}

	public void setCommentReviewId(int CommentReviewId){
		this.CommentReviewId = CommentReviewId;
	}
	
	public int getCommentReviewId(){
		return CommentReviewId;
	}

	public void setCommentDate(Date CommentDate){
		this.CommentDate = CommentDate != null ? CommentDate : new Date(0);
	}
	
	public Date getCommentDate(){
		return CommentDate;
	}

}
