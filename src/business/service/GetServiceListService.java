package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.AreaDao;
import business.dao.CountryDao;
import business.dao.MenuDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.AreaInfo;
import common.model.CountryInfo;
import common.model.MenuInfo;
import common.util.DBConnection;

public class GetServiceListService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;

        /*
		int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		*/
		//TODO テスト用
        int salonId = 1;
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<Integer> menuIdList = new ArrayList<Integer>();
			List<MenuInfo> menuInfoList  = new ArrayList<MenuInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				menuIdList = salonDao.getMenuIdList(dbConnection, salonId);
				MenuDao menuDao = new MenuDao();
				menuInfoList = menuDao.getMenuListInfo(dbConnection, menuIdList);	
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/*
			    {
			      country:[
			        {
			          t_counrty_id,
			          t_country_name,
			          area:[
			            {
			              t_area_id,
			              t_area_name
			            },
			            ...
			          ]
			        },
			        ...
			      ]
			    }
			 */
			
			/**
			 *
			    {
			      menu:[
			        {
			          t_menu_menuId,
			          t_menu_name
			        },
			        ...
			      ]
			    }
			 */
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray menuArray = new JSONArray();
		    for(MenuInfo info : menuInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_menu_menu_id", info.getMenuId());
		    	jsonOneData.put("t_menu_name", info.getMenuName());
			    menuArray.add(jsonOneData);
		    }
		    jsonObject.put("menu",menuArray);		    
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
