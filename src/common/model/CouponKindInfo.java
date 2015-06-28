package common.model;

public class CouponKindInfo {
	private int couponKindId = -1;
	private String couponKindName = "";
		
	public void setCouponKindId(int id){
		this.couponKindId = id;
	}
	
	public int getCouponKindId(){
		return couponKindId;
	}
	
	public void setCouponKindName(String name){
		this.couponKindName = name != null ? name : "";
	}
	
	public String getCouponKindName(){
		return couponKindName;
	}

}
