package business.service;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.UserDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.DBConnection;

/**
 * 
    setSalonInfo
        概要：サロン情報を登録する
        入力：

    {
      t_hairSalonMaster_name,
      t_area_id,
      t_hairSalonMaster_detailText,
      t_hairSalonMaster_openTime,
      t_hairSalonMaster_closeTime,
      t_hairSalonMaster_closeDay,
      t_hairSalonMaster_creditAvailable,
      t_hairSalonMaster_carParkAvailable,
      t_hairSalonMaster_salonImagePath:カンマ区切りの文字列,
      t_hairSalonMaster_japaneseAvailable,
    }

    出力：{ result:レコード更新成否 }

 */

public class SetSalonInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		 // userIdがパラメータ。なかったら-1を入れておく。
        //TODO テスト用
        userId = 1;
        
		HttpSession session = request.getSession(false);
		String salonId_str = "";
		int salonId = -1;
		//TODO: test
		salonId = 5;
		
		if (session != null){
			salonId_str = (String)session.getAttribute("salonId");
		}else{
			//session is null.
		}
		if(salonId_str.compareTo("") != 0){
			salonId = Integer.parseInt(salonId_str);
		}else{
			//salonId is null.
		}

		String t_hairSalonMaster_name = request.getParameter("t_hairSalonMaster_name") != null ?
				request.getParameter("t_hairSalonMaster_name").toString() : null;
		String t_area_id = request.getParameter("t_area_id") != null ?
				request.getParameter("t_area_id").toString() : null;
		String t_hairSalonMaster_detailText = request.getParameter("t_hairSalonMaster_detailText") != null ?
				request.getParameter("t_hairSalonMaster_detailText").toString() : null;
		/*
		Date t_hairSalonMaster_openTime = null;
		Date t_hairSalonMaster_closeTime = null;
		try {
			String t_hairSalonMaster_openTime_str = request.getParameter("t_hairSalonMaster_openTime") != null ?
					request.getParameter("t_hairSalonMaster_openTime").toString() : null;
			t_hairSalonMaster_openTime = toDate(t_hairSalonMaster_openTime_str, "YYYY-MM-DD HH:mm:ss");
			String t_hairSalonMaster_closeTime_str = request.getParameter("t_hairSalonMaster_closeTime") != null ?
					request.getParameter("t_hairSalonMaster_closeTime").toString() : null;
			t_hairSalonMaster_closeTime = toDate(t_hairSalonMaster_closeTime_str, "YYYY-MM-DD HH:mm:ss");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		String t_hairSalonMaster_openTime = request.getParameter("t_hairSalonMaster_openTime") != null ?
				request.getParameter("t_hairSalonMaster_openTime").toString() : null;
		String t_hairSalonMaster_closeTime = request.getParameter("t_hairSalonMaster_closeTime") != null ?
				request.getParameter("t_hairSalonMaster_closeTime").toString() : null;
		String t_hairSalonMaster_closeDay = request.getParameter("t_hairSalonMaster_closeDay") != null ?
				request.getParameter("t_hairSalonMaster_closeDay").toString() : null;
		String t_hairSalonMaster_creditAvailable = request.getParameter("t_hairSalonMaster_creditAvailable") != null ?
				request.getParameter("t_hairSalonMaster_creditAvailable").toString() : null;
		String t_hairSalonMaster_carParkAvailable = request.getParameter("t_hairSalonMaster_carParkAvailable") != null ?
				request.getParameter("t_hairSalonMaster_carParkAvailable").toString() : null;
				/*
		List<String> salonImagePathList = request.getParameter("t_hairSalonMaster_salonImagePath") != null ?
				Arrays.asList(request.getParameter("t_hairSalonMaster_salonImagePath").split(",")) : new ArrayList<String>();	
				*/
		String t_hairSalonMaster_salonImagePath = request.getParameter("t_hairSalonMaster_salonImagePath");
		String t_hairSalonMaster_japaneseAvailable = request.getParameter("t_hairSalonMaster_japaneseAvailable") != null ?
				request.getParameter("t_hairSalonMaster_japaneseAvailable").toString() : null;
/*
 *      //salonInfo を渡したほうがきれいかも.
		HairSalonInfo salonInfo = new HairSalonInfo();
		salonInfo.setHairSalonId(salonId);
		salonInfo.setHairSalonName(t_hairSalonMaster_name);
		salonInfo.setAreaId(t_area_id);
		salonInfo.setSalonDetailText(t_hairSalonMaster_detailText);
		salonInfo.setSalonOpenTime(t_hairSalonMaster_openTime);
		salonInfo.setSalonCloseTime(t_hairSalonMaster_closeTime);
		salonInfo.setSalonCloseDay(t_hairSalonMaster_closeDay);
		salonInfo.setSalonCreditAvailable(t_hairSalonMaster_creditAvailable);
		salonInfo.setSalonCarParkAvailable(t_hairSalonMaster_carParkAvailable);
		salonInfo.setSalonImagePath(t_hairSalonMaster_salonImagePath);
		salonInfo.setSalonAvailableCountries(t_hairSalonMaster_japaneseAvailable);
*/

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				UserDao userDao = new UserDao();
				result = userDao.setSalonInfo(
						dbConnection,
						salonId,
					    t_hairSalonMaster_name,
					    t_area_id,
					    t_hairSalonMaster_detailText,
					    t_hairSalonMaster_openTime,
					    t_hairSalonMaster_closeTime,
					    t_hairSalonMaster_closeDay,
					    t_hairSalonMaster_creditAvailable,
					    t_hairSalonMaster_carParkAvailable,
					    t_hairSalonMaster_salonImagePath,
					    t_hairSalonMaster_japaneseAvailable
						);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
		    jsonObject.put("result",result);
			
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
	
    public static Date toDate(String str, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(0);
        if(str != null){
            date = sdf.parse(str);        	
        }
        return date;
    }
}


/**
 * INSERT INTO `MEIHAIWAN_TEST`.`t_hairSalonMaster` (`t_hairSalonMaster_salonId`, `t_hairSalonMaster_name`, `t_hairSalonMaster_viewNumber`, `t_hairSalonMaster_goodNumber`, `t_hairSalonMaster_displayOrder`, `t_hairSalonMaster_areaId`, `t_hairSalonMaster_menuId`, `t_hairSalonMaster_disableFlag`, `t_hairSalonMaster_detailText`, `t_hairSalonMaster_couponId`, `t_hairSalonMaster_stylistId`, `t_hairSalonMaster_blogId`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_reviewId`, `t_hairSalonMaster_hairStyleId`, `t_hairSalonMaster_contactUserName`, `t_hairSalonMaster_address`, `t_hairSalonMaster_phoneNumber`, `t_hairSalonMaster_mail`, `t_hairSalonMaster_passward`, `t_hairSalonMaster_openTime`, `t_hairSalonMaster_closeTime`, `t_hairSalonMaster_closeDay`, `t_hairSalonMaster_creditAvailable`, `t_hairSalonMaster_carParkAvailable`, `t_hairSalonMaster_mapUrl`, `t_hairSalonMaster_mapImagePath`, `t_hairSalonMaster_message`, `t_hairSalonMaster_mapLatitude`, `t_hairSalonMaster_mapLongitude`, `t_hairSalonMaster_mapInfoText`, `t_hairSalonMaster_availableCountryId`, `t_hairSalonMaster_favoriteNumber`, `t_hairSalonMaster_isNetReservation`, `t_hairSalonMaster_searchConditionId`) VALUES ('5', 'name', '0', '0', '0', '1', '1', '0', 'detail', '0', '0', '0', 'img.com', '0', '0', 'userName', '0', '0', '0', '0', '2015-06-18 00:00:00', '2015-06-02 00:00:00', 'mon', '1', '1', 'map', 'map', 'message', '0', '0', 'mapinfo', '0', '0', '1', '0')
*/

/**
 * UPDATE `MEIHAIWAN_TEST`.`t_hairSalonMaster` SET `t_hairSalonMaster_name` = 'name2', `t_hairSalonMaster_areaId` = '1,2' WHERE `t_hairsalonmaster`.`t_hairSalonMaster_salonId` = 5;
 */
