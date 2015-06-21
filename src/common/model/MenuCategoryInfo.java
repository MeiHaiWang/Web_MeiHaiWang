package common.model;

public class MenuCategoryInfo {
	private int MenuCategoryId = Integer.MIN_VALUE;
	private String MenuCategoryName ="";

	public MenuCategoryInfo(){
		
	}
	
	public void setMenuCategoryId(int MenuCategoryId){
		this.MenuCategoryId = MenuCategoryId;
	}
	
	public int getMenuCategoryId(){
		return MenuCategoryId;
	}
	public void setMenuCategoryName(String MenuCategoryName){
		this.MenuCategoryName = MenuCategoryName != null ? MenuCategoryName : "";
	}
	
	public String getMenuCategoryName(){
		return MenuCategoryName;
	}

}
