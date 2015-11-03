package business.factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.util.ConfigUtil;
import common.constant.Constant;
import business.service.AddHairStyleFavoriteService;
import business.service.AddSalonFavoriteService;
import business.service.AddStylistFavoriteService;
import business.service.CheckLoginService;
import business.service.CheckSessionService;
import business.service.DeleteAlbumInfoService;
import business.service.DeleteCommentService;
import business.service.DeleteCouponInfoService;
import business.service.DeleteImageService;
import business.service.DeleteMenuInfoService;
import business.service.DeleteReviewService;
import business.service.DeleteStaffInfoService;
import business.service.DeleteStaffMenuService;
import business.service.GetAlbumInfoService;
import business.service.GetAreaService;
import business.service.GetCountryAreaListService;
import business.service.GetCouponInfoService;
import business.service.GetCouponKindListService;
import business.service.GetCouponListService;
import business.service.GetCustomerListService;
import business.service.GetHairStyleDetailService;
import business.service.GetHairStyleFavoriteService;
import business.service.GetHairStyleHistoryService;
import business.service.GetHairStyleOrderGoodService;
import business.service.GetHairTypeCategoryService;
import business.service.GetHairStyleOrderNewService;
import business.service.GetHairTypeListService;
import business.service.GetMapInfoService;
import business.service.GetMenuInfoService;
import business.service.GetMypageService;
import business.service.GetNewsService;
import business.service.GetProfileService;
import business.service.GetRecommendService;
import business.service.GetReservationInfoService;
import business.service.GetReservationListService;
import business.service.GetReviewCommentService;
import business.service.GetReviewsService;
import business.service.GetSalonDetailService;
import business.service.GetSalonFavoriteService;
import business.service.GetSalonHistoryService;
import business.service.GetSalonInfoService;
import business.service.GetSalonMapService;
import business.service.GetSearchConditionListService;
import business.service.GetSearchConditionTypeService;
import business.service.GetSearchSalonService;
import business.service.GetSearchStylistService;
import business.service.GetServiceCategoryListService;
import business.service.GetServiceListService;
import business.service.GetStaffInfoService;
import business.service.GetStylistDetailService;
import business.service.GetStylistFavoriteService;
import business.service.GetStylistHistoryService;
import business.service.GetStylistListService;
import business.service.GetUserInfoService;
import business.service.GetUserReservationListService;
import business.service.IServiceExcuter;
import business.service.LogoutSessionService;
import business.service.RegistSalonService;
import business.service.SMScertificationService;
import business.service.SetAlbumInfoService;
import business.service.SetClaimService;
import business.service.SetCouponInfoService;
import business.service.SetHairStyleConditionService;
import business.service.SetHairStyleService;
import business.service.SetMapInfoService;
import business.service.SetMenuInfoService;
import business.service.SetRegistUserService;
import business.service.SetReservationFinishService;
import business.service.SetReservationInfoService;
import business.service.SetReviewService;
import business.service.SetSalonInfoService;
import business.service.SetStaffConditionService;
import business.service.SetStaffInfoService;
import business.service.SetStaffMenuService;
import business.service.SetStylistConditionService;
import business.service.UploadImageService;
import business.service.UploadSalonImageService;
import business.service.UserLoginService;
import business.service.UserRegistService;

public class ServiceExcuteController {
	private static Logger logger = LogManager.getLogger();
	
	public IServiceExcuter getServiceExcuter(HttpServletRequest request, HttpServletResponse response,String requetType){

		logger.info("{}:getServiceExcuter Start",this.getClass().getName(), request.getRequestURI());		
		String uri = request.getRequestURI();
		
		IServiceExcuter service = null;
		
		/**
		 * アプリ側 GET
		 */
		//オススメ取得　GET /api/:version/osusume
		if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/osusume") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetRecommendService();
		}
		//店舗検索　GET /api/:version/shop_list
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/shop_list") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetSearchSalonService();
		}
		//店舗詳細取得 GET /api/:version/shop_detail
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/shop_detail") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetSalonDetailService();
		}
		//店舗地図取得 GET /api/:version/shopMap
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/shopMap") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetSalonMapService();
		}
		//スタイリスト一覧取得 GET /api/:version/stylist_list
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist_list") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetSearchStylistService();
		}
		//スタイリスト詳細取得 GET /api/:version/stylist_detail
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist_detail") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetStylistDetailService();
		}
		//スタイリスト用ヘアスタイルアップ GET /api/:version/stylist_style_up
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist_style_up") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new SetHairStyleService();
		}
		//スタイリスト用タグ登録　GET /api/:version/stylist_tag
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist_tag") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new SetStylistConditionService();
		}
		//地域取得 GET /api/:version/area
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/area") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetAreaService();
		}
		//ヘアカタログカテゴリー取得 GET /api/:version/catalog/categoly
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/catalog/categoly") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetHairTypeCategoryService();
		}
		//お気に入りサロン取得 GET /api/:version/salon_Favorite
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/salon_Favorite") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetSalonFavoriteService();
		}
		//お気に入りヘアスタイル取得 GET /api/:version/hire_Favorite
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/hire_Favorite") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetHairStyleFavoriteService();
		}
		//お気に入りスタイリスト一覧取得 GET /api/:version/stylist_Favorite
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist_Favorite") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetStylistFavoriteService();
		}
		//最近見たサロン一覧 GET /api/:version/salon_history
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/salon_history") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetSalonHistoryService();
		}
		//最近見たスタイリスト一覧取得 GET /api/:version/stylist_History
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist_History") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetStylistHistoryService();
		}
		//最近みたヘアスタイル取得 GET /api/:version/hire_History
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/hire_History") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetHairStyleHistoryService();
		}
		//美容ニュース一覧取得 GET /api/:version/news
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/hire_History") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetNewsService();
		}
		//口コミ取得 GET /api/:version/reviews
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/reviews") && Constant.HTTP_REQUEST_TYPE_GET.equals(requetType)){
			service = new GetReviewsService();
		}
		
		/**
		 * アプリ側 POST
		 */
		//アカウント登録 POST /api/:version/smscert
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/smscert") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SMScertificationService();
		}
		
		//ログイン POST /api/:version/login
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/login") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new UserLoginService();
		}

		//クーポン一覧取得 POST /api/:version/CouponList
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/CouponList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetCouponListService();
		}
		//検索条件タイプ取得 POST /api/:version/getSearchConditionType
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getSearchConditionType") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetSearchConditionTypeService();
		}
		//検索条件取得 POST /api/:version/getSearchConditionList
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getSearchConditionList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetSearchConditionListService();
		}
		//ヘアスタイル検索条件追加/api/:version/hair/setCondition
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/hair/setCondition") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetHairStyleConditionService();
		}
		//スタイリスト検索条件追加 POST /api/:version/stylist/setCondition
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist/setCondition") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetStaffConditionService();
		}
		//ヘアカタログ一覧取得
		//POST /api/:version/catalog/list/new 新着順
		//POST /api/:version/catalog/list/good 高評価順
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/catalog/list/new") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetHairStyleOrderNewService();
		}
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/catalog/list/good") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetHairStyleOrderGoodService();
		}
		//ヘアカタログ詳細取得 POST /api/:version/catalog/detail
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/catalog/detail") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetHairStyleDetailService();
		}
		//ヘアスタイルお気に入り更新 POST /api/:version/hire/good
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/hire/good") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new AddHairStyleFavoriteService();
		}
		//サロンお気に入り更新 POST /api/:version/salon/good
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/salon/good") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new AddSalonFavoriteService();
		}
		//スタイリストお気に入り更新 POST /api/:version/stylist/good
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/stylist/good") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new AddStylistFavoriteService();
		}
		//マイページ情報取得 POST /api/:version/mypage
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/mypage") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetMypageService();
		}
		//プロフィール情報取得 POST /api/:version/profile
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/profile") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetProfileService();
		}
		//口コミ返信取得 POST /api/:version/reviewRe
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/reviewRe") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetReviewCommentService();
		}
		//口コミ登録 POST /api/:version/reviewRegist
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/reviewRegist") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetReviewService();
		}
		//口コミ削除 POST /api/:version/deleteReview
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/reviewRegist") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteReviewService();
		}
		//口コミ返信削除 POST /api/:version/deleteComment
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/reviewRegist") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteCommentService();
		}

		/**
		 * 店舗側 POST 共通
		 */
		//URL：/api/:version/checkLogin 概要：認証＆セッションの確立セット
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/checkLogin") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new CheckLoginService();
		}
		//URL：/api/:version/checkSession 概要：セッションが確立されているか確認し、確立されていればサロンIDを取得
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/checkSession") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new CheckSessionService();
		}
		//URL: /api/:version/logOutSession ログアウト
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/logOutSession") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new LogoutSessionService();
		}
		
		//URL: api/:version/uploadImage 概要： 画像アップロード
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/uploadImage") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new UploadImageService();
		}

		//URL：/api/:version/getCountryAreaList 概要：都市、地域一覧取得
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getCountryAreaList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetCountryAreaListService();
		}
		//URL：/api/:version/getServiceList 概要：サービス一覧取得
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getServiceList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetServiceListService();
		}
		//URL：/api/:version/getStylistList 概要：スタイリスト一覧取得
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getStylistList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetStylistListService();
		}
		//URL：/api/:version/getServiceCategoryList 概要：サービスカテゴリ一覧取得
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getServiceCategoryList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetServiceCategoryListService();
		}
		//URL：/api/:version/getHairTypeList 概要：ヘアスタイルタイプ一覧取得
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getHairTypeList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetHairTypeListService();
		}
		//URL：/api/:version/getCouponKindList 概要：クーポン種別一覧取得
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getCouponKindList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetCouponKindListService();
		}
		//URL：/api/:version/getSearchConditionList 概要：検索条件タイプのタイトルと値一覧を取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getSearchConditionList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetSearchConditionListService();
		}
		
		/**
		 * 店舗側 POST サロンページ
		 */
		//URL：/api/:version/getSalonInfo 概要：サロン情報管理ページ表示用データを取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getSalonInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetSalonInfoService();
		}
		//URL：/api/:version/setSalonInfo 概要：サロン情報を登録する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setSalonInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetSalonInfoService();
		}
		//URL：/api/:version/deleteImage 概要：画像を削除する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/deleteImage") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteImageService();
		}
		//URL: api/:version/uploadSalonImage 概要： 画像アップロード
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/uploadSalonImage") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new UploadSalonImageService();
		}

		
		/**
		 * 店舗側 POST スタイリストページ
		 */
		//URL：/api/:version/getStaffInfo 概要：スタッフ情報管理ページ表示用データを取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getStaffInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetStaffInfoService();
		}
		//URL：/api/:version/setStaffInfo 概要：スタッフ情報管理ページ表示用データを取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setStaffInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetStaffInfoService();
		}
		//URL：/api/:version/setStaffMenu 概要：スタッフの対応可能なサービスの更新
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setStaffMenu") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetStaffMenuService();
		}
		//URL：/api/:version/deleteStaffMenu 概要：スタッフの対応可能なサービスの削除
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/deleteStaffMenu") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteStaffMenuService();
		}
		//URL：/api/:version/deleteStaffInfo 概要：スタッフ情報の削除
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/deleteStaffInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteStaffInfoService();
		}
		
		/**
		 * 店舗側 POST サービス（メニュー）ページ
		 */
		//URL：/api/:version/getMenuInfo 概要：サービス情報管理ページ表示用データを取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getMenuInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetMenuInfoService();
		}
		//URL：/api/:version/setMenuInfo 概要：サービス情報を登録する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setMenuInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetMenuInfoService();
		}
		//URL：/api/:version/deleteMenuInfo 概要：サービス情報の削除
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/deleteMenuInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteMenuInfoService();
		}
		
		/**
		 * 店舗側 POST クーポンページ
		 */
		//URL：/api/:version/getCouponInfo 概要：クーポン情報管理ページ表示用データを取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getCouponInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetCouponInfoService();
		}
		//URL：/api/:version/setCouponInfo 概要：クーポン情報を登録する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setCouponInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetCouponInfoService();
		}
		//URL：/api/:version/deleteCouponInfo 概要：クーポン情報の削除
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/deleteCouponInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteCouponInfoService();
		}

		/**
		 * 店舗側 POST アルバムページ
		 */
		//URL：/api/:version/getAlbumInfo 概要：アルバム情報管理ページ表示用データを取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getAlbumInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetAlbumInfoService();
		}
		//URL：/api/:version/setAlbumInfo 概要：クーポン情報を登録する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setAlbumInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetAlbumInfoService();
		}
		//URL：/api/:version/deleteAlbumInfo 概要：アルバム情報の削除
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/deleteAlbumInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new DeleteAlbumInfoService();
		}

		/**
		 * 店舗側 POST 地図情報ページ
		 */
		//URL：/api/:version/getMapInfo 概要：地図情報管理ページ表示用データを取得する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getMapInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetMapInfoService();
		}
		//URL：/api/:version/setMapInfo 概要：地図情報を登録する
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setMapInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetMapInfoService();
		}
		
		/**
		 * 店舗側 POST 予約用
		 */
		//予約情報登録 setReservationInfo
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setReservationInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetReservationInfoService();
		}
		//予約情報取得 getReservationInfo
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getReservationInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetReservationInfoService();
		}
		//予約一覧取得 getReservationList
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getReservationList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetReservationListService();
		}
		//ユーザ予約一覧取得 getUserReservationList
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getUserReservationList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetUserReservationListService();
		}
		//予約終了 setReservationFinish
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setReservationFinish") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetReservationFinishService();
		}
		//顧客情報一覧取得 getCustomerList
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getCustomerList") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetCustomerListService();
		}
		//クレーム登録 setClaim
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setClaim") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetClaimService();
		}
		//ユーザ情報取得 getUserInfo
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/getUserInfo") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new GetUserInfoService();
		}
		//ユーザ情報登録 setRegistUser
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/setRegistUser") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new SetRegistUserService();
		}

		/**
		 * 管理用
		 */
		//店舗登録 POST /api/:version/registSalon
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/registSalon") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new RegistSalonService();
		}
		/*
		//ユーザ登録 api/:version/regist
		else if(uri.equals(ConfigUtil.getConfig("contextPath") + "/api/:version/regist") && Constant.HTTP_REQUEST_TYPE_POST.equals(requetType)){
			service = new UserRegistService();
		}
		*/

		
		else{
		}

		logger.info("getServiceExcuter Selected Excuter ={}",service.getClass().getName());
		logger.info("{}:getServiceExcuter End",this.getClass().getName());	
		return service;
	}
}
