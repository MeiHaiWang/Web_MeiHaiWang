package business.service;

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

import net.sf.json.JSONObject;
import business.dao.MenuDao;
import business.dao.SalonDao;
import common._model.THairSalonMasterInfo;
import common._model.TMenuInfo;
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
      t_menu_time
    }

 *
    出力：

    {
      result:レコード更新成否,
      t_menu_menuId:登録したサービスのID,
    }
 *
 */

public class SetMenuInfoService implements IServiceExcuter{
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
		String t_menu_menuId = request.getParameter("t_menu_menuId") != null ?
				request.getParameter("t_menu_menuId").toString() : null;
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
		String t_menu_time = request.getParameter("t_menu_time") != null ?
				request.getParameter("t_menu_time").toString() : null;

		//MenuInfo にパラメータを格納して渡す.
		TMenuInfo menuInfo = new TMenuInfo();
		int menuId = -1;
		if(t_menu_menuId != null){
			if(t_menu_menuId.length()!=0) menuId = Integer.parseInt(t_menu_menuId);
		}
		menuInfo.setTMenuMenuId(menuId);
		int categoryId = 0;
		if(t_menu_categoryId != null && t_menu_categoryId != ""){
			categoryId = Integer.parseInt(t_menu_categoryId);
		}
		menuInfo.setTMenuCategoryId(categoryId);
		menuInfo.setTMenuName(t_menu_name);
		int price = 0;
		if(t_menu_price != null && t_menu_price != ""){
			price = Integer.parseInt(t_menu_price);
		}
		menuInfo.setTMenuPrice(price);
		menuInfo.setTMenuDetailText(t_menu_detailText);
		menuInfo.setTMenuImagePath(t_menu_imagePath);
		menuInfo.setTMenuTime(t_menu_time);

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			//int menuId = -1;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				MenuDao menuDao = new MenuDao();
				/*
				menuId = menuDao.setMenuInfoForMaster(
						dbConnection,
						salonId,
						menuInfo
						);
						*/
				int resultInt = -1;
				if(menuInfo.getTMenuMenuId()<0){
					resultInt = menuDao.save(dbConnection, menuInfo);
				}else{
					resultInt = menuDao.update(dbConnection, menuInfo);
				}
				/**
				 * SalonテーブルにmenuIdを追加
				 */
				SalonDao salonDao = new SalonDao();
				THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
				salonInfo = salonDao.get(dbConnection, salonId);
				String menuIds = salonInfo.getTHairSalonMasterMenuId();
				List<String> menuIdList = Arrays.asList(menuIds.split(","));
				if(!menuIdList.contains(menuId)) {
					menuIds="";
					for(int index=0;index<menuIdList.size();index++){
						menuIds += menuIdList.get(index)+",";
					}
					menuIds = menuIds.substring(0,menuIds.length()-1);
					salonInfo.setTHairSalonMasterMenuId(menuIds);
					resultInt = salonDao.update(dbConnection, salonInfo);
					if(resultInt > 0) result = true;
				}
				
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
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
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
