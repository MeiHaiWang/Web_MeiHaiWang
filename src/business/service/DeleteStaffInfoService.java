package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				int stylistId =  Integer.parseInt(t_stylist_Id);
				StylistDao stylistDao = new StylistDao();
				UserDao userDao = new UserDao();
				TStylistInfo info = new TStylistInfo();
				info = stylistDao.get(dbConnection,stylistId);
				int result_int = userDao.logicalDelete(dbConnection, info.getTStylistUserId());
				if(result_int>0){ result_int = stylistDao.logicalDelete(dbConnection, stylistId); }
				if(result_int>0){ 
					//result_int = salonDao.removeId(dbConnection, "t_hairSalonMaster_stylistId", stylistId, salonId); 
					SalonDao salonDao = new SalonDao();
					THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
					salonInfo = salonDao.get(dbConnection, salonId);
					String stylistIds = salonInfo.getTHairSalonMasterStylistId();
					List<String> stylistIdList = Arrays.asList(stylistIds.split(","));
					stylistIds = "";
					for(int index=0;index<stylistIdList.size();index++){
						if(!stylistIdList.get(index).equals(stylistId)){
							stylistIds += stylistIdList.get(index)+",";
						}
					}
					stylistIds = stylistIds.substring(0,stylistIds.length()-1);
					salonInfo.setTHairSalonMasterStylistId(stylistIds);
					result_int = salonDao.update(dbConnection, salonInfo);
				}
				//TODO
				//if(result_int>0){ result_int = hairStyleDao.logicalDelete(dbConnection, hairStyleId); }
				if(result_int>0){ result = true; }
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
