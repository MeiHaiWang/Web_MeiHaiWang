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
import business.dao.MenuDao;
import common.constant.Constant;
import common.model.MenuInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *   
 *   入力：
    {
      t_hairSalonMaster_salonId,
      t_menu_categoryId,
      t_menu_name,
      t_menu_price,
      t_menu_detailText,
      t_menu_imagePath,
    }

 *
    出力：

    {
      result:レコード更新成否,
      t_menu_menuId:登録したサービスのID,
    }
 *
 */

public class SetMenuInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
  		HttpSession session = request.getSession(false);
  		//salonId kokokara
  	    int salonId = -1;
  	    //get a salonId by session
  		String salonId_str = "";
  		if (session != null){
  			salonId_str = (String)session.getAttribute("salonId");
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
		String t_menu_categoryId = request.getParameter("t_menu_categoryId") != null ?
				request.getParameter("t_menu_categoryId").toString() : null;
		String t_menu_name = request.getParameter("t_menu_name") != null ?
				request.getParameter("t_menu_name").toString() : null;
		String t_menu_price = request.getParameter("t_menu_price") != null ?
				request.getParameter("t_menu_price").toString() : null;
		String t_menu_detailText = request.getParameter("t_menu_detailText") != null ?
				request.getParameter("t_menu_detailText").toString() : null;
		String t_menu_imagePath = request.getParameter("t_menu_imagePath") != null ?
				request.getParameter("t_menu_imagePath").toString() : null;

		//MenuInfo を渡したほうがきれいかも.
		MenuInfo menuInfo = new MenuInfo();
		int categoryId = -1;
		if(t_menu_categoryId != null) categoryId = Integer.parseInt(t_menu_categoryId);
		menuInfo.setMenuCategoryId(categoryId);
		menuInfo.setMenuName(t_menu_name);
		int price = -1;
		if(t_menu_price != null) price = Integer.parseInt(t_menu_price);
		menuInfo.setMenuPrice(price);
		menuInfo.setMenuDetailText(t_menu_detailText);
		menuInfo.setMenuImagePath(t_menu_imagePath);

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			int menuId = -1;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				MenuDao menuDao = new MenuDao();
				menuId = menuDao.setMenuInfoForMaster(
						dbConnection,
						salonId,
						menuInfo
						);
				if(menuId > 0) result = true;
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			      t_menu_menuId:登録したサービスのID,
			    }
			 */		
		    jsonObject.put("result",result);
		    jsonObject.put("t_menu_menuId", menuId);
			
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
