package common.model;

public class CouponInfo {

	private int couponId = Integer.MIN_VALUE;
	private int salonId = Integer.MIN_VALUE;
	private String couponName = "";
	private String couponDetailText = "";
	private String couponCategoryName = "";
	private int price = Integer.MIN_VALUE;
	private String presentationCondition = "";
	private String deadLine = "";
	private int isFirstFlag = Integer.MIN_VALUE;
	private String useCondition ="";
	private int couponKindId = -1;
	private String imagePath = "";
	
	
	public void setCouponId(int id){
		this.couponId = id;
	}
	
	public int getCouponId(){
		return couponId;
	}
	
	public void setSalonId(int id){
		this.salonId = id;
	}
	
	public int getSalonId(){
		return salonId;
	}

	public void setCouponName(String name){
		this.couponName = name != null ? name : "";
	}
	
	public String getCouponName(){
		return couponName;
	}

	public void setCouponDetailText(String text){
		this.couponDetailText = text != null ? text : "";
	}
	
	public String getCouponDetailText(){
		return couponDetailText;
	}
	
	public void setCouponCategoryName(String name){
		this.couponCategoryName = name != null ? name : "";
	}
	
	public String getCouponCategoryName(){
		return couponCategoryName;
	}	
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setPresentationCondition(String preCondition){
		this.presentationCondition =preCondition != null ? preCondition : "";
	}
	
	public String getPresentationCondition(){
		return presentationCondition;
	}

	public void setDeadLine(String deadLine){
		this.deadLine =deadLine != null ? deadLine : "";
	}
	
	public String getDeadLine(){
		return deadLine;
	}
	public void setIsFirstFlag(int isFirst){
		this.isFirstFlag = isFirst;
	}
	
	public int getIsFirstFlag(){
		return isFirstFlag;
	}

	public void setUseCondition(String useCondition){
		this.useCondition = useCondition != null ? useCondition : "";
	}
	
	public String getUseCondition(){
		return useCondition;
	}

	//for Master
	public void setCouponKindId(int kindId){
		this.couponKindId = kindId;
	}

	public int getCouponKindId() {
		return couponKindId;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath != null ? imagePath : "";
	}
	public String getImagePath() {
		return imagePath;
	}
}
