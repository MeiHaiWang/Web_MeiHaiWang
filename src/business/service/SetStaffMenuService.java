package business.service;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.StylistDao;
import common._model.TStylistInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.StylistInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 * 入力：
    {
      t_stylist_Id,
      t_menu_t_menu_id:カンマ区切りの文字列,
    }
    
    出力：{ result:レコード更新成否 }
 */

public class SetStaffMenuService implements IServiceExcuter {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
   		HttpSession session = request.getSession(false);
		
		String t_stylist_Id = request.getParameter("t_stylist_Id") != null ?
				request.getParameter("t_stylist_Id").toString() : null;
		String t_menu_t_menu_id = request.getParameter("t_menu_t_menu_id") != null ?
				request.getParameter("t_menu_t_menu_id").toString() : null;
				
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null && t_stylist_Id!=null && t_menu_t_menu_id!=null){
				if(t_menu_t_menu_id.charAt(0)==',')
					t_menu_t_menu_id = t_menu_t_menu_id.substring(1);

				StylistDao stylistDao = new StylistDao();
				TStylistInfo stylistInfo = new TStylistInfo();
				stylistInfo = stylistDao.get(dbConnection, Integer.parseInt(t_stylist_Id));
				//TODO: append? set?
				stylistInfo.setTStylistMenuId(t_menu_t_menu_id);
				int result_int = stylistDao.update(dbConnection, stylistInfo);
				if(result_int>0) result = true;
				/*
				result = stylistDao.setStylistMenuForMaster(
						dbConnection,
						t_stylist_Id,
						t_menu_t_menu_id
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
