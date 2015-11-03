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

/**
 * 
 * @author kanijunnari
 *
    getServiceList
        概要：サービス一覧取得
        入力：{ t_hairSalonMaster_salonId }
        出力：

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

public class GetServiceListService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){

		/*
		 * Declaration value
		 */
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;

		//salonId kokokara
	    int salonId = -1;
	    //get a salonId by session
		String salonId_str = "";
		if (session != null){
			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
			System.out.println("salonId_str: " + salonId_str);
		}else{
			System.out.println("session is null...");
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

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<Integer> menuIdList = new ArrayList<Integer>();
			List<MenuInfo> menuInfoList  = new ArrayList<MenuInfo>();
			
			if(conn!=null){
				SalonDao salonDao = new SalonDao();
				MenuDao menuDao = new MenuDao();

				//menuIdList for salonId in HairSalon-Table 
				menuIdList = salonDao.getMenuIdList(dbConnection, salonId);

				//menuInfoList for menuIdList in Menu-Table 
				menuInfoList = menuDao.getMenuListInfo(dbConnection, menuIdList);	

				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();			
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
