package business.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;
import business.dao.SalonDao;
import business.dao.StylistDao;
import business.dao.UserDao;
import common._model.THairSalonMasterInfo;
import common._model.TStylistInfo;
import common._model.TUserInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.DBConnection;

/**
 *   input:
 *     {
      t_stylist_name,
      t_stylist_sex,
      t_stylist_phoneNumber,
      t_stylist_mail,
      t_stylist_imagePath,
      t_stylist_birth,
      t_stylist_position,
      t_stylist_experienceYear,
      t_stylist_specialMenu,
      t_stylist_message,
      t_stylist_restDay,
      t_stylist_restTime
    }
 * @author kanijunnari
 *
 */

public class SetStaffInfoService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	private static Logger logger = LogManager.getLogger();
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
  		HttpSession session = request.getSession(false);

  		String salonId_str = "";
		int salonId = -1;
		if (session != null){
			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
		}else{
			//session is null.
			response = faultError(response, "session is null.");
			return response;
		}
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}else{
			//salonId is null.
			response = faultError(response, "salonId is empty.");
			return response;
		}
		
		String t_stylist_Id = request.getParameter("t_stylist_Id") != null ?
				request.getParameter("t_stylist_Id").toString() : null;
		String t_stylist_name = request.getParameter("t_stylist_name") != null ?
				request.getParameter("t_stylist_name").toString() : null;
		String t_stylist_sex = request.getParameter("t_stylist_sex") != null ?
				request.getParameter("t_stylist_sex").toString() : null;
		String t_stylist_phoneNumber = request.getParameter("t_stylist_phoneNumber") != null ?
				request.getParameter("t_stylist_phoneNumber").toString() : null;
		String t_stylist_mail = request.getParameter("t_stylist_mail") != null ?
				request.getParameter("t_stylist_mail").toString() : null;
		String t_stylist_imagePath = request.getParameter("t_stylist_imagePath") != null ?
				request.getParameter("t_stylist_imagePath").toString() : null;
		String t_stylist_birth = request.getParameter("t_stylist_birth") != null ?
				request.getParameter("t_stylist_birth").toString() : null;
		String t_stylist_position = request.getParameter("t_stylist_position") != null ?
				request.getParameter("t_stylist_position").toString() : null;
		String t_stylist_experienceYear = request.getParameter("t_stylist_experienceYear") != null ?
				request.getParameter("t_stylist_experienceYear").toString() : null;
		String t_stylist_specialMenu = request.getParameter("t_stylist_specialMenu") != null ?
				request.getParameter("t_stylist_specialMenu").toString() : null;
		String t_stylist_message = request.getParameter("t_stylist_message") != null ?
				request.getParameter("t_stylist_message").toString() : null;
		String t_stylist_searchConditionId = request.getParameter("t_stylist_searchConditionId") != null ?
				request.getParameter("t_stylist_searchConditionId").toString() : "";
		String t_stylist_restDay = request.getParameter("t_stylist_restDay") != null ?
				request.getParameter("t_stylist_restDay").toString() : null;
		String t_stylist_restTime = request.getParameter("t_stylist_restTime") != null ?
				request.getParameter("t_stylist_restTime").toString() : null;

		/**
		 * stylistInfo にパラメータ情報を格納.
		 */
		TStylistInfo stylistInfo = new TStylistInfo();
		stylistInfo.setName(t_stylist_name);
		//stylistInfo.setStylistGender(Integer.parseInt(t_stylist_sex));
		int sex = -1;
		if(t_stylist_sex != ""){
			sex = Integer.parseInt(t_stylist_sex);
		}else{
			sex = 0;
		}
		stylistInfo.setTStylistSex(sex);
		stylistInfo.setTStylistPhoneNumber(t_stylist_phoneNumber);
		//debug
		//System.out.println("register phone:" + t_stylist_phoneNumber + ", " + stylistInfo.getPhoneNumber());
		stylistInfo.setTStylistMail(t_stylist_mail);
		//debug
		//System.out.println("register mail:" + t_stylist_mail + ", " + stylistInfo.getMail());
		stylistInfo.setTStylistImagePath(t_stylist_imagePath);
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date birth = new Date(0);
		try {
			if(!t_stylist_birth.equals("--00:00:00"))
				birth = sdf.parse(t_stylist_birth);
			stylistInfo.setTStylistBirth(birth);
			//debug
			//System.out.println("birth = "+birth);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stylistInfo.setTStylistPosition(t_stylist_position);
		int year = -1;
		if(t_stylist_experienceYear != "") year = Integer.parseInt(t_stylist_experienceYear);
			else year = 0;
		stylistInfo.setTStylistExperienceYear(year);
		stylistInfo.setTStylistSpecialMenu(t_stylist_specialMenu);
		stylistInfo.setTStylistMessage(t_stylist_message);
		stylistInfo.setTStylistSearchConditionId(t_stylist_searchConditionId);
		//stylistInfo.setTStylistRestDay(t_stylist_restDay);
		//stylistInfo.setTStylistRestDay(t_stylist_restTime);
		
		/**
		 * DB更新
		 * スタイリスト情報の更新
		 * ユーザ情報が登録されていれば, ユーザ情報とスタイリスト情報とを紐付け
		 * ユーザ情報が登録されていなければ, ユーザ情報も同時に登録
		 */
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			//成功確認と返り値
			boolean result = false;
			int stylistId = -1;

			/**
			 * ユーザ情報が登録されていればuserIDが格納される変数
			 */
			int userId = -1; //registered UserId
			
			/**
			 * スタイリスト情報の登録か編集かを調べるために、stylistIdをチェック
			 */
			if(t_stylist_Id != null){
				//debug
				System.out.println("stylistId:"+t_stylist_Id);
				if(t_stylist_Id.length()!=0){
					//スタイリストの編集
					stylistId = Integer.parseInt(t_stylist_Id);
				}else{
					//スタイリストの新規登録
					//stylistId = -1
				}
			}
			
			//返却用JSONオブジェクト
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				StylistDao stylistDao = new StylistDao();
				UserDao userDao = new UserDao();
				TUserInfo userInfo = new TUserInfo();
				
				/**
				 * スタイリスト情報をUPDATEする場合には、既登録情報を取得
				 * ユーザ情報をUPDATEする場合には、既登録情報を取得
				 */
				TStylistInfo stylistPreviousInfo = new TStylistInfo();
				if(stylistId!=-1){
					//スタイリストもユーザもすでに登録済み
					//スタイリスト情報のUpdate+ユーザ情報のUpdate
					//stylistPreviousInfo = stylistDao.getStylistDetailInfo(dbConnection, stylistId);
					stylistPreviousInfo = stylistDao.get(dbConnection, stylistId);
					if(stylistPreviousInfo != null) userId = stylistPreviousInfo.getTStylistUserId();
				}else{
					//スタイリストの新規登録 & ユーザUPDATE?
					if(t_stylist_phoneNumber!=""){
						//電話番号からユーザ情報を取得->ユーザ情報は登録済みなのでUpdate
						//userInfo = userDao.getUserInfoByTel(dbConnection, t_stylist_phoneNumber);
						List<TUserInfo> userInfoList = userDao.getByColumn(dbConnection, TableConstant.COLUMN_USER_TEL, t_stylist_phoneNumber);
						userInfo = userInfoList.get(0);
						if(userInfo!=null){
							userId = userInfo.getTUserId();
						}
					}else{
						//ユーザ情報も新規登録
					}
				}
				
				/**
				 *  まずユーザ情報をDBに更新
				 */
				if(userId != -1){
					//スタイリスト+ユーザが既に登録されている場合Update
					//debug
					System.out.println("スタイリストがすでにユーザ登録済み"+",userId:"+userId);
					//ユーザ情報をアップデート
					userInfo.setTUserId(userId);
					userInfo.setTUserMail(stylistInfo.getTStylistMail());
					userInfo.setTUserTel(stylistInfo.getTStylistPhoneNumber());
					userInfo.setUserIsStylist(1);
					userInfo.setName(stylistInfo.getName());
					userInfo.setTUserSex(stylistInfo.getTStylistSex());
					userInfo.setTUserBirth(stylistInfo.getTStylistBirth());
					userInfo.setTUserImagePath(stylistInfo.getTStylistImagePath());
					//userId = userDao.setUserAcount(dbConnection, userInfo);
					userId = userDao.update(dbConnection, userInfo);
				}else{
					//ユーザ情報が登録されていない場合
					userInfo = new TUserInfo();
					userInfo.setTUserMail(stylistInfo.getTStylistMail());
					userInfo.setTUserTel(stylistInfo.getTStylistPhoneNumber());
					userInfo.setUserIsStylist(1);
					userInfo.setTUserName(stylistInfo.getTStylistName());
					userInfo.setTUserSex(stylistInfo.getTStylistSex());
					userInfo.setTUserBirth(stylistInfo.getTStylistBirth());
					userInfo.setTUserImagePath(stylistInfo.getTStylistImagePath());
					userInfo.setTUserPassward("0000"); //TODO 初期パスワード
					userId = userDao.save(dbConnection, userInfo);
					//debug
					logger.info("スタイリストを新規にユーザ登録:userId={}"+userId);
				}
				if(userId>-1){
					SalonDao salonDao = new SalonDao();
					//String salonAreaId = salonDao.getSalonAreaId(dbConnection,salonId);
					String salonAreaId = salonDao.get(dbConnection,salonId).getTHairSalonMasterAreaId();
					stylistInfo.setTStylistAreaId(salonAreaId);
					//debug
					System.out.println("スタイリスト登録: "+stylistId+", "+userId+", "+stylistInfo.getName());
					/**
					 * stylistIdが0より小さい場合は新規スタイリスト登録
					 * stylistIdが0より大きい場合はスタイリスト情報修正
					 */
					if(stylistId>0){
						stylistId = stylistDao.save(
								dbConnection,
								stylistInfo
								);
					}else{
						stylistId = stylistDao.update(
								dbConnection,
								stylistInfo
								);
					}
					
					if(stylistId>0){
						/**
						 * stylistIdをsalonテーブルに追加
						 */
						//salonDao.setSalonInfo(dbConnection, salonId, salonInfo);
						THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
						salonInfo = salonDao.get(dbConnection, salonId);
						String stylistIds = salonInfo.getTHairSalonMasterStylistId();
						List<String> stylistIdList = Arrays.asList(stylistIds.split(","));
						if(!stylistIdList.contains(stylistId)) {
							stylistIds="";
							for(int index=0;index<stylistIdList.size();index++){
								stylistIds += stylistIdList.get(index)+",";
							}
							stylistIds = stylistIds.substring(0,stylistIds.length()-1);
							salonInfo.setTHairSalonMasterStylistId(stylistIds);
							int resultInt = salonDao.update(dbConnection, salonInfo);
							if(resultInt > 0) result = true;
						}
					}
					
					if(result){
						/**
						 * userIdをスタイリストテーブルに追加
						 */
						stylistInfo = stylistDao.get(dbConnection, stylistId);
						stylistInfo.setTStylistUserId(userId);
						int resultInt = stylistDao.update(dbConnection, stylistInfo);
						if(resultInt > 0) result = true;
					}
					
				}else{
					//userId is null.
					response = faultError(response, "Fault in Set-User-Registration."
							+ "Your phone-number may be used by another.");
					return response;
				}
				if(stylistId < 0) {
					//stylistId is -1
					response = faultError(response, "Fault in Set-Stylist-Infomation.");
					return response;
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			      t_stylist_Id:登録したスタッフのID,
			    }
			 * 
			 */
			
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
		    jsonObject.put("t_stylist_Id", stylistId);
			
		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		    
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}
	/*
    public static Date toDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(0);
        if(str != null){
            date = sdf.parse(str);        	
        }
        return date;
    }
    */

	//エラーメソッド　何らかの理由でアクションに失敗
	public HttpServletResponse faultError(HttpServletResponse response, String resultStr){
		int responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "false");
		jsonObject.put("reason", resultStr);
	    try {
			PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setStatus(responseStatus);
		return response;
	}
	
}
