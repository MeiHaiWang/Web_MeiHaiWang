package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.MenuDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.MenuInfo;
import common.util.DBConnection;

public class GetMenuInfoService {
	@SuppressWarnings({ "unchecked", "unused" })
		
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
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

        int responseStatus = HttpServletResponse.SC_OK;
				
		try{
			DBConnection dbConnection = new DBConnection();
			JSONObject jsonObject = new JSONObject();
			java.sql.Connection conn = dbConnection.connectDB();

			List<Integer> menuIdList = new ArrayList<Integer>();
			MenuInfo menuInfo = new MenuInfo();
			List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				menuIdList = salonDao.getMenuIdList(dbConnection, salonId);
				MenuDao menuDao = new MenuDao();
				menuInfoList = menuDao.getMenuInfoForMaster(dbConnection, menuIdList);				
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object(title)
			/**
			 *  {
			      menu:[
			        {
			          t_menu_categoryId,
			          t_menu_menuId,
			          t_menu_name,
			          t_menu_price,
			          t_menu_detailText,
			          t_menu_imagePath,
			        },
			      ]
			    }
			 */
			JSONArray jsonArray = new JSONArray();
		    for(MenuInfo menuOneInfo : menuInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_menu_categoryId", menuOneInfo.getMenuCategoryId());
		    	jsonOneData.put("t_menu_menuId", menuOneInfo.getMenuId());
		    	jsonOneData.put("t_menu_name", menuOneInfo.getMenuName());
		    	jsonOneData.put("t_menu_price", menuOneInfo.getMenuPrice());
		    	jsonOneData.put("t_menu_detailText", menuOneInfo.getMenuDetailText());
		    	jsonOneData.put("t_menu_imagePath", menuOneInfo.getMenuImagePath());
		    	jsonArray.add(jsonOneData);
		    }
		    // 返却用サロンデータ（jsonデータの作成）
		    jsonObject.put("menu",jsonArray);
						    		    
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
