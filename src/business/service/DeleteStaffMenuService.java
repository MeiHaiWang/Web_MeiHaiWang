package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.StylistDao;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.StylistInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
 *UPDATE `MEIHAIWAN_TEST`.`t_stylist` SET `t_stylist_menuId` = '' WHERE `t_stylist`.`t_stylist_Id` = 13;
 */

public class DeleteStaffMenuService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
  		HttpSession session = request.getSession(false);
		
		String t_stylist_Id = request.getParameter("t_stylist_Id") != null ?
				request.getParameter("t_stylist_Id").toString() : null;

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null && t_stylist_Id!=null){
				StylistDao stylistDao = new StylistDao();
				StylistInfo stylistInfo = new StylistInfo();
				stylistInfo.setObjectId(Integer.parseInt(t_stylist_Id));
				int result_int = stylistDao.setStylistStringData(dbConnection, TableConstant.COLUMN_STYLIST_MENUID, Constant.EMPTY, stylistInfo);
				if(result_int>0) result = true;
				/*
				result = stylistDao.DeleteStylistMenuForMaster(
						dbConnection,
						t_stylist_Id
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
