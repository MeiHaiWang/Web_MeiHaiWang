package common.constant;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import common.util.PropertiesManager;

public  class Constant {
	private Constant(){
		//インスタンス生成禁止
	}

	public static final String KEY = "azu93fzzei93084jnnekamel2asdfghj";
	public static final String IV_IV = "azu93fzzei93084j";
	public static final  int FLAG_ON = 1;
	public static final  int FLAG_OFF = 0;
	public static final  int USER_NOT_LOGIN = -1;
	public static final String HEADER_USERID = "userID";
	public static final String PARAMETER_SALONID = "t_hairSalonMaster_salonId";
	public static final String ID = "id";
	public static final int ONE_PAGE_NUM = 20;
	public static final int COUNTRY_NUMBER = 20; //TODO: 国の数、定数にする？
	public static final int PAGE_DISPLAY_LIMIT = 100;
	public static final String TYPE_FOR_SALON_CONDITION = "サロン検索条件";
	public static final String TYPE_FOR_STYLIST_CONDITION = "スタイリスト条件検索（サービス）";
	public static final String TYPE_FOR_STYLIST_LIKE = "スタイリスト条件検索（好み）";
	public static final String TYPE_FOR_HAIRSTYLE_MENU_FORMEN = "男性用ヘアスタイル条件検索";
	public static final String TYPE_FOR_HAIRSTYLE_MENU_FORWOMEN = "女性用ヘアスタイル条件検索";
	public static final String TYPE_FOR_HAIRSTYLE_MENU = "ヘアスタイル条件検索（メニュー）";
	public static final String TYPE_FOR_HAIRSTYLE_FACE = "ヘアスタイル条件検索（顔型）";
	
	public static final int MEN = 0;
	public static final int WOMEN = 1;
	
	public static final int JAPANESE_COUNTRY_ID = 1; //TODO: 決め打ちしないほうがいいかも

	//テーブル操作用文字定義
	public static final String SELECT = "SELECT";
	public static final String SELECTALL = "SELECT * FROM";
	public static final String FROM = "FROM";
	public static final String WHERE = "WHERE";
	public static final String AND = "AND";
	public static final String INSERT = "INSERT INTO";
	public static final String VALUES = "VALUES";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";
	public static final String SET = "SET";
	public static final String SPACE = " ";
	public static final String EQUAL = "=";
	public static final String DOT = ".";
	public static final String SINGLEQ = "'";
	public static final String BACKQ = "`";
	public static final String SEMICOLON = ";";
	public static final String BRACKET_1 = "(";
	public static final String BRACKET_2 = ")";
	public static final String EMPTY = "";
	public static final String ZERO = "0";
	public static final String COMMA = ",";
	public static final String USERID = "userID";
	public static final String NO_TITLE = "no title";
	public static final String NO_IMAGE_NAME = "none";
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new  SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	
	//通信定義
	public static final String HTTP_REQUEST_TYPE_POST = "POST";
	public static final String HTTP_REQUEST_TYPE_GET = "GET";
	
	//SQL定義
	public static final String SORT_BY_DESC = "DESC";
	public static final String SORT_BY_ASC = "ASC";
	//テーブル定義(master)
	public static final String TABLE_BEUTY_NEWS = "t_masterNews";
	public static final String TABLE_AREA = "t_masterArea";
	public static final String TABLE_COUNTRY = "t_masterCountry";
	public static final String TABLE_COUPON_KIND = "t_masterCouponKind";
	public static final String TABLE_HAIR_TYPE = "t_masterHairType";
	public static final String TABLE_MENU_CATEGORY = "t_masterMenuCategory";
	public static final String TABLE_RECOMMEND = "t_masterRecommend";
	public static final String TABLE_SEARCH_CONDITION = "t_masterSearchCondition";
	public static final String TABLE_SEARCH_CONDITION_TITLE = "t_masterSearchConditionTitle";
	public static final String TABLE_SEARCH_CONDITION_TYPE = "t_masterSearchConditionType";
	//テーブル定義(user)
	public static final String TABLE_CLAIM = "t_claim";
	public static final String TABLE_COMMENT = "t_comment";
	public static final String TABLE_COUPON = "t_coupon";
	public static final String TABLE_EVALUATION = "t_evaluation";
	public static final String TABLE_SALON = "t_hairSalonMaster";
	public static final String TABLE_HAIR_STYLE = "t_hairStyle";
	public static final String TABLE_IMAGE = "t_image";
	public static final String TABLE_MENU = "t_menu";
	public static final String TABLE_RESERVATION = "t_reservation";
	public static final String TABLE_REVIEW = "t_review";
	public static final String TABLE_SEAT = "t_seat";
	public static final String TABLE_STYLIST = "t_stylist";
	public static final String TABLE_USER = "t_user";
	
	/**
	 * メッセージリソース
	 */
	public static final String MESSAGE_ERROR_NO_USER = "存在しないユーザが指定されました";
	public static final String MESSAGE_ERROR_NO_SESSION = "セッションが確立できていません";
	public static final String MESSAGE_ERROR_NO_LOGIN = "ログイン状態が確認できませんでした";
	
	
}
