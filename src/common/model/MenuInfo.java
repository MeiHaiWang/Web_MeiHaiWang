package common.model;

public class MenuInfo extends BaseInfo implements IBaseInfo{
	private int MenuId = Integer.MIN_VALUE;
	private String MenuName ="";
	private int MenuPrice = 0;
	private int MenuCategoryId = 0;
	private String MenuDetailText ="";
	private String MenuImagePath ="";
	private String menuTime;

	public MenuInfo(){
		
	}
	
	public void setMenuId(int MenuId){
		this.MenuId = MenuId;
	}
	
	public int getMenuId(){
		return MenuId;
	}
	public void setMenuName(String MenuName){
		this.MenuName = MenuName != null ? MenuName : "";
	}
	
	public String getMenuName(){
		return MenuName;
	}

	public void setMenuImagePath(String MenuImagePath){
		this.MenuImagePath = MenuImagePath != null ? MenuImagePath : "";
	}
	
	public String getMenuImagePath(){
		return MenuImagePath;
	}
	
	public void setMenuPrice(int Price){
		this.MenuPrice = Price;
	}
	
	public int getMenuPrice(){
		return MenuPrice;
	}

	public int getMenuCategoryId(){
		return MenuCategoryId;
	}

	public void setMenuCategoryId(int id){
		this.MenuCategoryId = id;
	}
	
	public void setMenuDetailText(String detail){
		this.MenuDetailText = detail != null ? detail : "";
	}
	
	public String getMenuDetailText(){
		return MenuDetailText;
	}

	public void setMenuTime(String time){
		this.menuTime = time != null ? time : "";
	}
	public String getMenuTime() {
		return menuTime;
	}
}
