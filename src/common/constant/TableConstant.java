package common.constant;

public class TableConstant {
	//t_user テーブルカラム名
	public static final String COLUMN_USER_ID = "t_user_Id";
	public static final String COLUMN_USER_FAVORITE_SALON = "t_user_favoriteSalonId";
	public static final String COLUMN_USER_FAVORITE_STYLIST = "t_user_favoriteStylistId";
	public static final String COLUMN_USER_FAVORITE_HAIR_STYLE = "t_user_favoriteHairStyleId";
	public static final String COLUMN_USER_TEL = "t_user_tel";
	public static final String COLUMN_USER_PASSWORD = "t_user_passward"; //綴り間違ってる?
	public static final String COLUMN_USER_HASH = "t_user_cookie";
	public static final String COLUMN_USER_DISABLE_FLAG = "t_user_disableFlag";
	public static final String COLUMN_USER_MAIL = "t_user_mail";
	public static final String COLUMN_USER_IMAGE = "t_user_imagePath";
	public static final String COLUMN_USER_SEX = "t_user_sex";
	public static final String COLUMN_USER_BIRTH = "t_user_birth";
	public static final String COLUMN_USER_NAME = "t_user_name";
	public static final String COLUMN_USER_LATEST_SALON = "t_user_latestViewSalonId";
	public static final String COLUMN_USER_LATEST_STYLIST = "t_user_latestViewStylistId";
	public static final String COLUMN_USER_LATEST_HAIRSTYLE = "t_user_latestViewHairStyleId";
	public static final String COLUMN_USER_POINT = "t_user_point";
	public static final String COLUMN_USER_HISTORY_SALON = "t_user_historySalonId";
	public static final String COLUMN_USER_MASTER_SALON = "t_user_MasterSalonId";
	
	//t_stylist テーブルカラム名
	public static final String COLUMN_STYLIST_ID = "t_stylist_Id";
	public static final String COLUMN_STYLIST_NAME = "t_stylist_name";
	public static final String COLUMN_STYLIST_SEX = "t_stylist_sex";
	public static final String COLUMN_STYLIST_DETAILTEXT = "t_stylist_detailText";
	public static final String COLUMN_STYLIST_USERID = "t_stylist_userId";
	public static final String COLUMN_STYLIST_IMAGE = "t_stylist_imagePath";
	public static final String COLUMN_STYLIST_POSITION = "t_stylist_position";
	public static final String COLUMN_STYLIST_MESSAGE = "t_stylist_message";
	public static final String COLUMN_STYLIST_EXPERIENCEYEAR = "t_stylist_experienceYear";
	public static final String COLUMN_STYLIST_SPECIALMENU = "t_stylist_specialMenu";
	public static final String COLUMN_STYLIST_GOODNUMBER = "t_stylist_goodNumber";
	public static final String COLUMN_STYLIST_VIEWNUMBER = "t_stylist_viewNumber";
	public static final String COLUMN_STYLIST_MAIL = "t_stylist_mail";
	public static final String COLUMN_STYLIST_PHONE = "t_stylist_phoneNumber";
	public static final String COLUMN_STYLIST_BIRTH = "t_stylist_birth";
	public static final String COLUMN_STYLIST_MENUID = "t_stylist_menuId";
	public static final String COLUMN_STYLIST_HAIRSTYLEID = "t_stylist_hairStyleId";
	public static final String COLUMN_STYLIST_SALONID = "t_stylist_salonId";
	public static final String COLUMN_STYLIST_FAVORITE_NUMBER = "t_stylist_favoriteNumber";
	public static final String COLUMN_STYLIST_ISNETRESERVATION = "t_stylist_isNetReservation";
	public static final String COLUMN_STYLIST_CONDITIONID = "t_stylist_searchConditionId";
	public static final String COLUMN_STYLIST_AREAID = "t_stylist_areaId";
	
	//t_hairSalonMaster
	public static final String COLUMN_SALON_ID = "t_hairSalonMaster_salonId";
	public static final String COLUMN_SALON_NAME = "t_hairSalonMaster_name";
	public static final String COLUMN_SALON_VIEW_NUMBER = "t_hairSalonMaster_viewNumber";
	public static final String COLUMN_SALON_GOOD_NUMBER = "t_hairSalonMaster_goodNumber";
	public static final String COLUMN_SALON_DISPLAYORDER = "t_hairSalonMaster_displayOrder";
	public static final String COLUMN_SALON_AREAID = "t_hairSalonMaster_areaId";
	public static final String COLUMN_SALON_MENUID = "t_hairSalonMaster_menuId";
	public static final String COLUMN_SALON_DISABLE_FLAG = "t_hairSalonMaster_disableFlag";
	public static final String COLUMN_SALON_DITAIL_TEXT = "t_hairSalonMaster_detailText";
	public static final String COLUMN_SALON_COUPONID = "t_hairSalonMaster_couponId";
	public static final String COLUMN_SALON_STYLISTID = "t_hairSalonMaster_stylistId";
	public static final String COLUMN_SALON_IMAGEPATH = "t_hairSalonMaster_salonImagePath";
	public static final String COLUMN_SALON_REVIEWID = "t_hairSalonMaster_reviewId";
	public static final String COLUMN_SALON_HAIRSTYLEID = "t_hairSalonMaster_hairStyleId";
	public static final String COLUMN_SALON_CONTACTNAME = "t_hairSalonMaster_contactUserName";
	public static final String COLUMN_SALON_ADDRESS = "t_hairSalonMaster_address";
	public static final String COLUMN_SALON_PHONENUMBER = "t_hairSalonMaster_phoneNumber";
	public static final String COLUMN_SALON_MAIL = "t_hairSalonMaster_mail";
	public static final String COLUMN_SALON_PASSWORD = "t_hairSalonMaster_passward";
	public static final String COLUMN_SALON_OPENTIME = "t_hairSalonMaster_openTime";
	public static final String COLUMN_SALON_CLOSETIME = "t_hairSalonMaster_closeTime";
	public static final String COLUMN_SALON_CLOSEDAY = "t_hairSalonMaster_closeDay";
	public static final String COLUMN_SALON_CREDIT_AVAILABLE = "t_hairSalonMaster_creditAvailable";
	public static final String COLUMN_SALON_CARPARK_AVAILABLE = "t_hairSalonMaster_carParkAvailable";
	public static final String COLUMN_SALON_MAPURL = "t_hairSalonMaster_mapUrl";
	public static final String COLUMN_SALON_MAPIMAGE = "t_hairSalonMaster_mapImagePath";
	public static final String COLUMN_SALON_MESSAGE = "t_hairSalonMaster_message";
	public static final String COLUMN_SALON_MAPLATI = "t_hairSalonMaster_mapLatitude";
	public static final String COLUMN_SALON_MAPLONG = "t_hairSalonMaster_mapLongitude";
//	public static final String COLUMN_SALON_MAPINFO = "t_hairSalonMaster_mapInfoText";
//	public static final String COLUMN_SALON_AVAILABLECOUNTRYID = "t_hairSalonMaster_availableCountryId";
	public static final String COLUMN_SALON_FAVORITENUM = "t_hairSalonMaster_favoriteNumber";
	public static final String COLUMN_SALON_ISNETRESERVATION = "t_hairSalonMaster_isNetReservation";
	public static final String COLUMN_SALON_CONDITIONID = "t_hairSalonMaster_searchConditionId";
	public static final String COLUMN_SALON_RESERVATIONID = "t_hairSalonMaster_reservationId";
	public static final String COLUMN_SALON_SEATID = "t_hairSalonMaster_seatId";
		
}
