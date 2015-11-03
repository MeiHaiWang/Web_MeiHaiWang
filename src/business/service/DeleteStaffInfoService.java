package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.StylistDao;
import business.dao.UserDao;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.DBConnection;

public class DeleteStaffInfoService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        /*
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		 // userIdがパラメータ。なかったら-1を入れておく。
        //TODO テスト用
        userId = 1;
        */
        
  		HttpSession session = request.getSession(false);

  		//salonId kokokara
  	    int salonId = -1;
  	    //get a salonId by session
  		String salonId_str = "";
  		if (session != null){
  			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
  		}
  		if(salonId_str != null){			
  			if(salonId_str.compareTo("") != 0){
  				salonId = Integer.parseInt(salonId_str);
  			}
  		}   
  		if(salonId < 0){
  	        //get a salonId by parameter
  	        salonId = request.getParameter(Constant.PARAMETER_SALONID)!= null 
  			?Integer.parseInt(request.getParameter(Constant.PARAMETER_SALONID)) : -1;
  		}
  		//salonId kokomade		
  		
		String t_stylist_Id = request.getParameter("t_stylist_Id") != null ?
				request.getParameter("t_stylist_Id").toString() : null;
		//debug
		System.out.println("delete:"+t_stylist_Id);
				
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			/**
			 * TODO
			 * ¥ 物理削除（テーブルから直接データを消す）にするか
			 *   論理削除（disableFlag）にするか
			 * ¥ stylistをdisableにしたらuserもdisableでいいか
			 */
			if(conn!=null && t_stylist_Id!=null){
				StylistDao stylistDao = new StylistDao();
				UserDao userDao = new UserDao();
				StylistInfo info = new StylistInfo();
				info.setObjectId(Integer.parseInt(t_stylist_Id));
				int userId = stylistDao.getStylistIntData(dbConnection, TableConstant.COLUMN_STYLIST_USERID, info);
				UserInfo userInfo = new UserInfo(); 
				userInfo.setObjectId(userId);
				int result_int = userDao.setUserIntData(dbConnection, TableConstant.COLUMN_USER_DISABLE_FLAG, Constant.FLAG_ON, userInfo);
				if(result_int>0) result_int = stylistDao.setStylistIntData(dbConnection, TableConstant.COLUMN_STYLIST_DISABLE_FLAG, Constant.FLAG_ON, info);
				if(result_int>0) result_int = salonDao.removeId(dbConnection, "t_hairSalonMaster_stylistId", stylistId, salonId);
				if(result_int>0) result_int = hairStyleDao.setHairStyleIntData(dbConnection, TableConstant.COLUMN_HAIRSTYLE_DISABLE_FLAG, Constant.FLAG_ON, hairStyleInfo);
				if(result_int>0) result = true;
				//result = stylistDao.DeleteStylistObject(dbConnection, t_stylist_Id);
				/*
				result = stylistDao.DeleteStylistInfoForMaster(
						dbConnection,
						t_stylist_Id,
						salonId
						);
						*/
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			    }
			 * 
			 */
			
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
			
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
}
