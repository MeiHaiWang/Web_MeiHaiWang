package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.MenuDao;
import business.dao.SalonDao;
import business.dao.StylistDao;
import common._model.THairSalonMasterInfo;
import common._model.TStylistInfo;
import common.constant.Constant;
import common.util.DBConnection;

public class DeleteMenuInfoService implements IServiceExcuter{
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

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null && t_menu_menuId!=null){
				MenuDao menuDao = new MenuDao();
				SalonDao salonDao = new SalonDao();
				StylistDao stylistDao = new StylistDao();
				
				/*
				result = menuDao.DeleteMenuInfoForMaster(
						dbConnection,
						t_menu_menuId,
						salonId
						);
						*/
				int resultInt = menuDao.logicalDelete(dbConnection, Integer.parseInt(t_menu_menuId));
				if(resultInt>0){ 
					/**
					 * SalonからmenuIdを削除
					 */
					THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
					salonInfo = salonDao.get(dbConnection, salonId);
					String menuIds = salonInfo.getTHairSalonMasterMenuId();
					List<String> menuIdList = Arrays.asList(menuIds.split(","));
					menuIds = "";
					for(int index=0;index<menuIdList.size();index++){
						if(!menuIdList.get(index).equals(t_menu_menuId)){
							menuIds += menuIdList.get(index)+",";
						}
					}
					menuIds = menuIds.substring(0,menuIds.length()-1);
					salonInfo.setTHairSalonMasterMenuId(menuIds);
					resultInt = salonDao.update(dbConnection, salonInfo);
				}
				if(resultInt>0){ 
					/**
					 * StylistカラムからmenuIdを削除
					 */
					List<String> stylistIdList = 
							Arrays.asList(salonDao.get(dbConnection, salonId).getTHairSalonMasterStylistId().split(","));
					List<TStylistInfo> sList = new ArrayList<TStylistInfo>();
					for(String id : stylistIdList){
						TStylistInfo stylistInfo = new TStylistInfo();
						stylistInfo = stylistDao.get(dbConnection, Integer.parseInt(id));
						List<String> stylistMenuIdList = Arrays.asList(stylistInfo.getTStylistMenuId().split(","));
						if(stylistMenuIdList.contains(id)){
							sList.add(stylistInfo);
						}
					}
					for(TStylistInfo stylistInfo : sList){
						String menuIds = stylistInfo.getTStylistMenuId();
						List<String> menuIdList = Arrays.asList(menuIds.split(","));
						menuIds = "";
						for(int index=0;index<menuIdList.size();index++){
							if(!menuIdList.get(index).equals(t_menu_menuId)){
								menuIds += menuIdList.get(index)+",";
							}
						}
						menuIds = menuIds.substring(0,menuIds.length()-1);
						stylistInfo.setTStylistMenuId(menuIds);
						resultInt = stylistDao.update(dbConnection, stylistInfo);
						if(resultInt<=0) break;
					}
					if(resultInt>0) result = true;					
				}
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
