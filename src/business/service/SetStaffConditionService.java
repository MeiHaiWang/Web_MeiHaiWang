package business.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
import business.dao.SalonDao;
import business.dao.StylistDao;
import business.dao.UserDao;
import common._model.TStylistInfo;
import common.constant.TableConstant;
import common.model.StylistInfo;
import common.model.UserInfo;
import common.util.CommonUtil;
import common.util.DBConnection;

public class SetStaffConditionService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        		
  		HttpSession session = request.getSession(false);
		String salonId_str = "";

		/**
		 * stylistId=1234&tag=1&tag=2&tag=6&tag=4&tag=3
			タグ　Trueのタグを送信するのでパラメータについてないタグはfalseに変更する
		 */
		
		String stylistId = request.getParameter("stylistId") != null ?
				request.getParameter("stylistId").toString() : null;
		List<String> tagList = request.getParameterValues("tag") != null ?
				Arrays.asList(request.getParameterValues("tag")) : new ArrayList<String>();	
		/*
		if(tagList.isEmpty()){
			tagList.add("");
		}
		*/
		
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			if(stylistId != null){
			}
			
			JSONObject jsonObject = new JSONObject();
			StylistDao stylistDao = new StylistDao();
			String tags="";
			for(String tag:tagList){
				if(!CommonUtil.isNum(tag)){
					continue;
				}
				tags += tag+",";
			}
			tags = tags.substring(0,tags.length()-1);
			if(stylistId!=null && !tagList.isEmpty()){
				if(conn!=null){
					TStylistInfo info = stylistDao.get(dbConnection, Integer.parseInt(stylistId));
					info.setTStylistSearchConditionId(tags);
					int result_int = stylistDao.update(dbConnection, info);
					if(result_int > 0) result = true;
						/*
						result  = stylistDao.setStylistCondition(
								dbConnection,
								stylistId,
								tagList
								);
						*/
					dbConnection.close();
				}else{
					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
					throw new Exception("DabaBase Connect Error");
				}
			}else{
				//stylistId is -1
				response = faultError(response, "Fault in Set-Stylist-Infomation.");
				return response;
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
