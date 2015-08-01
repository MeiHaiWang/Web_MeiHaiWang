package common.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import common.util.PropertiesManager;

public  class Constant {
	private Constant(){
		//インスタンス生成禁止
	}
	public static final  int FLAG_ON = 1;
	public static final  int FLAG_OFF = 0;
	public static final  int USER_NOT_LOGIN = -1;
	public static final String HEADER_USERID = "userID";
	public static final String PARAMETER_SALONID = "t_hairSalonMaster_salonId";
	public static final String ID = "id";
	public static final int ONE_PAGE_NUM = 20;
	public static final int COUNTRY_NUMBER = 20; //TODO: 国の数、定数にする？

	/*
	public static final int TYPE_FOR_SALON_CONDITION = 1;
	public static final int TYPE_FOR_STYLIST_CODITION = 2;
	public static final int TYPE_FOR_STYLIST_LIKE = 3;
	public static final int TYPE_FOR_HAIRSTYLE_MENU = 4;
	public static final int TYPE_FOR_HAIRSTYLE_FACE = 5;
	*/
	
	public static final String TITLE_FOR_SALON_CONDITION = "サロン条件検索";
	public static final String TITLE_FOR_STYLIST_CODITION = "スタイリスト検索条件";
	public static final String TITLE_FOR_STYLIST_LIKE = "スタイリスト検索好み";
	public static final String TITLE_FOR_HAIRSTYLE_MENU = "ヘアスタイル検索メニュー";
	public static final String TITLE_FOR_HAIRSTYLE_FACE = "ヘアスタイル検索顔型";
	
	public static final String TITLE_NAME_LIST_FOR_SALON = "'時間','サービス','設備'";
	public static final String TITLE_NAME_LIST_FOR_STYLIST_CODITION = "'対応日時','得意なメニュー','得意なスタイル'";
	public static final String TITLE_NAME_LIST_FOR_STYLIST_LIKE = "'性別','年齢'";
	public static final String TITLE_NAME_LIST_FOR_HAIRSTYLE_MENU = "'カラー','イメージ'";
	public static final String TITLE_NAME_LIST_FOR_HAIRSTYLE_FACE = "'顔型'";
	
	public static final int MEN = 0;
	public static final int WOMEN = 1;
	
	public static final int JAPANESE_COUNTRY_ID = 0; //TODO: 決め打ちしないほうがいいかも
	
}
