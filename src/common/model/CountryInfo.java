package common.model;

public class CountryInfo extends BaseInfo implements IBaseInfo{
	private int CountryId = Integer.MIN_VALUE;
	private String CountryName ="";
	
	public CountryInfo(){
	}
	
	public void setCountryId(int CountryId){
		this.CountryId = CountryId;
	}
	
	public int getCountryId(){
		return CountryId;
	}

	public void setCountryName(String CountryName){
		this.CountryName = CountryName != null ? CountryName : "";
	}
	
	public String getCountryName(){
		return CountryName;
	}

}
