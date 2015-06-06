package common.constant;

public  class Constant {
	private Constant(){
		//インスタンス生成禁止
	}
	public static final  int FLAG_ON = 1;
	public static final  int FLAG_OFF = 0;
	public static final  int USER_NOT_LOGIN = -1;
	public static final String HEADER_USERID = "userID";
	public static final String ID = "id";
	public static final int ONE_PAGE_NUM = 20;
	public static final String TITLE_NAME_LIST_FOR_SALON = "'時間','サービス','設備'";
	public static final String TITLE_NAME_LIST_FOR_STYLIST_CODITION = "'対応日時','得意なメニュー','得意なスタイル'";
	public static final String TITLE_NAME_LIST_FOR_STYLIST_LIKE = "'性別','年齢'";
	public static final String TITLE_NAME_LIST_FOR_HAIRSTYLE_MENU = "'カラー','イメージ'";
	public static final String TITLE_NAME_LIST_FOR_HAIRSTYLE_FACE = "'顔型'";
}
